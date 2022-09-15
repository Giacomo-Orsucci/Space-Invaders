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
import javax.swing.*;

/**
 * Class for aliens element (JLabel)
 * @author Giacomo Orsucci & Riccardo Pacini 4IC
 */
public class Alien extends JLabel{
    
    private int height;
    private int width;
    private int x;
    private int y;
    private int rightLimit;
    private int downLimit;
    private int direction = 1; // 1=right, -1=left
    private int score;
    
    private JPanel panel;
    
    private Rectangle rectangle;
    
    private ImageIcon alienType;
    
    private Clip ol, ol2, ol3;
    
    private boolean mute;
    
    /**
     * Parametrized constructor
     * @param height, alien height
     * @param width, alien width
     * @param x, alien x position
     * @param y, alien y position
     * @param rightLimit, right limit for the movement
     * @param downLimit, downlimit for gameover
     * @param panel, alien panel
     * @param alienType, alien image
     * @param rectangle, alien hitbox
     * @param score, score for alien destruction
     * @param mute, alien sound or not 
     */
    public Alien(int height, int width, int x, int y, int rightLimit, int downLimit, JPanel panel, ImageIcon alienType, Rectangle rectangle, int score, boolean mute){
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
        this.rightLimit = rightLimit;
        this.downLimit = downLimit;
        this.panel = panel;
        this.alienType = alienType;
        this.rectangle = rectangle;
        this.score = score;
        this.mute = mute;
        
        Image j = alienType.getImage().getScaledInstance(50, 40, 50);
        alienType.setImage(j);
        setIcon(alienType);
        setDoubleBuffered(true);
    }
    
    /**
     * Method for alien movement sound
     */
    public void alienExpSound(){ // alien explosion sound
        
        if(mute == false){ //if audio is on, play it 
            File sf = new File("src/Sound/alien_death.wav");
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
     * Method for alien sound when it reaches the left border
     */
    public void borderLSound(){ // left border sound
        
        if(mute == false){   
            File sf = new File("src/Sound/fastinvader1.wav");
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
                ol2.start();
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
     * Method for alien sound when it reaches right limit
     */
    public void borderRSound(){ // right border sound
        
        if(mute == false){
            File sf = new File("src/Sound/fastinvader2.wav");
            AudioFileFormat aff;
            AudioInputStream ais;

            try
            {
                aff = AudioSystem.getAudioFileFormat(sf);
                ais = AudioSystem.getAudioInputStream(sf);

                AudioFormat af = aff.getFormat();

                DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat(), ((int) ais.getFrameLength() * af.getFrameSize()));

                ol3 = (Clip) AudioSystem.getLine(info);

                ol3.open(ais);
                ol3.start();
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
    
    //GETTER E SETTER
    @Override
    public int getHeight(){return height;}
    @Override
    public int getWidth(){return width;}
    @Override
    public int getX(){return x;}
    public void setX(int x){this.x = x;}
    @Override
    public int getY(){return y;}
    public void setY(int y){this.y = y;}
    public int getDirection(){return direction;}
    public void setDirection(int direction){this.direction = direction;}
    public JPanel getPanel(){return panel;}
    public int getRightLimit(){return rightLimit;}
    public int getDownLimit(){return downLimit;}
    public ImageIcon getImageIcon(){return alienType;}
    public Rectangle getRectangle(){return rectangle;}
    public int getScore(){return score;}
}