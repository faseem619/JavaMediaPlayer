import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;


import javax.sound.sampled.*;

public class Player implements ActionListener{
    JMenu fileMenu;
    JMenuItem openItem;
    Footer footer;
    JLabel fileName;
    JPanel fileNamePanel;
    Player(){
        JFrame frame = new JFrame("Media Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(400,400);

        // Menubar
        JMenuBar menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        openItem = new JMenuItem("Open");
        openItem.addActionListener(this);
        fileMenu.add(openItem);
        
        menuBar.add(fileMenu);

        fileName = new JLabel("No File Selected");
        fileNamePanel = new JPanel();
        fileNamePanel.setLayout(new GridBagLayout());
        fileNamePanel.setPreferredSize(new Dimension(200,100));


    
        footer = new Footer();
        frame.add(footer,BorderLayout.SOUTH);
         fileNamePanel.add(fileName);
        frame.add(fileNamePanel);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==openItem){
            JFileChooser fileChooser = new JFileChooser();
           fileChooser.setCurrentDirectory(new File("."));
           fileChooser.setFileFilter( new FileNameExtensionFilter("Wav Files", "wav"));

           int response = fileChooser.showOpenDialog(null);
           if(response==JFileChooser.APPROVE_OPTION){
               fileName.setText(fileChooser.getSelectedFile().getName());
               File myFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
               try {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(myFile);
                
                footer.setClip(audioStream);
            } catch (UnsupportedAudioFileException e1) {

                e1.printStackTrace();
            } catch (IOException e1) {

                e1.printStackTrace();
            } 
                
            }
        
        }
    
    }
}
