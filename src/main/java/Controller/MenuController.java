package Controller;

import View.FirstLevel;
import View.InfoMenu;
import View.Menu;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Class for menu view KeyListener
 * @author Giacomo Orsucci & Riccardo Pacini 4IC
 */
public class MenuController implements KeyListener{
    
    private Menu menu;
    private InfoMenu iM;
    private FirstLevel fL;
    
    private int keyCode;
    private int shipNumber;
    
    private Toolkit toolKit = Toolkit.getDefaultToolkit();
        
    private ImageIcon  cyanShip = new ImageIcon(toolKit.getImage("src/Sprite/cyan_ship.png"));
    private ImageIcon  greenShip = new ImageIcon(toolKit.getImage("src/Sprite/green_ship.png"));
    private ImageIcon  pinkShip = new ImageIcon(toolKit.getImage("src/Sprite/pink_ship.png"));
    private ImageIcon  redShip = new ImageIcon(toolKit.getImage("src/Sprite/red_ship.png"));
    private ImageIcon  greenShip2 = new ImageIcon(toolKit.getImage("src/Sprite/green_ship.png"));
    private ImageIcon  cyanShip2 = new ImageIcon(toolKit.getImage("src/Sprite/cyan_ship.png"));
    private ImageIcon  pinkShip2 = new ImageIcon(toolKit.getImage("src/Sprite/pink_ship.png"));
    private ImageIcon  redShip2 = new ImageIcon(toolKit.getImage("src/Sprite/red_ship.png"));
    private ImageIcon  muteImage = new ImageIcon(toolKit.getImage("src/Sprite/mute_img.png"));
    private ImageIcon  soundImage = new ImageIcon(toolKit.getImage("src/Sprite/sound_img.png"));
    
    private JLabel shipLab;
   
    /**
     * Parameterized constructor
     * @param menu, menu view reference 
     */
    public MenuController(Menu menu){
        this.menu = menu;
    }
    
    /**
     * Parameterized constructor
     * @param iM, info view reference 
     */
    public MenuController(InfoMenu iM){
        this.iM = iM;
    }
    
    /**
     * Empty
     * @param e, event origin 
     */
    @Override
    public void keyTyped(KeyEvent e) {}
    
    /**
     * Empty
     * @param e, event origin 
     */
    @Override
    public void keyPressed(KeyEvent e) {}
    
    /**
     * Method for action following key releasing
     * @param e, event origin 
     */
    @Override
    public void keyReleased(KeyEvent e) {
        
        keyCode = e.getKeyCode(); //we get the event origin
        
        shipNumber = menu.getShipNumber(); 
        shipLab = menu.getShipLab();
        
        //sound off/sound on, mute/smute and different icons set
        if(keyCode == KeyEvent.VK_M){
            boolean mute = menu.isMute();
            mute = !mute;
            if(!mute){
                Image h = soundImage.getImage().getScaledInstance(30, 30, 30);
                soundImage.setImage(h);
                menu.getSoundLab().setIcon(soundImage);
            }
            else{
                Image h = muteImage.getImage().getScaledInstance(30, 30, 30);
                muteImage.setImage(h);
                menu.getSoundLab().setIcon(muteImage);
            }
            menu.setMute(mute);
            menu.music();
        }
        
        /*
            Different colored ships are scrolled in conseguence of 
            the pressure of the right arrow or the left arrow
        */
        if(keyCode == KeyEvent.VK_RIGHT){
            
            if(shipNumber < 4){
                shipNumber++; //scrolling up
            }
            else
                shipNumber = 1;
            
            menu.setShipNumber(shipNumber);
            
            /*
                Different ships scrolled and painted
            */
            switch(shipNumber){
                case 1: 
                    shipLab.setIcon(greenShip);
                    Image j = greenShip.getImage().getScaledInstance(100, 90, 100);
                    greenShip.setImage(j);
                    menu.livesDrawingUpdate(greenShip2);
                    break;
                case 2:
                    shipLab.setIcon(cyanShip);
                    Image l = cyanShip.getImage().getScaledInstance(100, 90, 100);
                    cyanShip.setImage(l);
                    menu.livesDrawingUpdate(cyanShip2);
                    menu.setImageIcon(cyanShip2);
                    break;
                case 3:
                    shipLab.setIcon(pinkShip);
                    Image m = pinkShip.getImage().getScaledInstance(100, 90, 100);
                    pinkShip.setImage(m);
                    menu.livesDrawingUpdate(pinkShip2);
                    menu.setImageIcon(pinkShip2);
                    break;
                case 4:
                    shipLab.setIcon(redShip);
                    Image n = redShip.getImage().getScaledInstance(100, 90, 100);
                    redShip.setImage(n);
                    menu.livesDrawingUpdate(redShip2);
                    menu.setImageIcon(redShip2);
                    break;               
            }
        }
        
        if(keyCode == KeyEvent.VK_LEFT){
            
            if(shipNumber > 1){
                shipNumber--; //scrolling down
            }
            else
                shipNumber = 4;
            
            menu.setShipNumber(shipNumber);
            
            /*
                Different ships scrolled and painted
            */
            switch(shipNumber){
                case 1: 
                    shipLab.setIcon(greenShip);
                    Image j = greenShip.getImage().getScaledInstance(100, 90, 100);
                    greenShip.setImage(j);
                    menu.livesDrawingUpdate(greenShip2);
                    menu.setImageIcon(greenShip2);
                    break;
                case 2:
                    shipLab.setIcon(cyanShip);
                    Image l = cyanShip.getImage().getScaledInstance(100, 90, 100);
                    cyanShip.setImage(l);
                    menu.livesDrawingUpdate(cyanShip2);
                    menu.setImageIcon(cyanShip2);
                    break;
                case 3:
                    shipLab.setIcon(pinkShip);
                    Image m = pinkShip.getImage().getScaledInstance(100, 90, 100);
                    pinkShip.setImage(m);
                    menu.livesDrawingUpdate(pinkShip2);
                    menu.setImageIcon(pinkShip2);
                    break;
                case 4:
                    shipLab.setIcon(redShip);
                    Image n = redShip.getImage().getScaledInstance(100, 90, 100);
                    redShip.setImage(n);
                    menu.livesDrawingUpdate(redShip2);
                    menu.setImageIcon(redShip2);
                    break;               
            }
        }
        
        //starting the level
        if(keyCode == KeyEvent.VK_SPACE){
            fL = new FirstLevel("Space Invaders - Game", menu, menu.isMute());
            menu.setMute(true);
            menu.music();
            fL.setShipNumber(shipNumber);
            
            menu.dispose();    
        }
        
        //creating info view
        if(keyCode == KeyEvent.VK_H){
            
            iM = new InfoMenu("Space Invaders - Info");
            iM.setVisible(true);   
        }
        
        menu.repaint();
        menu.revalidate();
    } 
}