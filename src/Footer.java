import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import java.awt.event.MouseEvent;

import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.sound.sampled.*;



public class Footer extends JPanel {
    JLabel playPausebtn;
    Boolean playing = false;
    Clip clip;
    Footer(){
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(400,120));
        this.setBackground(new Color(0x8843F2));

        // buttons

        playPausebtn = new JLabel();
        playPausebtn.setSize(new Dimension(70,70));;
        setAsIcon(playPausebtn, "play-button.png");
        playPausebtn.addMouseListener(new MouseInputAdapter() {
           
            public void mouseClicked(MouseEvent me){
                if(playing){
                    setAsIcon(playPausebtn, "play-button.png");
                    playing = false;
                    clip.stop();
                }
                else if(clip!=null){
                    setAsIcon(playPausebtn,"Pause-Button.png");
                    playing =true;setAsIcon(playPausebtn,"Pause-Button.png");
                    playing =true;

                    clip.start();

                }


            }
        });
        this.add(playPausebtn);



    }
    public void setAsIcon(JLabel label,String filepath){
    BufferedImage img = null;
    try {
        img = ImageIO.read(new File(filepath));
    } catch (IOException e) {
        e.printStackTrace();
    }
    finally{

        Image dimg = img.getScaledInstance(label.getWidth(), label.getHeight(),
            Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(dimg));
    }

    }

    public void setClip(AudioInputStream audioStream){
        System.out.println("clip test");
        try {
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (LineUnavailableException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        
    }

    
}
