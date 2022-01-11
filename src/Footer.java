import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
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
    JLabel forwardBtn;
    JLabel backBtn;
    JSlider volumeSlider;
    Boolean playing = false;
    Clip clip;
    Footer(){
        // -------Panel Properties----
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(400,120));
        this.setBackground(new Color(0x8843F2));

        // -----Buttons -----------

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

        forwardBtn = new JLabel();
        forwardBtn.setSize(new Dimension(70,70));
        setAsIcon(forwardBtn, "forward.png");
        forwardBtn.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent me){
                if(playing && clip!=null){
                    clip.setMicrosecondPosition(clip.getMicrosecondPosition()+5000000);

                }
            }
        });

        backBtn = new JLabel();
        backBtn.setSize(new Dimension(70,70));
        setAsIcon(backBtn, "backwards.png");
        backBtn.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent me){
                if(playing && clip!=null){
                    clip.setMicrosecondPosition(clip.getMicrosecondPosition()-5000000);
                }
            }
        });

        // ---- Volume Control--------
        volumeSlider = new JSlider();
        volumeSlider.setSize(new Dimension(100,10));
        volumeSlider.setLocation(40, 60);
        


        this.add(backBtn);
        this.add(playPausebtn);
        this.add(forwardBtn);



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
