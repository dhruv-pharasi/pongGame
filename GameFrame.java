import java.awt.*;

import javax.swing.*;

public class GameFrame extends JFrame {

    private int startCount = 0;
    GamePanel gamePanel;

    public GameFrame() {
        this.startCount++;
        if (this.startCount > 1) {
            this.dispose();
        }
        gamePanel = new GamePanel();

        this.add(gamePanel);
        this.setTitle("PongMania");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
