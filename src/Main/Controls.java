package Main;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

public class Controls extends JPanel {
    private Board board;
    private Display display;
    private Timer timer;
    private JComboBox<String> configurationDropdown;

    public Controls(Board board, Display display) {
        this.board = board;
        this.display = display;
        addControlButtons();

    }
    private void addControlButtons() {
        JButton startButton = new JButton("Start");
        JButton pauseButton = new JButton("Pause");
        JButton resetButton = new JButton("Reset");
        JTextField stepNumber = new JFormattedTextField(1);
        stepNumber.setColumns(5);
        JButton jumpButton = new JButton("Jump Steps");
        JButton inButton = new JButton("Zoom In");
        JButton outButton = new JButton("Zoom Out");
        JSlider speedSlider = new JSlider(1, 500, 250);
        JTextField saveName = new JFormattedTextField("Shape Name");
        saveName.setColumns(20);
        JButton saveButton = new JButton("Save");


        timer = new Timer(100, e -> {
            board.nextStep();
            display.repaint();
        });

        startButton.addActionListener(e -> timer.start());
        pauseButton.addActionListener(e -> timer.stop());

        resetButton.addActionListener(e -> {
            timer.stop();
            board.reset();
            display.repaint();
        });

        jumpButton.addActionListener(e -> handleJumpButton(stepNumber.getText()));
        saveButton.addActionListener(e -> handleSaveButton(saveName.getText()));

        inButton.addActionListener(e -> {
            display.zoomIn();
        });
        outButton.addActionListener(e -> {
            display.zoomOut();
        });
        speedSlider.addChangeListener(e -> timer.setDelay(501 - speedSlider.getValue()));

        add(startButton);
        add(pauseButton);
        add(resetButton);
        add(stepNumber);
        add(jumpButton);
        add(saveName);
        add(saveButton);
        add(inButton);
        add(outButton);
        add(new JLabel("Speed:"));
        add(speedSlider);
    }

    private void handleJumpButton(String stepNumber) {
        try {
            int steps = Integer.parseInt(stepNumber.replace(",", ""));
            if (steps > 0) {
                timer.stop();
                for(int i = 0; i < steps; i++) {
                    board.nextStep();
                }
                display.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a positive number.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number entered. Please enter a valid integer.");
        }
    }
    private void handleSaveButton(String saveName) {
        try {
            if (!saveName.isEmpty()) {
                DefaultSetups.saveConfig(board.getCurrentCells(), saveName);
                JOptionPane.showMessageDialog(this, "Successfully saved.");
                display.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a name for the shape.");
            }
        }  catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error while saving.");
        }
    }

}
