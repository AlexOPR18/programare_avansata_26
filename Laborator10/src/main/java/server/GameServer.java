package server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer {
	public static final int PORT = 8100;
	private boolean running = true;
	private ServerSocket serverSocket;
	
	private ExecutorService threadPool = Executors.newFixedThreadPool(10);
	private List<Question> questions = new ArrayList<>();
	
	public GameServer() {
		loadQuestions();
	}
	
	private void loadQuestions() {
		try (BufferedReader br = new BufferedReader(new FileReader("intrebari.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(";");
				if(parts.length==2) {
					questions.add(new Question(parts[0], parts[1]));
				}
			} 
			System.out.println("Am incarcat " + questions.size() + " intrebari.");
		} catch (IOException e) {
			System.out.println("Eroare la citirea intrebarilor: " + e.getMessage());
		}
	}
	
	public void start() {
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("Serverul a pornit pe portul " + PORT);
			
			while(running) {
				try {
					Socket clientSocket = serverSocket.accept();
					System.out.println("Un client s-a conectat");
					threadPool.execute(new ClientThread(clientSocket, this));
				} catch (SocketException e) {
					if(!running) {
						System.out.println("Serverul se inchide...");
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
			if(serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
			threadPool.shutdown();
			System.out.println("Serverul a fost oprit complet");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Question getRandomQuestion() {
		if(questions.isEmpty()) return new Question("Nu exista intrebari", "Nimic");
		return questions.get(new Random().nextInt(questions.size()));
	}
	
	public static void main(String[] args) {
		new GameServer().start();
	}
}
