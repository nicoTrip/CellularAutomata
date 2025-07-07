package Main;

import javax.swing.*;
import java.awt.*;

public class GameController {

    public void run() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Conway's Game of Life");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            Board board = new Board();
            Display display = new Display(board);
            Controls controls = new Controls(board, display);

            frame.add(display, BorderLayout.CENTER);
            frame.add(controls, BorderLayout.SOUTH);

            frame.pack();
            frame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.run();
    }
}
