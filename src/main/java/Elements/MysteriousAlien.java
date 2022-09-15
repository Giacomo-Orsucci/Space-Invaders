package Elements;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class for red alien element
 * @author Giacomo Orsucci & Riccardo Pacini 4IC
 */
public class MysteriousAlien extends JLabel{
    private int height;
    private int width;
    private int x;
    private int y;
    private int score = 200;
    private int rightLimit;
    
    private Rectangle rectangle;
    
    private ImageIcon alienType;
    
    private JPanel panel;
    
    private Clip ol, ol2;
    
    private boolean mute;
    
    /**
     * Parameterized constructor
     * @param height, mysterious alien height
     * @param width, mysterious alien width
     * @param x, mysterious alien x position
     * @param y, mysterious alien y position
     * @param panel, mysterious alien panel
     * @param alienType, mysterious alien image
     * @param mute, sound on or off 
     */
    public MysteriousAlien(int height, int width, int x, int y, JPanel panel, ImageIcon alienType, boolean mute){
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
        this.panel = panel;
        this.rightLimit = panel.getWidth();
        this.alienType = alienType;
        this.rectangle = new Rectangle(x,y,width,height);
        this.mute = mute;
        
        Image j = alienType.getImage().getScaledInstance(width, height, width);
        alienType.setImage(j);
        
        setIcon(alienType);
        
        setBounds(x,y,width,height);
        
        setDoubleBuffered(true);
        setVisible(true);
    }
    
    /**
     * Method for mysterious alien explosion sound
     */
    public void mAExpSound(){ 
        
        //if sound is on, play the sound
        if(mute == false){
            File sf = new File("src/Sound/ufo_highpitch.wav");
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
     * Method for mysterious alien sound moving
     */
    public void movingSound(){ 
        
        //if audio is on play the sound
        if(mute == false){   
            File sf = new File("src/Sound/ufo_lowpitch.wav");
            AudioFileFormat aff;
            AudioInputStream ais;

            try
            {
                aff = AudioSystem.getAudioFileFormat(sf);
                ais = AudioSystem.getAudioInputStream(sf);

                AudioFormat af = aff.getFormat();

                DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat(), ((int) ais.getFrameLength() * af.getFrameSize()));

                ol2 = (Clip) AudioSystem.getLine(info);

                ol2.open(ais);
                ol2.loop(Clip.LOOP_CONTINUOUSLY);
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
        /* 
            The method is recalled a second time when the alien
            has passed, after the setting of mute on true
        */
        else{ 
            if( ol2 != null)
             ol2.stop();
        }
    }
    
    //GETTER E SETTER
    @Override
    public int getWidth() {return width;}
    public void setWidth(int width) {this.width = width;}
    @Override
    public int getX() {return x;}
    public void setX(int x) {this.x = x;}
    @Override
    public int getY() {return y;}
    public void setY(int y) {this.y = y;}
    public int getRightLimit() {return rightLimit;}
    public void setRightLimit(int rightLimit) {this.rightLimit = rightLimit;}
    public Rectangle getRectangle() {return rectangle;}
    public void setRectangle(Rectangle rectangle) {this.rectangle = rectangle;}
    public JPanel getPanel() {return panel;}
    public void setPanel(JPanel panel) {this.panel = panel;}
    public boolean getMute() {return mute;}
    public void setMute(boolean mute) {this.mute = mute;}
    public int getScore() {return score;}
    public void setImage(ImageIcon newImage){ //to set in the right way the new image
        
        Image l = newImage.getImage().getScaledInstance(60,30,60);
        newImage.setImage(l);
        setIcon(newImage);
    }
}
