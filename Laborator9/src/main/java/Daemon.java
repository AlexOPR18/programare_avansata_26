class GameDaemon extends Thread {
    Maze maze;
    long startTime;

    public GameDaemon(Maze maze) {
        this.maze = maze;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void run() {
        while (!maze.isGameOver()) {
            try {
                Thread.sleep(2000); 
                long elapsed = (System.currentTimeMillis() - startTime) / 1000;
                
                System.out.println("\n[DAEMON] Timp scurs: " + elapsed + " secunde.");
                maze.printBoard();

                if (elapsed > 30) {
                    System.out.println("!!! TIMP EXPIRAT. JOCUL S-A OPRIT !!!");
                    System.exit(0);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}