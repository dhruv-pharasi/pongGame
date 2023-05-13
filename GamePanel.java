import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {

    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int PONG_BALL_DIAMETER = 25;
    static final int PADDLE_WIDTH = 30;
    static final int PADDLE_HEIGHT = 110;

    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    PongBall pongBall;
    GameScore gameScore;
    boolean pause = false; // check how to pause game

    public GamePanel() {
        newPaddles();
        newPongBall();
        gameScore = new GameScore(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new ActionListener());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();

    }

    public void newPongBall() {
        random = new Random();
        pongBall = new PongBall((GAME_WIDTH / 2) - (PONG_BALL_DIAMETER / 2),
                random.nextInt((GAME_HEIGHT - PONG_BALL_DIAMETER)), PONG_BALL_DIAMETER, PONG_BALL_DIAMETER);
    }

    public void newPaddles() {
        paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH,
                PADDLE_HEIGHT, 2);
    }

    public void paint(Graphics grpcs) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        grpcs.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics grpcs) {
        paddle1.draw(grpcs);
        paddle2.draw(grpcs);
        pongBall.draw(grpcs);
        gameScore.draw(grpcs);
    }

    public void move() {
        paddle1.move();
        paddle2.move();
        pongBall.move();
    }

    public void collisionChecker() {
        // code for bouncing pongBall off bottom and top of screen
        if (pongBall.y <= 0) {
            pongBall.setRandomYDirection(-pongBall.ballYVelocity);
        }
        if (pongBall.y >= GAME_HEIGHT - PONG_BALL_DIAMETER) {
            pongBall.setRandomYDirection(-pongBall.ballYVelocity);
        }

        // code for bouncing pongBall off the paddles
        if (pongBall.intersects(paddle1)) {
            pongBall.ballXVelocity = Math.abs(pongBall.ballXVelocity);
            pongBall.ballXVelocity += 1; // will increase ball speed after every bounce on paddle
            if (pongBall.ballYVelocity > 0) {
                pongBall.ballYVelocity += 1;
            } else {
                pongBall.ballYVelocity -= 1;
            }
            pongBall.setRandomXDirection(pongBall.ballXVelocity);
            pongBall.setRandomYDirection(pongBall.ballYVelocity);
        }
        if (pongBall.intersects(paddle2)) {
            pongBall.ballXVelocity = Math.abs(pongBall.ballXVelocity);
            pongBall.ballXVelocity += 1; // will increase ball speed after every bounce on paddle
            if (pongBall.ballYVelocity > 0) {
                pongBall.ballYVelocity += 1;
            } else {
                pongBall.ballYVelocity -= 1;
            }
            pongBall.setRandomXDirection(-pongBall.ballXVelocity);
            pongBall.setRandomYDirection(pongBall.ballYVelocity);
        }

        // code for preventing paddles from moving off screen
        if (paddle1.y <= 0) {
            paddle1.y = 0;
        }
        if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
            paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        }
        if (paddle2.y <= 0) {
            paddle2.y = 0;
        }
        if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
            paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
        }

        // code for scoring and making new pongball + paddles
        if (pongBall.x <= 0) {
            gameScore.incrementPlayer2Score();
            newPaddles();
            newPongBall();
        }
        if (pongBall.x >= GAME_WIDTH - PONG_BALL_DIAMETER) {
            gameScore.incrementPlayer1Score();
            newPaddles();
            newPongBall();
        }
    }

    int gameOverScore = 5;
    ImageIcon icon = new ImageIcon("icons/pongBall.png");
    String[] playAgain = { "Play Again!" };

    public boolean gameOver() {
        if (gameScore.getPlayer1Score() == gameOverScore && gameScore.getPlayer2Score() != gameOverScore) {
            int x = JOptionPane.showOptionDialog(null, "PLAYER 1 WINS!", "GAME OVER!", JOptionPane.OK_OPTION,
                    JOptionPane.PLAIN_MESSAGE, icon, playAgain, 0);
            if (x == 0) {
                GameFrame g = new GameFrame();
            }
            return true;
        } else if (gameScore.getPlayer1Score() != gameOverScore && gameScore.getPlayer2Score() == gameOverScore) {
            int x = JOptionPane.showOptionDialog(null, "PLAYER 2 WINS!", "GAME OVER!", JOptionPane.OK_OPTION,
                    JOptionPane.PLAIN_MESSAGE, icon, playAgain, 0);
            if (x == 0) {
                GameFrame g = new GameFrame();
            }
            return true;
        } else {
            return false;
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double tickAmount = 60.0;
        double nanoSeconds = 1000000000 / tickAmount;
        double delta = 0;
        while (!gameOver()) {
            long present = System.nanoTime();
            delta += (present - lastTime) / nanoSeconds;
            lastTime = present;
            if (delta >= 1) {
                move();
                collisionChecker();
                repaint();
                delta -= 1;
            }
        }
    }

    public class ActionListener extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
