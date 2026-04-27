package lab8;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class MazePanel extends JPanel {
	private int rows = 10, cols = 10;
	private int cellSize = 40;
	private Cell[][] grid;
	
	public MazePanel() {
		initGrid(rows, cols);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toggleWall(e.getX(), e.getY());
			}
		});
	}
	
	public void initGrid(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		grid = new Cell[rows][cols];
		for(int r = 0; r<rows; r++) {
			for (int c =0; c<cols; c++) {
				grid[r][c] = new Cell(r,c);
			}
		}
		
		setPreferredSize(new Dimension(cols * cellSize, rows * cellSize));
        revalidate();
		repaint();
	}
	
	public void createRandomMaze() {
		initGrid(rows, cols);
		Random rand = new Random();
		for (int r=0; r<rows; r++) {
			for (int c=0; c<cols; c++) {
				int wallToRemove = rand.nextInt(4);
				grid[r][c].walls[wallToRemove] = false;
				syncAdjacentWalls(r,c,wallToRemove, false);
			}
		}
		repaint();
	}
	
	private void toggleWall(int x, int y) {
		int c= x /cellSize;
		int r= y /cellSize;
		
		if(r>=rows||c>=cols) return;
		
		int rx= x% cellSize;
		int ry = y % cellSize;
		
		int[] dists = {ry, cellSize - rx, cellSize - ry, rx};
		int minIndex= 0;
		for(int i =1; i<4;i++) {
			if(dists[i]<dists[minIndex]) minIndex=i;
		}
		
		boolean newState = !grid[r][c].walls[minIndex];
		grid[r][c].walls[minIndex] = newState;
		syncAdjacentWalls(r,c,minIndex,newState);
		repaint();
	}
	
	private void syncAdjacentWalls(int r, int c, int wallIndex, boolean state) {
		if (wallIndex == 0 && r >0) grid[r-1][c].walls[2] = state;
		if (wallIndex == 1 && c <cols-1) grid[r][c+1].walls[3] =state;
		if (wallIndex == 2 && r < rows -1) grid[r+1][c].walls[0] = state;
		if (wallIndex == 3 && c > 0) grid[r][c-1].walls[1] = state;
	}
	
	public boolean validateMaze() {
		boolean[][] visited = new boolean[rows][cols];
		Queue<Cell> queue = new LinkedList<>();
		
		queue.add(grid[0][0]);
		visited[0][0] = true;
		
		while(!queue.isEmpty()) {
			Cell curr = queue.poll();
			int r = curr.row;
			int c = curr.col;
			if(r==rows-1 && c==cols-1) return true;
			
			if(!curr.walls[0] && r>0 && !visited[r-1][c]) {visited[r-1][c] = true; queue.add(grid[r-1][c]);}
			if (!curr.walls[1] && c < cols - 1 && !visited[r][c + 1]) { visited[r][c + 1] = true; queue.add(grid[r][c + 1]); }
            if (!curr.walls[2] && r < rows - 1 && !visited[r + 1][c]) { visited[r + 1][c] = true; queue.add(grid[r + 1][c]); }
            if (!curr.walls[3] && c > 0 && !visited[r][c - 1]) { visited[r][c - 1] = true; queue.add(grid[r][c - 1]); }
        }
		return false;
	}
	
	public void exportToPNG(String filename) {
		BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = image.createGraphics();
		paint(g2d);
		try {
			ImageIO.write(image, "PNG", new File(filename));
			JOptionPane.showMessageDialog(this, "Imagine salvata ca: " + filename);
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public void saveStatus(String filename) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
			out.writeObject(grid);
			JOptionPane.showMessageDialog(this, "Labirint salvat!");
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public void loadStatus(String filename) {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
			grid = (Cell[][]) in.readObject();
			rows = grid.length;
			cols = grid[0].length;
			repaint();
			JOptionPane.showMessageDialog(this, "Labirint incarcat");
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(2));
		
		if(grid == null) return;
		for(int r=0; r<rows; r++) {
			for(int c=0; c<cols; c++) {
				int x= c * cellSize;
				int y= r * cellSize;
				Cell cell = grid[r][c];
				
				if (r == 0 && c == 0) { g2d.setColor(Color.GREEN); g2d.fillRect(x, y, cellSize, cellSize); }
                else if (r == rows - 1 && c == cols - 1) { g2d.setColor(Color.RED); g2d.fillRect(x, y, cellSize, cellSize); }
                
                g2d.setColor(Color.BLACK);
                if (cell.walls[0]) g2d.drawLine(x, y, x + cellSize, y);
                if (cell.walls[1]) g2d.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
                if (cell.walls[2]) g2d.drawLine(x, y + cellSize, x + cellSize, y + cellSize);
                if (cell.walls[3]) g2d.drawLine(x, y, x, y + cellSize);
			}
		}
	}
}
