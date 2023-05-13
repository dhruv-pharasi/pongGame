import java.awt.*;
import java.awt.event.*;

public class Paddle extends Rectangle {

    int playerID;
    int paddleVelocity; // or yVelocity
    final int paddleSpeed = 12;

    public Paddle(int x, int y, int paddleWidth, int paddleHeight, int playerID) {
        super(x, y, paddleWidth, paddleHeight);
        this.playerID = playerID;

    }

    public void keyPressed(KeyEvent e) {
        switch (playerID) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(-paddleSpeed);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(paddleSpeed);
                    move();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(-paddleSpeed);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(paddleSpeed);
                    move();
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (playerID) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);
                    move();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0);
                    move();
                }
                break;
        }
    }

    public void setYDirection(int yDirection) {
        paddleVelocity = yDirection;
    }

    public void move() {
        y = y + paddleVelocity;
    }

    public void draw(Graphics grpcs) {
        if (playerID == 1)
            grpcs.setColor(Color.GREEN);
        else
            grpcs.setColor(Color.ORANGE);
        grpcs.fillRect(x, y, width, height);
    }
}
