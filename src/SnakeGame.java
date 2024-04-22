import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

import javax.swing.JPanel;

public class SnakeGame extends JPanel implements ActionListener{
    private class Tile{
        int x,y;
        Tile(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
     int width;
     int Height;
     int tileSize = 25;

     Tile head,food;
     Random random;
     Timer loop;

     SnakeGame(int width,int Height){
        this.width = width;
        this.Height = Height;
        setPreferredSize(new Dimension(this.width,this.Height));
        setBackground(Color.BLACK);
        
        head= new Tile(5, 5);
        food = new Tile(10,10);
        random = new Random();
        placeFood();
        loop = new Timer(1000,this);
        loop.start();
     }
     public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
     }
     public void draw(Graphics g){
        //snake
        g.setColor(Color.green);
        g.fillRect(head.x*tileSize, head.y*tileSize, tileSize, tileSize);

        //food
        g.setColor(Color.red);
        g.fillOval(food.x*tileSize,food.y*tileSize,tileSize,tileSize);
     }

     public void placeFood(){
         food.x=random.nextInt(width/tileSize);
         food.y=random.nextInt(Height/tileSize);
     }
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

}
