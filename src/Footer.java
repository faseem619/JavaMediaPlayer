import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;

public class Footer extends JPanel {
    Footer(){
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(400,50));
        this.setBackground(Color.red);

    }

    
}
