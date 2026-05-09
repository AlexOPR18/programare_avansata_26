import java.util.Random;

abstract class Agent implements Runnable {
    String id;
    Maze maze;
    int r, c;
    int speed = 1000; 
    boolean paused = false;
    Random rand = new Random();

    public Agent(String id, Maze maze, int r, int c) {
        this.id = id;
        this.maze = maze;
        this.r = r;
        this.c = c;
        maze.placeInitial(this);
    }

    public void applyCommand(String cmd) {
        switch (cmd) {
            case "speedup": this.speed = Math.max(200, this.speed - 300); break;
            case "slowdown": this.speed += 500; break;
            case "stop": this.paused = true; break;
            case "resume": this.paused = false; break;
        }
    }

    @Override
    public void run() {
        while (!maze.isGameOver()) {
            try {
                if (!paused) {
                    makeMove();
                }
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    protected abstract void makeMove();
}