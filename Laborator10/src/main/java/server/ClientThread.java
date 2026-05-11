package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread implements Runnable {
	private Socket socket;
	private GameServer server;
	private long questionSentTime;
	private Question currentQuestion;
	
	public ClientThread(Socket socket, GameServer server) {
		this.socket = socket;
		this.server = server;
	}
	
	@Override
	public void run() {
		try (
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
		) {
			String request;
			out.println("Bun venit la Quiz! Trimite comanda 'play' pentru o intrebare, 'stop' pentru a opri serverul");
			
			while((request = in.readLine()) != null) {
				System.out.println("Am primit de la client: " + request);
				
				if(request.equalsIgnoreCase("stop")) {
					out.println("Server stopped");
					server.stopServer();
					break;
				} else if (request.equalsIgnoreCase("play")) {
					currentQuestion = server.getRandomQuestion();
					questionSentTime = System.currentTimeMillis();
					out.println("Intrebare: " + currentQuestion.getText() + " (Esti contra-cronometru!)");
				} else if (currentQuestion != null) {
					long responseTime = System.currentTimeMillis() - questionSentTime;
					if(request.equalsIgnoreCase(currentQuestion.getAnswer())) {
						out.println("Corect! Timp de raspuns: " + (responseTime / 1000.0) + " secunde.");
					} else {
						out.println("Gresit! Raspunsul corect era: " + currentQuestion.getAnswer());
					}
				currentQuestion = null;
				} else {
					out.println("Server received the request: " + request);
				}
			}
		} catch (IOException e) {
			System.out.println("Client deconectat brusc.");
		} finally {
			try { socket.close(); } catch (IOException e) { e.printStackTrace();}
		}
	}
}
