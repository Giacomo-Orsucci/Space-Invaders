package Elements;

import PaintComponent.FirstLevelPaintComponent;
import Threads.AlienBulletThread;
import Threads.ShipBulletThread;
import View.FirstLevel;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/**
 * Class for entire shield element
 * @author Giacomo Orsucci & Riccardo Pacini 4IC
 */
public class EntireShield {
    
    private Toolkit toolKit = Toolkit.getDefaultToolkit();
    
    private ImageIcon shield_0 = new ImageIcon(toolKit.getImage("src/Sprite/shield_0_3.png"));
    private ImageIcon shield_1 = new ImageIcon(toolKit.getImage("src/Sprite/shield_1_0.png"));
    private ImageIcon shield_2 = new ImageIcon(toolKit.getImage("src/Sprite/shield_2_3.png"));
    private ImageIcon shield_3 = new ImageIcon(toolKit.getImage("src/Sprite/shield_0_0.png"));
    private ImageIcon shield_4 = new ImageIcon(toolKit.getImage("src/Sprite/shield_2_0.png"));
    private ImageIcon shield_0_destroyed = new ImageIcon(toolKit.getImage("src/Sprite/shield_0_3_destroyed.png"));
    private ImageIcon shield_1_destroyed = new ImageIcon(toolKit.getImage("src/Sprite/shield_1_0_destroyed.png"));
    private ImageIcon shield_2_destroyed = new ImageIcon(toolKit.getImage("src/Sprite/shield_2_3_destroyed.png"));
    private ImageIcon shield_3_destroyed = new ImageIcon(toolKit.getImage("src/Sprite/shield_0_0_destroyed.png"));
    private ImageIcon shield_4_destroyed = new ImageIcon(toolKit.getImage("src/Sprite/shield_2_0_destroyed.png"));
    
    private Shield[] entireShield = new Shield[8];
    private Shield[] destroyedShield = new Shield[8];
    
    /**
     * parameterized constructor
     * @param height, shield height
     * @param width, shield width
     * @param x, shield x position
     * @param y, shield y position
     * @param fL, level reference
     * @param mainPanel, shield panel 
     */
    public EntireShield(int height, int width, int x, int y, FirstLevel fL, FirstLevelPaintComponent mainPanel){
        
        /*
            Constructing the array with different shield pieces and the array
            with different destroyed shield pieces
        */
        entireShield[0] = new Shield(height, width, x, y, mainPanel, shield_0);
        entireShield[1] = new Shield(height, width, x, y-20, mainPanel, shield_1);
        entireShield[2] = new Shield(height, width, x+30, y-20, mainPanel, shield_1);
        entireShield[3] = new Shield(height, width, x+60, y-20, mainPanel, shield_1);
        entireShield[4] = new Shield(height, width, x+30, y-40, mainPanel, shield_1);
        entireShield[5] = new Shield(height, width, x+60, y, mainPanel, shield_2);
        entireShield[6] = new Shield(height, width, x, y-40, mainPanel, shield_3);
        entireShield[7] = new Shield(height, width, x+60, y-40, mainPanel, shield_4);
        
        destroyedShield[0] = new Shield(height, width, x, y, mainPanel, shield_0_destroyed, null);
        destroyedShield[1] = new Shield(height, width, x, y-20, mainPanel, shield_1_destroyed, null);
        destroyedShield[2] = new Shield(height, width, x+30, y-20, mainPanel, shield_1_destroyed, null);
        destroyedShield[3] = new Shield(height, width, x+60, y-20, mainPanel, shield_1_destroyed, null);
        destroyedShield[4] = new Shield(height, width, x+30, y-40, mainPanel, shield_1_destroyed, null);
        destroyedShield[5] = new Shield(height, width, x+60, y, mainPanel, shield_2_destroyed, null);
        destroyedShield[6] = new Shield(height, width, x, y-40, mainPanel, shield_3_destroyed, null);
        destroyedShield[7] = new Shield(height, width, x+60, y-40, mainPanel, shield_4_destroyed, null);
    }
    
    /**
     * Method to check shield integrity by an alien bullet
     * @param alienBulletRect, alien bullet hitbox
     * @param aBT, alien bullet thread that does the check 
     */
    public void shieldCheck(Rectangle alienBulletRect, AlienBulletThread aBT){
        /*
            If the bullet hit a part of the shield, it is changed
            with his destroyed piece or it is completely destroyed.
            The second case happens when one block is hit the second time.
        */
        for(int i=0; i<entireShield.length; i++){
            if(entireShield[i].getHitBox().intersects(alienBulletRect)){
                aBT.getAlienBullet().setVisible(false);
                aBT.setAlive(false);
                if(entireShield[i].isNotHitYet() == true){
                   destroyedShield[i].setHitBox(entireShield[i].getHitBox());
                   entireShield[i].setVisible(false);
                   entireShield[i] = destroyedShield[i];
                   entireShield[i].setVisible(true);
                }
                else{
                    entireShield[i].setVisible(false);
                    entireShield[i].setHitBox(new Rectangle(10,0,10,10));
                }
                entireShield[i].setNotHitYet(false);
            } 
        }
    }
    
    /**
     * Method to check shield integrity by a ship bullet
     * @param shipBulletRect, ship bullet hitbox
     * @param sBT, ship bullet thread that does the check 
     */
    public void shieldCheck(Rectangle shipBulletRect, ShipBulletThread sBT){
        /*
            If the bullet hit a part of the shield, it is changed
            with his destroyed piece or it is completely destroyed.
            The second case happens when one block is hit the second time.
        */
        for(int i=0; i<entireShield.length; i++){
            if(entireShield[i].getHitBox().intersects(shipBulletRect)){
                sBT.getShipBullet().setVisible(false);
                sBT.setAlive(false);
                if(entireShield[i].isNotHitYet() == true){
                   destroyedShield[i].setHitBox(entireShield[i].getHitBox());
                   entireShield[i].setVisible(false);
                   entireShield[i] = destroyedShield[i];
                   entireShield[i].setVisible(true);
                }
                else{
                    entireShield[i].setVisible(false);
                    entireShield[i].setHitBox(new Rectangle(10,0,10,10));
                }
                entireShield[i].setNotHitYet(false);
            } 
        }
    } 
}