package lab11.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Component;

import lab11.repository.PlayerRepository;
import lab11.repository.QuestionRepository;

@Component
public class GameServer {
    public static final int PORT = 8100;
    private boolean running = true;
    private ServerSocket serverSocket;
    
    private ExecutorService threadPool = Executors.newFixedThreadPool(10);
    private final PlayerRepository playerRepository;
    private final QuestionRepository questionRepository;    
    
    public GameServer(PlayerRepository playerRepository, QuestionRepository questionRepository) {
        this.playerRepository = playerRepository;
        this.questionRepository = questionRepository;
    }
    
    
    public void start() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("[SERVER] Serverul de Quiz a pornit pe portul " + PORT);

            while (running) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("[SERVER] Un client nou s-a conectat!");
                    
                    threadPool.execute(new ClientThread(clientSocket, this, playerRepository, questionRepository));
                } catch (SocketException e) {
                    if (!running) {
                        System.out.println("[SERVER] Serverul se inchide gratios...");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stopServer();
        }
    }

    public void stopServer() {
        running = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            threadPool.shutdown();
            System.out.println("[SERVER] Serverul a fost oprit complet.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   
}