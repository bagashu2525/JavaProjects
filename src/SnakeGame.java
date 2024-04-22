import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

import javax.swing.JPanel;

public class SnakeGame extends JPanel implements ActionListener,KeyListener{
    private class Tile{
        int x,y;
        Tile(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    
     int width;
     int Height;
     public int tileSize = 25;
     int speedX,speedY;
     public int count=-1;

     Tile head,food;
     
     ArrayList<Tile> sBody;
     Random random;
     Timer loop;
     boolean gameOver;
     SnakeGame(){}
     
     SnakeGame(int width,int Height){
        this.width = width;
        this.Height = Height;
        speedX=speedY=0;
        setPreferredSize(new Dimension(this.width,this.Height));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);
        
        
        head= new Tile(5, 5);
        food = new Tile(10,10);
        sBody = new ArrayList<Tile>();
        random = new Random();

        placeFood();
        loop = new Timer(100,this);
        loop.start();

        gameOver=false;
     }
     public enum GradientType {
        LINEAR, RADIAL, DIAGONAL
    }
     public void paintComponent(Graphics g){
        super.paintComponent(g);
        // Define the first gradient from top to bottom (blue to green)
        paintGradient(g, 0, 0, getWidth(), getHeight(), Color.blue, Color.DARK_GRAY, GradientType.DIAGONAL);

        // Define the second gradient from left to right (green to yellow)
        //paintGradient(g, 0, 0, getWidth(), getHeight(), Color.GREEN, Color.YELLOW, GradientType.LINEAR);

        // Define the third gradient from top-left to bottom-right (yellow to red)
        //paintGradient(g, 0, 0, getWidth(), getHeight(), Color.YELLOW, Color.RED, GradientType.DIAGONAL);
        draw(g);
     }
     private void paintGradient(Graphics g, int x, int y, int width, int height, Color color1, Color color2, GradientType type) {
        // Create start and end points based on the gradient type
        Point2D startPoint;
        Point2D endPoint;
        if (type == GradientType.LINEAR) {
            startPoint = new Point2D.Float(x, y);
            endPoint = new Point2D.Float(x, y + height);
        } else {
            startPoint = new Point2D.Float(x, y);
            endPoint = new Point2D.Float(x + width, y + height);
        }

        // Create the gradient paint object
        GradientPaint gradientPaint = new GradientPaint(startPoint, color1, endPoint, color2);

        // Create a Graphics2D object from the Graphics object
        Graphics2D g2d = (Graphics2D) g;

        // Set the paint to the gradient
        g2d.setPaint(gradientPaint);

        // Fill the background of the panel with the gradient
        g2d.fillRect(x, y, width, height);
    }

     public void draw(Graphics g){
        
        //snake head
        g.setColor(Color.cyan);
        g.fillRect(head.x*tileSize, head.y*tileSize, tileSize, tileSize);

        //food
        g.setColor(Color.red);
        g.fillOval(food.x*tileSize,food.y*tileSize,tileSize,tileSize);

        //snake body
        for (int i = 0; i<sBody.size(); i++){
            Tile snakePart = sBody.get(i);
            g.setColor(Color.cyan);
            g.fillRect(snakePart.x*tileSize, snakePart.y*tileSize, tileSize, tileSize);
        }

        //Score
        
     }

     public void placeFood(){
         food.x=random.nextInt(width/tileSize);
         food.y=random.nextInt(Height/tileSize);
         count++;
     }
     public boolean collison(Tile obj1, Tile obj2){
        return obj1.x == obj2.x && obj1.y == obj2.y;
     }
     public void move(){

        if(collison(head, food)){
            sBody.add(new Tile(food.x,food.y));
            placeFood();
            
        }
        
        for(int i=sBody.size()-1;i>=0;i--){
            Tile snakepart = sBody.get(i);
            if(i==0){
                snakepart.x=head.x;snakepart.y=head.y;
            }
            else{
                Tile prev =sBody.get(i-1);
                snakepart.x= prev.x;snakepart.y= prev.y;
            }
        }

        head.x+=speedX;
        head.y+=speedY;

        for(int i = 0 ; i< sBody.size(); i++){
            Tile snakepart = sBody.get(i);
            if(collison(head, snakepart)){
                gameOver=true;
            }
        }
        if(head.x*tileSize <= 0 || head.y*tileSize <= 0 || head.x*tileSize >= 600 || head.y*tileSize >= 600){
            gameOver=true;
        }
     }
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if(gameOver){
            loop.stop();
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println("KeyEvent: " + e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_UP && speedY != 1) {
            speedX = 0;speedY = -1;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN && speedY != -1) {
            speedX = 0;speedY = 1;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && speedX != 1) {
            speedX = -1;speedY = 0;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && speedX != -1) {
            speedX = 1;speedY = 0;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}

}