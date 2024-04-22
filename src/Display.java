import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Display extends JPanel{
    int width,height;
    Display(int width,int height){
        this.width=width;
        this.height=height;
        setBackground(Color.red);
        setPreferredSize(new Dimension(this.width,this.height));
        
    }
}
