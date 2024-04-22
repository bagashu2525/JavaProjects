import javax.swing.*;
import java.awt.*;

public class DifferentSizePanelsExample {
    public static void main(String[] args) {
        // Create the JFrame
        JFrame frame = new JFrame("Different Size Panels Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout()); // Set layout to BorderLayout

        // Create Panel 1
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.BLUE);
        panel1.setPreferredSize(new Dimension(200, 300)); // Set preferred size

        // Create Panel 2
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.RED);
        panel2.setPreferredSize(new Dimension(400, 200)); // Set preferred size

        // Add panels to the JFrame
        frame.add(panel1, BorderLayout.WEST); // Add panel1 to the left (WEST) of the JFrame
        frame.add(panel2, BorderLayout.CENTER); // Add panel2 to the center (CENTER) of the JFrame

        // Pack the frame and set it to be visible
        frame.pack();
        frame.setVisible(true);
    }
}
