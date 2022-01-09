import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Player {
    Player(){
        JFrame frame = new JFrame("Media Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(400,400);
        
        frame.add(new JLabel("Test"));
        frame.add(new Footer(),BorderLayout.SOUTH);
        frame.setVisible(true);

    }
    
}
