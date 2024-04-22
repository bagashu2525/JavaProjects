import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
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
     public int count=0;

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
     public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
     }
     public void draw(Graphics g){
        //snake head
        g.setColor(Color.green);
        g.fillRect(head.x*tileSize, head.y*tileSize, tileSize, tileSize);

        //food
        g.setColor(Color.red);
        g.fillOval(food.x*tileSize,food.y*tileSize,tileSize,tileSize);

        //snake body
        for (int i = 0; i<sBody.size(); i++){
            Tile snakePart = sBody.get(i);
            g.setColor(Color.green);
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
