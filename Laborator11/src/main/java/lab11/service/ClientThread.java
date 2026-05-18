package lab11.service;

import lab11.entity.Player;
import lab11.entity.Question;
import lab11.repository.PlayerRepository;
import lab11.repository.QuestionRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Random;

public class ClientThread implements Runnable {
    private final Socket socket;
    private final GameServer server;
    private final PlayerRepository playerRepository;
    private final QuestionRepository questionRepository;
    
    private Player currentPlayer;
    private Question currentQuestion;
    private long questionSentTime;

    public ClientThread(Socket socket, GameServer server, PlayerRepository playerRepository, QuestionRepository questionRepository) {
        this.socket = socket;
        this.server = server;
        this.playerRepository = playerRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.println("Bun venit la Quiz multiplayer securizat prin JPA! Scrie comanda 'join <numele_tau>'");

            String request;
            while ((request = in.readLine()) != null) {
                System.out.println("[SERVER] Am primit request: " + request);

                if (request.equalsIgnoreCase("stop")) {
                    out.println("Server stopped");
                    server.stopServer();
                    break;
                } else if (request.toLowerCase().startsWith("join ")) {
                    String name = request.substring(5).trim();
                                        currentPlayer = new Player(name);
                    currentPlayer = playerRepository.save(currentPlayer);
                    
                    out.println("Te-ai inregistrat in baza de date cu succes! Scrie 'play' pentru o intrebare.");
                } else if (request.equalsIgnoreCase("play")) {
                    List<Question> questions = questionRepository.findAll();
                    if (questions.isEmpty()) {
                        out.println("Eroare: Nu exista intrebari in baza de date!");
                    } else {
                        currentQuestion = questions.get(new Random().nextInt(questions.size()));
                        questionSentTime = System.currentTimeMillis();
                        out.println("INTREBARE: " + currentQuestion.getText());
                    }
                } else if (currentQuestion != null) {
                    long responseTime = System.currentTimeMillis() - questionSentTime;
                    if (request.equalsIgnoreCase(currentQuestion.getAnswer())) {
                        out.println("CORECT! Timp de raspuns: " + (responseTime / 1000.0) + " secunde.");
                    } else {
                        out.println("GRESIT! Raspunsul corect era: " + currentQuestion.getAnswer());
                    }
                    currentQuestion = null; 
                } else {
                    out.println("Server received the request: " + request);
                }
            }
        } catch (IOException e) {
            System.out.println("[SERVER] Un client s-a deconectat.");
        } finally {
            try { socket.close(); } catch (IOException e) { e.printStackTrace(); }
        }
    }
}