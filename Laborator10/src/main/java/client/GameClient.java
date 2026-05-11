package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GameClient {
	public static void main(String[] args) {
		String serverAddress = "127.0.0.1";
		int port = 8100;
		
		try (
			Socket socket = new Socket(serverAddress, port);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Scanner scanner = new Scanner(System.in)
		) {
			System.out.println("Conectat la server!");
			
			Thread listenerThread = new Thread (() -> {
				try {
					String serverMessage;
					while ((serverMessage = in.readLine()) != null) {
						System.out.println("\n[SERVER]: " + serverMessage);
						System.out.print("> ");
					}
				} catch (IOException e) {
					System.out.println("Conexiunea cu serverul s-a inchis.");
				}
			});
			
			listenerThread.setDaemon(true);
			listenerThread.start();
			
			while(true) {
				System.out.print("> ");
				String command = scanner.nextLine();
				if(command.equalsIgnoreCase("exit")) {
					System.out.println("Iesire din joc..");
					System.exit(0);
				}
				
				out.println(command);
			}
		} catch (IOException e) {
			System.err.println("Nu ma pot conecta la server: " + e.getMessage());
		}
	}
}