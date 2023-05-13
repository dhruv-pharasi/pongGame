import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WelcomeWindow implements ActionListener {

    static GameFrame g;
    static JFrame frame;
    static JPanel panel;
    static JButton startGame;
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static ImageIcon pongMania;
    static JLabel label;

    public static void main(String[] args) {
        setWindow();
    }

    public static void setWindow() {
        // Heading Image
        pongMania = new ImageIcon("icons/PongMania.png");
        label = new JLabel(pongMania);

        frame = new JFrame("PongMania");

        panel = new JPanel();
        panel.setSize(SCREEN_SIZE);
        panel.setBackground(Color.BLACK);
        panel.setLayout(null);
        frame.add(panel);

        // PLAY Button
        startGame = new JButton();
        startGame.setText("PLAY");
        startGame.addActionListener(new WelcomeWindow());
        startGame.setBounds((GAME_WIDTH / 2) - 125, (GAME_HEIGHT / 2) - 50, 250, 100);
        startGame.setFont(new Font("Courier New", Font.BOLD, 80));
        startGame.setBackground(Color.BLACK);
        startGame.setForeground(Color.CYAN);

        panel.add(startGame);
        panel.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(SCREEN_SIZE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startGame) {
            frame.dispose();
            g = new GameFrame();
        }
    }
}
