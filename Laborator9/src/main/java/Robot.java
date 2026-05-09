class Robot extends Agent {
    public Robot(String id, Maze maze, int r, int c) { super(id, maze, r, c); }

    @Override
    protected void makeMove() {
        int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        
        for (int[] d : dirs) {
            int newR = r + d[0];
            int newC = c + d[1];
            if (newR >= 0 && newR < maze.rows && newC >= 0 && newC < maze.cols) {
                if (!maze.visitedByRobots[newR][newC]) {
                    if (maze.moveAgent(this, r, c, newR, newC)) return;
                }
            }
        }
        
        int[] d = dirs[rand.nextInt(4)];
        maze.moveAgent(this, r, c, r + d[0], c + d[1]);
    }
}