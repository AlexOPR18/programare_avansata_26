public class Maze {
    int rows, cols;
    boolean gameOver = false;
    boolean[][] visitedByRobots;
    Agent[][] grid;

    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Agent[rows][cols];
        this.visitedByRobots = new boolean[rows][cols];
    }

    public synchronized boolean moveAgent(Agent agent, int oldR, int oldC, int newR, int newC) {
        if (gameOver) return false;
        
        if (newR < 0 || newR >= rows || newC < 0 || newC >= cols) return false;

        Agent occupant = grid[newR][newC];

        if (occupant != null && occupant.getClass() != agent.getClass()) {
            gameOver = true;
            System.out.println("\n!!! ROBOTII AU PRINS IEPURASUL LA (" + newR + ", " + newC + ") !!!");
            return true;
        }

        if (occupant != null && occupant instanceof Robot && agent instanceof Robot) {
            return false; 
        }

        if (agent instanceof Bunny && newR == rows - 1 && newC == cols - 1) {
            gameOver = true;
            System.out.println("\n!!! IEPURASUL A EVADAT !!!");
            return true;
        }

        grid[oldR][oldC] = null;
        grid[newR][newC] = agent;
        agent.r = newR;
        agent.c = newC;
        
        if (agent instanceof Robot) {
            visitedByRobots[newR][newC] = true;
        }

        return true;
    }

    public synchronized void placeInitial(Agent agent) {
        grid[agent.r][agent.c] = agent;
    }

    public synchronized boolean isGameOver() {
        return gameOver;
    }

    public synchronized void printBoard() {
        if (gameOver) return;
        System.out.println("-------------------------");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == rows - 1 && j == cols - 1 && grid[i][j] == null) {
                    System.out.print("[E] "); // Exit
                } else if (grid[i][j] == null) {
                    System.out.print("[ ] ");
                } else {
                    System.out.print("[" + grid[i][j].id.charAt(0) + "] ");
                }
            }
            System.out.println();
        }
        System.out.println("-------------------------");
    }
}

