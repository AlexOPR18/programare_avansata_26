package lab8;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

public class Lab8App extends JFrame {
	private MazePanel canvas;

	public Lab8App() {
		super("Maze Gen - Laborator 8");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(850, 700);
		setLayout(new BorderLayout());
		
		init();
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void init() {
		canvas = new MazePanel();
		JPanel centerWrapper = new JPanel();
		centerWrapper.add(canvas);
		add(centerWrapper, BorderLayout.CENTER);
		
		JPanel configPanel = new JPanel();
		JLabel label = new JLabel("Grid Size: ");
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(10, 5, 50, 1));
		JButton btnDraw = new JButton("Draw");
		
		btnDraw.addActionListener(e -> {
			int size = (int) spinner.getValue();
			canvas.initGrid(size, size);
		});
		
		configPanel.add(label);
		configPanel.add(spinner);
		configPanel.add(btnDraw);
		add(configPanel, BorderLayout.NORTH);
		
		JPanel controlPanel = new JPanel();
        JButton btnCreate = new JButton("Create (Random)");
        JButton btnValidate = new JButton("Validate Path");
        JButton btnExport = new JButton("Export PNG");
        JButton btnSave = new JButton("Save Data");
        JButton btnLoad = new JButton("Load Data");
        JButton btnReset = new JButton("Reset");
        JButton btnExit = new JButton("Exit");
        
        btnCreate.addActionListener(e -> canvas.createRandomMaze());
        
        btnValidate.addActionListener(e -> {
            boolean isSolvable = canvas.validateMaze();
            if(isSolvable) JOptionPane.showMessageDialog(this, "Labirintul POATE fi parcurs!");
            else JOptionPane.showMessageDialog(this, "Eroare: Nu există drum de la Start la End!");
        });

        btnExport.addActionListener(e -> canvas.exportToPNG("maze_export.png"));
        btnSave.addActionListener(e -> canvas.saveStatus("maze.dat"));
        btnLoad.addActionListener(e -> canvas.loadStatus("maze.dat"));
	
        btnReset.addActionListener(e -> canvas.initGrid((int) spinner.getValue(), (int) spinner.getValue()));
        btnExit.addActionListener(e -> System.exit(0));
        
        controlPanel.add(btnCreate);
        controlPanel.add(btnValidate);
        controlPanel.add(btnExport);
        controlPanel.add(btnSave);
        controlPanel.add(btnLoad);
        controlPanel.add(btnReset);
        controlPanel.add(btnExit);
        add(controlPanel, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Lab8App());
	}
}
