import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputAdapter;



import java.awt.event.MouseEvent;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
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
    JLabel volumeBtn;
    Boolean playing = false;
    Boolean muted =false;
    int prevVolume;
    FloatControl volCtrl;
    Clip clip;
    Footer(){
        // -------Panel Properties----
        this.setPreferredSize(new Dimension(400,120));
        this.setBackground(new Color(0x8843F2));
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(0x8843F2));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(0x8843F2));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEADING));

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
                    clip.loop(100);


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
        volumeSlider = new JSlider(-35,0);
        volumeSlider.setSize(new Dimension(100,10));
        volumeSlider.setValue(-5);
        volumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e){
                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting()) {
                    int volume = source.getValue();
                    volume = volume<-34?-80:volume;
                    volCtrl.setValue(volume);
                    
                
            }
            }

            });

        volumeBtn = new JLabel();
        volumeBtn.setSize(30,30);
        volumeBtn.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent me){
                if(muted){
                    volCtrl.setValue(prevVolume>-34?prevVolume:-80);
                    volumeSlider.setValue(prevVolume);
                    setAsIcon(volumeBtn, "volume.png");
                    muted=false;
                }
                else{
                    volCtrl.setValue(-80);
                    prevVolume = volumeSlider.getValue();
                    volumeSlider.setValue(-35);
                    setAsIcon(volumeBtn, "mute.png");
                    muted = true;

                }

            }
        });
        setAsIcon(volumeBtn, "volume.png");
        


        topPanel.add(backBtn);
        topPanel.add(playPausebtn);
        topPanel.add(forwardBtn);

        bottomPanel.add(volumeBtn);
        bottomPanel.add(volumeSlider);
        
        this.add(topPanel,BorderLayout.NORTH);
        this.add(bottomPanel,BorderLayout.SOUTH);



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
        try {
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (LineUnavailableException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        volCtrl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        volCtrl.setValue(-5);
        
        
    }

    
}
