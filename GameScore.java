import java.awt.*;

public class GameScore extends Rectangle {

    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    private int scorePlayer1;
    private int scorePlayer2;

    public GameScore(int gameWidth, int gameHeight) {
        GameScore.GAME_WIDTH = gameWidth;
        GameScore.GAME_HEIGHT = gameHeight;
    }

    public void draw(Graphics grpcs) {
        grpcs.setColor(Color.CYAN);

        grpcs.setFont(new Font("Courier New", Font.PLAIN, 60));
        grpcs.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT);
        grpcs.drawString(String.valueOf(scorePlayer1 / 10) + String.valueOf(scorePlayer1 % 10), (GAME_WIDTH / 2) - 85,
                50);
        grpcs.drawString(String.valueOf(scorePlayer2 / 10) + String.valueOf(scorePlayer2 % 10), (GAME_WIDTH / 2) + 20,
                50);

        grpcs.setFont(new Font("Courier New", Font.PLAIN, 20));
        grpcs.drawString("Player 1", (GAME_WIDTH / 2) - 400, 30);
        grpcs.drawString("Player 2", (GAME_WIDTH / 2) + 300, 30);
    }

    public int getPlayer1Score() {
        return scorePlayer1;
    }

    public int getPlayer2Score() {
        return scorePlayer2;
    }

    public void incrementPlayer1Score() {
        scorePlayer1++;
    }

    public void incrementPlayer2Score() {
        scorePlayer2++;
    }
}