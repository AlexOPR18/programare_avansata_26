import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lab9App {
    public static void main(String[] args) {
        System.out.println("--- Incepem Simularea Lab 9 ---");
        Maze maze = new Maze(10, 10);
        
        Bunny bunny = new Bunny("Bugs", maze, 0, 0);
        List<Robot> robots = new ArrayList<>();
        robots.add(new Robot("R1", maze, 9, 0));
        robots.add(new Robot("R2", maze, 0, 9));
        robots.add(new Robot("R3", maze, 5, 5));
        new Thread(bunny).start();
        for (Robot r : robots) {
            new Thread(r).start();
        }

        GameDaemon daemon = new GameDaemon(maze);
        daemon.setDaemon(true);
        daemon.start();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Comenzi disponibile: 'speedup', 'slowdown', 'stop', 'resume' urmate de 'bunny', 'robots' sau ID (ex: 'stop R1'). Scrieti 'exit' pentru a iesi.");
        
        while (!maze.isGameOver()) {
            if (scanner.hasNextLine()) {
                String cmd = scanner.nextLine().toLowerCase();
                if (cmd.equals("exit")) {
                    System.out.println(">>> Ati folosit comanda exit. Inchidem jocul...");
                    System.exit(0);
                }
                
                String[] parts = cmd.split(" ");
                if (parts.length == 2) {
                    String action = parts[0];
                    String target = parts[1];

                    if (target.equals("bunny") || target.equals("all")) {
                        bunny.applyCommand(action);
                    }
                    for (Robot r : robots) {
                        if (target.equals("robots") || target.equals("all") || target.equalsIgnoreCase(r.id)) {
                            r.applyCommand(action);
                        }
                    }
                    System.out.println(">>> Comanda aplicata: " + cmd);
                }
            }
        }
        scanner.close();
    }
}
