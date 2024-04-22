import java.awt.BorderLayout;

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
        frame.setLayout(new BorderLayout());

        SnakeGame game = new SnakeGame(600, 600);
        frame.add(game,BorderLayout.WEST);
        Display score = new Display(game,600,100);
        frame.add(score,BorderLayout.SOUTH);
        frame.pack();
        frame.setResizable(false);
        game.requestFocus();
    }
}
