package Elements;

import View.Menu;
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
 * Class for ship element (JLabel)
 * @author Giacomo Orsucci & Riccardo Pacini 4IC
 */
public class Ship extends JLabel{
    
    private int height;
    private int width;
    private int x;
    private int y;
    private int rightLimit;
    private int direction;
    
    private boolean moving, shooting=true, firsMusic=true;
    
    private JPanel panel;
     
    private ImageIcon shipType;

    private Rectangle rectangle;

    private Clip ol, ol2;
    
    private boolean mute;
    
    /**
     * Parameterized constructor
     * @param height, ship height
     * @param width, ship width
     * @param x, ship x position
     * @param y, ship y position
     * @param rightLimit, ship right limit reachble
     * @param menu, menu reference
     * @param panel, ship panel
     * @param mute, audio on or off 
     */
    public Ship(int height, int width, int x, int y, int rightLimit, Menu menu, JPanel panel, boolean mute) {
        
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
        this.rightLimit = rightLimit;
        this.shipType = menu.getImageIcon(); 
        this.panel = panel;
        this.mute = mute;
        
        rectangle = new Rectangle(x,y,width,height);
        
        Image j = shipType.getImage().getScaledInstance(50, 40, 50);
        shipType.setImage(j);
        setIcon(shipType);
        setDoubleBuffered(true);
    }
    
    /**
     * To set if the ship can move and in which direction
     * @param direction, ship direction -1 left and 1 right
     * @param moving, if the ship can move or not 
     */
    public void movement(int direction, boolean moving){
        this.direction = direction;
        this.moving = moving;
    }
    
    /**
     * Method for ship shot sound
     */
    public void shotSound(){
        
        //if audio is on, play the sound
        if(mute == false){
            File sf = new File("src/Sound/ship_shoot.wav");
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
     * Method for ship explosion sound
     */
    public void shipExpSound(){
        
        //if the audio is on, play the sound
        if(mute == false){
            File sf = new File("src/Sound/explosion.wav");
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
    
     //GETTER AND SETTER
    @Override
    public int getHeight(){return height;}
    @Override
    public int getWidth(){return width;}
    @Override
    public int getX(){return x;}
    @Override
    public int getY(){return y;}
    public int getRightLimit(){return rightLimit;}
    public JPanel getPanel(){return panel;}
    public int getDirection(){return direction;}
    public boolean isMoving(){return moving;}
    public ImageIcon getImageIcon(){return shipType;}
    public void setX(int x){this.x = x;}
    public Rectangle getRectangle(){return rectangle;}
    public void setRectangle(Rectangle rectangle){this.rectangle = rectangle;}
    public boolean isShooting(){return shooting;}
    public void setShooting(boolean shooting){this.shooting = shooting;}
}