package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;

public class Display extends JPanel{
    private final int initGridX = 40;
    private final int initGridY = 60;
    private int gridX = 40;
    private int gridY = 60;
    private Board board;
    private int cellSize = 20;
    private double zoom = 1.0;
    private JComboBox<String> configurationDropdown;
    private JLabel iterationLabel;
    private JLabel liveCellLabel;

    public Display(Board board) {
        this.board = board;
        this.setPreferredSize(new Dimension(gridX * cellSize, gridY * cellSize));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int col = e.getX() / cellSize;
                int row = e.getY() / cellSize;
                Cell cell = new Cell(row - gridX / 2, col - gridY / 2);
                board.changeCell(cell);
                System.out.println(cell);
                repaint();
            }
        });
        String[] configurations = DefaultSetups.getConfigNames();
        configurationDropdown = new JComboBox<>(configurations);


        configurationDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedConfig = (String) configurationDropdown.getSelectedItem();
                handleConfigurationSelection(selectedConfig);
            }
        });
        add(configurationDropdown, BorderLayout.NORTH);

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(2, 1));
        iterationLabel = new JLabel("Iterations: 0");
        liveCellLabel = new JLabel("Live Cells: 0");
        statsPanel.add(iterationLabel);
        statsPanel.add(liveCellLabel);

        add(statsPanel, BorderLayout.EAST);

    }
    private void handleConfigurationSelection(String config) {
        System.out.println("Selected configuration: " + config);
        board.reset();
        HashSet<Cell> cells = DefaultSetups.getConfig(config);
        for(Cell cell : cells) {
            board.changeCell(cell);
        }
        repaint();
    }

    public void zoomIn() {
        zoom *= 1.5;
        updateCellSize();
    }

    public void zoomOut() {
        zoom /= 1.5;
        updateCellSize();
    }

    private void updateCellSize() {
        cellSize = (int) (20 * zoom);
        gridX = (int) (initGridX / zoom);
        gridY = (int) (initGridY / zoom);
        revalidate();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (zoom < .3) { // zoomed out so grid no longer drawn
            for (Cell cell : board.getCurrentCells()) {
                int row = cell.getX() + gridX / 2;
                int col = cell.getY() + gridY / 2;
                g.setColor(Color.BLACK);
                g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
            }
            updateStats();
            return;
        }
        for (int row = 0; row < gridX; row++) {
            for (int col = 0; col < gridY; col++) {
                if (board.contains(new Cell(row - gridX / 2, col - gridY / 2))) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
                g.setColor(Color.GRAY);
                g.drawRect(col * cellSize, row * cellSize, cellSize, cellSize);
            }
        }
        updateStats();
    }
    private void updateStats() {
        iterationLabel.setText("Iterations: " + board.getIterations());
        liveCellLabel.setText("Live Cells: " + board.getAliveNumber());
    }
}
