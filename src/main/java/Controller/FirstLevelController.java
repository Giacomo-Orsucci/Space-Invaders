package Controller;

import Elements.Ship;
import Elements.Bullet;
import Threads.ShipBulletThread;
import View.FirstLevel;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;

/**
 * Class for level Keylistener
 * @author Giacomo Orsucci & Riccardo Pacini 4IC
 */
public class FirstLevelController implements KeyListener {
    
    private int keyCode;
    private int supp = 0;
   
    private Ship ship;
    
    private Bullet sB;
    
    private ShipBulletThread sBT;
    
    private FirstLevel fL;
    
    private boolean canMove = true;
    
    private Toolkit toolKit = Toolkit.getDefaultToolkit();
    
    private ImageIcon bulletImage = new ImageIcon(toolKit.getImage("src/Sprite/ship_shot.png"));
    
    /**
     * parameterized constructor
     * @param ship, ship reference
     * @param fL, level reference
     */
    public FirstLevelController(Ship ship, FirstLevel fL){
        
        this.ship = ship;
        this.fL = fL;
    }
    
    /**
     * Empty
     * @param e, event origin 
     */
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
    /**
     * Method for action following key pressing
     * @param e, event origin
     */
    @Override
    public void keyPressed(KeyEvent e) {
        
        keyCode = e.getKeyCode(); //we get the event origin
        
        //if the ship can move
        if(canMove){
            if(keyCode == KeyEvent.VK_RIGHT){ //right arrow pressed

                ship.movement(1, true); //move the ship right
            }
            if(keyCode == KeyEvent.VK_LEFT){ //left arrow pressed
                
                ship.movement(-1, true); //move the ship left
            }
        }
    }

    /**
     * Method for action following key releasing
     * @param e, event origin 
     */
    @Override
    public void keyReleased(KeyEvent e) {
        keyCode = e.getKeyCode(); //we get the event origin
        
        //if the ship can move
        if(ship.isShooting()){
            if(keyCode == KeyEvent.VK_SPACE){ //space pressed
                if(supp == 1){ //to resolve the shooting loop
                    //creation and addition of ship bullet to the level and creation of ship bullet thread
                    Rectangle rectangle = new Rectangle((ship.getX()+ship.getWidth()/2)-5,ship.getY()-13,10,20);
                    sB = new Bullet(20, 10,(ship.getX()+ship.getWidth()/2)-5, ship.getY()-13, fL.getMainPanel(), bulletImage, rectangle);
                    fL.getMainPanel().add(sB);

                    sBT = new ShipBulletThread(sB, bulletImage, fL);

                    sBT.start();
                    fL.getShip().shotSound();
                    supp = 0;
                }
                else{
                    supp++;
                }
            }
        }
        if(keyCode == KeyEvent.VK_RIGHT){
            
            ship.movement(1, false);  
        }
        if(keyCode == KeyEvent.VK_LEFT){
            
            ship.movement(-1, false);
        }   
    }
    
    //GETTER E SETTER
    public boolean isCanMove(){return canMove;}
    public void setCanMove(boolean canMove){this.canMove = canMove;}
}