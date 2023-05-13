import java.awt.*;
import java.util.*;

public class PongBall extends Rectangle {

    Random random;
    int ballXVelocity;
    int ballYVelocity;
    int initialPongBallSpeed = 3;

    public PongBall(int x, int y, int width, int height) {
        super(x, y, width, height);
        random = new Random();
        int randomizedXDirection = random.nextInt(2);
        if (randomizedXDirection == 0) {
            randomizedXDirection -= 1;
        }
        setRandomXDirection(randomizedXDirection * initialPongBallSpeed);

        int randomizedYDirection = random.nextInt(2);
        if (randomizedYDirection == 0) {
            randomizedYDirection -= 1;
        }
        setRandomYDirection(randomizedYDirection * initialPongBallSpeed);
    }

    public void setRandomXDirection(int randomizedXDirection) {
        ballXVelocity = randomizedXDirection;
    }

    public void setRandomYDirection(int randomizedYDirection) {
        ballYVelocity = randomizedYDirection;
    }

    public void move() {
        x += ballXVelocity;
        y += ballYVelocity;
    }

    public void draw(Graphics grpcs) {
        grpcs.setColor(Color.YELLOW);
        grpcs.fillOval(x, y, height, width);
    }
}
