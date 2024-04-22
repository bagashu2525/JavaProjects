import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.random.*;

import javax.swing.JPanel;

public class SnakeGame extends JPanel{
    private class Tile{
        int x,y;
        Tile(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
     int broadwidth;
     int broadHeight;
     int tileSize = 25;

     Tile head,food;

     SnakeGame(int broadWidth,int boarddHeight){
        this.broadHeight = boarddHeight;
        this.broadwidth= broadWidth;
        setPreferredSize(new Dimension(this.broadwidth,this.broadHeight));
        setBackground(Color.BLACK);
        
        head= new Tile(5, 5);
        food = new Tile(10,10);
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

}
