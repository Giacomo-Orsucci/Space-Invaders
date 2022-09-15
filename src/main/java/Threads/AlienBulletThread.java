package Threads;

import Elements.Bullet;
import Elements.Ship;
import View.FirstLevel;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 * Class for alien bullet thread
 * @author Giacomo Orsucci & Riccardo Pacini 4IC
 */
public class AlienBulletThread extends Thread{
    
    private int bulletAnimation = 0;
    
    private boolean doOnce = true;
    
    private Bullet alienBullet;
    
    private Ship ship;
    
    private Toolkit toolKit = Toolkit.getDefaultToolkit();
    
    private ImageIcon shipDestroyedImage0Green = new ImageIcon(toolKit.getImage("src/Sprite/ship_destroyed_0_green.png"));
    private ImageIcon shipDestroyedImage1Green = new ImageIcon(toolKit.getImage("src/Sprite/ship_destroyed_1_green.png"));
    private ImageIcon shipDestroyedImage0Red = new ImageIcon(toolKit.getImage("src/Sprite/ship_destroyed_0_red.png"));
    private ImageIcon shipDestroyedImage1Red = new ImageIcon(toolKit.getImage("src/Sprite/ship_destroyed_1_red.png"));
    private ImageIcon shipDestroyedImage0Cyan = new ImageIcon(toolKit.getImage("src/Sprite/ship_destroyed_0_cyan.png"));
    private ImageIcon shipDestroyedImage1Cyan = new ImageIcon(toolKit.getImage("src/Sprite/ship_destroyed_1_cyan.png"));
    private ImageIcon shipDestroyedImage0Pink = new ImageIcon(toolKit.getImage("src/Sprite/ship_destroyed_0_pink.png"));
    private ImageIcon shipDestroyedImage1Pink = new ImageIcon(toolKit.getImage("src/Sprite/ship_destroyed_1_pink.png"));
    private ImageIcon alienBulletImage0 = new ImageIcon(toolKit.getImage("src/Sprite/alien_shot0.png"));
    private ImageIcon alienBulletImage1 = new ImageIcon(toolKit.getImage("src/Sprite/alien_shot1.png"));
    
    private ImageIcon shipDestroyed0;
    private ImageIcon shipDestroyed1;
    
    private Image image0Green,image1Green,image0Red,image1Red,image0Cyan,image1Cyan,image0Pink,image1Pink;
    
    private boolean alive = true;
    
    private Random random = new Random();
    
    private FirstLevel fL;
    
    /**
     * Parameterized constructor
     * @param alienBullet, his bullet reference
     * @param fL, first level reference 
     */
    public AlienBulletThread (Bullet alienBullet, FirstLevel fL){
        
        this.alienBullet = alienBullet;
        this.fL = fL;
        
        ship = fL.getShip();
        
        image0Green = shipDestroyedImage0Green.getImage().getScaledInstance(50,40,50);
        image1Green = shipDestroyedImage1Green.getImage().getScaledInstance(50,40,50);
        image0Red = shipDestroyedImage0Red.getImage().getScaledInstance(50,40,50);
        image1Red = shipDestroyedImage1Red.getImage().getScaledInstance(50,40,50);
        image0Cyan = shipDestroyedImage0Cyan.getImage().getScaledInstance(50,40,50);
        image1Cyan = shipDestroyedImage1Cyan.getImage().getScaledInstance(50,40,50);
        image0Pink = shipDestroyedImage0Pink.getImage().getScaledInstance(50,40,50);
        image1Pink = shipDestroyedImage1Pink.getImage().getScaledInstance(50,40,50);
        
        this.shipDestroyedImage0Green.setImage(image0Green);
        this.shipDestroyedImage1Green.setImage(image1Green);
        this.shipDestroyedImage0Red.setImage(image0Red);
        this.shipDestroyedImage1Red.setImage(image1Red);
        this.shipDestroyedImage0Cyan.setImage(image0Cyan);
        this.shipDestroyedImage1Cyan.setImage(image1Cyan);
        this.shipDestroyedImage0Pink.setImage(image0Pink);
        this.shipDestroyedImage1Pink.setImage(image1Pink);
        
        /*
            getting the chosen ship
        */
        switch(fL.getShipNumber()){
            case 1:
                shipDestroyed0 = shipDestroyedImage0Green;
                shipDestroyed1 = shipDestroyedImage1Green;
                break;
            case 2:
                shipDestroyed0 = shipDestroyedImage0Cyan;
                shipDestroyed1 = shipDestroyedImage1Cyan;
                break;
            case 3:
                shipDestroyed0 = shipDestroyedImage0Pink;
                shipDestroyed1 = shipDestroyedImage1Pink;
                break;
            case 4:
                shipDestroyed0 = shipDestroyedImage0Red;
                shipDestroyed1 = shipDestroyedImage1Red;
                break;
        }  
    }
    
