package Threads;

import Elements.Bullet;
import Elements.MysteriousAlien;
import View.FirstLevel;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/**
 * Class for ship bullet thread
 * @author Giacomo Orsucci & Riccardo Pacini 4IC
 */
public class ShipBulletThread extends Thread{
    
    private Bullet shipBullet;
    
    private Toolkit toolKit = Toolkit.getDefaultToolkit();
    
    private ImageIcon shipBulletImage;
    
    private ImageIcon alienDestroyedImage = new ImageIcon(toolKit.getImage("src/Sprite/alien_destroyed.png"));
    private ImageIcon mysteriousAlienImage = new ImageIcon(toolKit.getImage("src/Sprite/saucer.png"));
    private ImageIcon mysteriousAlienImageDestroyed = new ImageIcon(toolKit.getImage("src/Sprite/saucer_destroyed_0.png"));
    private ImageIcon mysteriousAlienImageScore = new ImageIcon(toolKit.getImage("src/Sprite/saucer_destroyed_1.png"));
    
    private Image image0,image1;
    
    private FirstLevel fL;
    
    private MysteriousAlien mA;
    
    private AlienThread[] alienArray;
    
    private static MysteriousAlienThread mAT;
    
    private boolean alive = true;
    
    /**
     * Parameterized constructor
     * @param shipBullet, bullet reference
     * @param shipBulletImage, image
     * @param fL, first level reference 
     */
    public ShipBulletThread(Bullet shipBullet, ImageIcon shipBulletImage, FirstLevel fL){
        
        this.shipBullet = shipBullet;
        this.shipBulletImage = shipBulletImage;
        this.fL = fL;
        alienArray = fL.getAlienArray();
        setPriority(MAX_PRIORITY);
        
        //inzializing mysterious alien
        mA = new MysteriousAlien(30, 60, 0, 50, fL.getMainPanel(), mysteriousAlienImage, fL.isMute());
        
        image0 = shipBulletImage.getImage().getScaledInstance(7,13,7);
        image1 = alienDestroyedImage.getImage().getScaledInstance(50,40,50);
        
        this.shipBulletImage.setImage(image0);
        this.alienDestroyedImage.setImage(image1);
    }

    /**
     * Thread running
     */
    @Override
    public void run() {
        
        long frameRate = 1000 / 60;
        long previousTimeAnimation = System.currentTimeMillis();
        
        while(alive){
            
            //alien thread refresh syncronization and speed
            long previousTime = System.currentTimeMillis();
            
            while (System.currentTimeMillis() - previousTime < frameRate) {
                try {
                    Thread.sleep(1,15);
                } catch (InterruptedException ex) {
                    System.out.println("Sleep error");
                }
            }
            
            shipBullet.setY(shipBullet.getY()-12);
            shipBullet.getRectangle().setBounds(shipBullet.getX(),shipBullet.getY(),shipBullet.getWidth(),shipBullet.getHeight());
            
            //when it reaches the top limit, it dies
            if(shipBullet.getY() <= fL.getRecord().getHeight()-5){
                shipBullet.setVisible(false);
                alive = false;   
            }
            
            //checking shields
            for(int i=0; i<fL.getShieldArray().length; i++){
                fL.getShieldArray()[i].shieldCheck(shipBullet.getRectangle(), this);
            }
            
            //checking collision between ship bullet and aliens
            for(int i=alienArray.length-1; i>=0; i--){
                if(alienArray[i].getAlien().getRectangle().intersects(shipBullet.getRectangle()) && alienArray[i].getAlive()==true){
                    
                    int supp = 0;

                    //counting remaining aliens
                    for(int j = 0; j<fL.getAlienArray().length; j++){
                        if(fL.getAlienArray()[j].getAlien().isVisible()){
                            supp++;
                        }
                    }
                    
                    //if there are 10 remaining aliens, the mysterious alien appears 
                    if(supp==10){
                        mAT = new MysteriousAlienThread(mA, fL);
                        mAT.start();
                    }
                    
                    //destroy the alien hit
                    alienArray[i].getAlien().alienExpSound();
                    alienArray[i].getAlien().setIcon(alienDestroyedImage);
                    alienArray[i].setAlienType0(alienDestroyedImage);
                    alienArray[i].setAlienType1(alienDestroyedImage);
                    try {
                        alienArray[i].sleep(100);
                    } catch (InterruptedException ex) {
                        System.out.println("Sleep error");
                    }
                    
                    //updating the score
                    fL.setTotScore(fL.getTotScore()+alienArray[i].getAlien().getScore());
                    
                    alienArray[i].getAlien().setVisible(false);
                    alienArray[i].setAlive(false);
                    
                    /*
                        if the player has won the level, check if it has beaten the record.
                        In that case, save it.
                    */
                    if(!fL.livingAlien()){
                        if(fL.getTotScore()>fL.getMenu().getRecord()){
                            fL.recordWriter();
                        }
                    }
                    
                    shipBullet.setVisible(false);
                    shipBullet = null;
                    alive = false;
                    break;
                }
            }
            
            //if the mysterious alien is alive, check the collision
            if(mAT != null && alive == true  && mAT.getAlive()){
                
                if(mAT.getmA().getRectangle().intersects(shipBullet.getRectangle()) && mAT.getAlive()){
                    
                    mAT.getmA().setRectangle(new Rectangle(1,2,1,1));
                    
                    mAT.getmA().mAExpSound();
                    
                    mAT.getmA().setImage(mysteriousAlienImageDestroyed);
                    
                    mAT.setnPixelMovement(0);
                    
                    try {
                        mAT.sleep(180);
                    } catch (InterruptedException ex) {
                        System.out.println("Sleep error");
                    }
                    
                    mAT.getmA().setImage(mysteriousAlienImageScore);
                    
                    try {
                        mAT.sleep(100);
                    } catch (InterruptedException ex) {
                        System.out.println("Sleep error");
                    }
                    
                    fL.setTotScore(fL.getTotScore()+mAT.getmA().getScore());
                    
                    mAT.getmA().setMute(true);
                    mAT.getmA().movingSound();
                    mAT.getmA().setVisible(false);
                    mAT.setAlive(false);
                                       
                    shipBullet.setVisible(false);
                    shipBullet = null;
                    alive = false;
                } 
            }
            
            if(alive){
                shipBullet.setBounds(shipBullet.getX(),shipBullet.getY(),shipBullet.getWidth(),shipBullet.getHeight());
            }
        }
    }
    //GETTER E SETTER
    public Bullet getShipBullet() {return shipBullet;}
    public void setAlive(boolean alive) {this.alive = alive;} 
}