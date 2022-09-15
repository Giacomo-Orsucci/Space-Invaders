package View;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class for game over view
 * @author Giacomo Orsucci & Riccardo Pacini 4IC
 */
public class GameOver extends JFrame{
    
    private Font f1;
    
    private JLabel g, a, m, e1, o, v, e2, r;
    
    private JPanel mainPanel;
    
    private Clip ol;
    
    private static final int WAIT = 500;
    
    private boolean mute;
    
    /**
     * Parameterized constructor
     * @param title, title
     * @param mute, audio on or off 
     */
    public GameOver(String title, boolean mute){
        super(title);
        this.mute = mute;
        musicGO();
        
        //setting the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(550,150,650,700);
        setVisible(true);
        
        
        //setting fonts
        f1 = new Font("Courier", Font.BOLD, 40);
        
        mainPanel = (JPanel)getContentPane();
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(null);
        
        g = new JLabel("G");
        a = new JLabel("A");
        m = new JLabel("M");
        e1 = new JLabel("E");
        o = new JLabel("O");
        v = new JLabel("V");
        e2 = new JLabel("E");
        r = new JLabel("R");
        
        g.setFont(f1);
        a.setFont(f1);
        m.setFont(f1);
        e1.setFont(f1);
        o.setFont(f1);
        v.setFont(f1);
        e2.setFont(f1);
        r.setFont(f1);
        
        g.setForeground(Color.WHITE);
        a.setForeground(Color.WHITE);
        m.setForeground(Color.WHITE);
        e1.setForeground(Color.WHITE);
        o.setForeground(Color.WHITE);
        v.setForeground(Color.WHITE);
        e2.setForeground(Color.WHITE);
        r.setForeground(Color.WHITE);
        
        g.setBounds(195,290,30,40);
        a.setBounds(225,290,30,40);
        m.setBounds(255,290,30,40);
        e1.setBounds(285,290,30,40);
        o.setBounds(335,290,30,40);
        v.setBounds(365,290,30,40);
        e2.setBounds(395,290,30,40);
        r.setBounds(425,290,30,40);
        
        //appearing effect
        sleep(WAIT);
        mainPanel.add(g);
        sleep(WAIT);
        mainPanel.add(a);
        sleep(WAIT);
        mainPanel.add(m);
        sleep(WAIT);
        mainPanel.add(e1);
        sleep(WAIT);
        mainPanel.add(o);
        sleep(WAIT);
        mainPanel.add(v);
        sleep(WAIT);
        mainPanel.add(e2);
        sleep(WAIT);
        mainPanel.add(r);
        
        sleep(WAIT+1000);
        dispose();
        Menu menu = new Menu("Space Invaders");
    }
    
    /**
     * Method to play game over music
     */
    public void musicGO(){
        //if audio is on, play sound
        if(mute == false){
            File sf = new File("src/Sound/game_over.wav");
            AudioFileFormat aff;
            AudioInputStream ais;

            try
            {
                aff = AudioSystem.getAudioFileFormat(sf);
                ais = AudioSystem.getAudioInputStream(sf);

                AudioFormat af = aff.getFormat();

                DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat(), ((int) ais.getFrameLength() * af.getFrameSize()));

                ol = (Clip) AudioSystem.getLine(info);

                ol.open(ais);
                ol.start();
            }
            catch(UnsupportedAudioFileException ee){
                System.out.println("File audio not supported");
            }
            catch(IOException ea){
                System.out.println("Audio error");
            }
            catch(LineUnavailableException LUE){
                System.out.println("Audio error");
            }
        }
    }
    
    /**
     * Method to do the appearing effect
     * @param millis, sleeping time in millis 
     */
    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameOver.class.getName()).log(Level.SEVERE, null, ex);
        }
        repaint();
    }
}