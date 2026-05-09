class Bunny extends Agent {
    public Bunny(String id, Maze maze, int r, int c) { super(id, maze, r, c); }

    @Override
    protected void makeMove() {
        int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        int[] d = dirs[rand.nextInt(4)];
        maze.moveAgent(this, r, c, r + d[0], c + d[1]);
    }
}