    /**
     * Thread running
     */
    
    @Override
    public void run() {
        
        long frameRate = 1000 / 60;
        long previousTimeAnimation = System.currentTimeMillis();
        
        while(alive){
            
            //alien bullet thread refresh syncronization and speed
            long previousTime = System.currentTimeMillis();
            
            while (System.currentTimeMillis() - previousTime < frameRate) {
                try {
                    Thread.sleep(1,15);
                } catch (InterruptedException ex) {
                    System.out.println("Sleep error");
                }
            }
            
            alienBullet.setY(alienBullet.getY()+3);
            alienBullet.getRectangle().setBounds(alienBullet.getX(),alienBullet.getY(),10,20);
            
            //animation, to change sprite
            if(System.currentTimeMillis() - previousTimeAnimation >= random.nextInt(200) && bulletAnimation == 0){
                
                Image j = alienBulletImage1.getImage().getScaledInstance(10,20,10);
                alienBulletImage1.setImage(j);
                alienBullet.setIcon(alienBulletImage1);
                previousTimeAnimation = System.currentTimeMillis();
                bulletAnimation = 1;
            }
            
            //animation, to change sprite
            if(System.currentTimeMillis() - previousTimeAnimation >= random.nextInt(200) && bulletAnimation == 1){
                
                Image j = alienBulletImage0.getImage().getScaledInstance(10,20,10);
                alienBulletImage0.setImage(j);
                alienBullet.setIcon(alienBulletImage0);
                previousTimeAnimation = System.currentTimeMillis();
                bulletAnimation = 0;
            }
            
            // once that the alien bullet has reached the down limit: visible = false, alienBulletThread alive = false
            if(alienBullet.getY()+alienBullet.getHeight() >= ship.getY()+ship.getHeight()){ 
                alienBullet.setVisible(false);
                alive = false;
            }
            
            //checking shields
            for(int i=0; i<fL.getShieldArray().length; i++){
                fL.getShieldArray()[i].shieldCheck(alienBullet.getRectangle(), this);
            }
            
            //when the ship is hit and the player has some remainnig lives
            if(fL.getRemainingLives()>0){
                if(alienBullet.getRectangle().intersects(ship.getRectangle())){
                    fL.getsT().setInvicible(true); // invincible during the animation
                    alienBullet.setVisible(false);
                    shipExplosion();
                    fL.livesCleanig();
                    fL.setRemainingLives(fL.getRemainingLives()-1);
                    fL.livesDrawing(fL.getShipIcon2());
                    fL.shipRelive();
                    alive = false;
                }
            }
            
            else{
                if(doOnce){ //create only one time the game over view
                    for(int i=fL.getAlienArray().length-1; i>=0; i--){
                        if(fL.getAlienArray()[i].getAlien().isVisible()){
                            // alien coordinates: if it reaches the down limit

                            fL.getAlienArray()[i].setGameOverCount(1);
                            fL.getAlienArray()[i].setAlive(false);

                            fL.getsT().setAlive(false);
                            fL.dispose();

                            for(int j=0; j<fL.getAlienArray().length; j++){
                                fL.getAlienArray()[j].setAlive(false);   
                            }
                            fL.createGameOver();
                            doOnce = false;
                            break; 
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Method for ship explosion 
     */
    private void shipExplosion(){
        fL.getShip().shipExpSound();
        fL.getFirstLevelController().setCanMove(false);
        ship.setShooting(false);
        for(int i=0; i<=3; i++){
            ship.setIcon(shipDestroyed0);
            try {
                sleep(150);
            } catch (InterruptedException ex) {
                Logger.getLogger(AlienBulletThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            ship.setIcon(shipDestroyed1);
            try {
                sleep(150);
            } catch (InterruptedException ex) {
                Logger.getLogger(AlienBulletThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ship.setVisible(false);
        ship.setShooting(true);
        fL.getFirstLevelController().setCanMove(true);
    }
    
    //GETTER E SETTER
    public Bullet getAlienBullet() {return alienBullet;}
    public void setAlive(boolean alive) {this.alive = alive;}
}