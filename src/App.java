import javax.swing.*;
public class App {
    public static void main(String[] args) throws Exception {
        int width = 600;
        int height = 600;

        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(width,height);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SnakeGame game = new SnakeGame(width, height);
        frame.add(game);
        frame.pack();
        frame.setResizable(false);
    }
}
