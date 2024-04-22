import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Display extends JPanel implements ActionListener {
    int width, height;
    SnakeGame snake;
    Timer timer;

    Display(SnakeGame game,int width, int height) {
        this.width = width;
        this.height = height;
        snake=game;
        setBackground(Color.black);
        setPreferredSize(new Dimension(this.width, this.height));
        
        // Start a timer to repaint the panel at regular intervals
        timer = new Timer(100, this); // Adjust the delay as needed
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // Score
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        if (snake.gameOver) {
            g.setColor(Color.RED);
            g.drawString("Game Over!!! Score: " + snake.count, snake.tileSize - 16, snake.tileSize);
            System.out.println("Game Over. Score: " + snake.count);
        } else {
            g.setColor(Color.white);
            g.drawString("Score: " + snake.count, snake.tileSize - 16, snake.tileSize);
            System.out.println("Current Score: " + snake.count);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // This will call paintComponent
        repaint();
        if(snake.gameOver){
            timer.stop();
        }
    }

    
}
