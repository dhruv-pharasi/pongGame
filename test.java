import javax.swing.*;

public class test {

    public static void main(String[] args) {

        // JLabel = a GUI display area for a string of text, an image, or both

        ImageIcon image = new ImageIcon("icons/PongMania.png");

        JLabel label = new JLabel(); // create a label
        label.setText("bro, do you even code?"); // set text of label
        label.setIcon(image);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.add(label);
    }
}
