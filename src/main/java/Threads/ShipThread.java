package Threads;

import Elements.Ship;
import View.FirstLevel;
import java.awt.Rectangle;

/**
 * Class for ship thread
 * @author Giacomo Orsucci & Riccardo Pacini 4IC
 */
public class ShipThread extends Thread {
    
    private Ship ship;
    
    private boolean invicible = false;
    private boolean alive = true;
    
    private FirstLevel fL;
    
    /**
     * Parameterized constructor
     * @param ship, ship reference
     */
    public ShipThread(Ship ship, FirstLevel fL){
        
        this.ship = ship;
        this.fL = fL;
        setPriority(MAX_PRIORITY);
    }
    
    /**
     * Thread running
     */
    @Override
    public void run(){
        long frameRate = 1000 / 100;
        
        //to decide when starting to check for the creation of next level
        long previousTimeNextLevel = System.currentTimeMillis(); 
        
        while (alive) {
            
            //alien thread refresh syncronization and speed
            long previousTime = System.currentTimeMillis();
           
            ship.getPanel().repaint();//only it repaints the panel

            while (System.currentTimeMillis() - previousTime < frameRate) {
                try {
                    Thread.sleep(1,15);
                } catch (InterruptedException ex) {
                    System.out.println("Sleep error");
                }
            }

            //ship movement limits
            if(ship.isMoving()){
                if(ship.getX()>=0){
                ship.setX(ship.getX()+10*ship.getDirection()); 
                }
                if(ship.getX()+ship.getWidth() >= ship.getRightLimit()-16){
                    ship.setX(ship.getRightLimit()-ship.getWidth()-16);
                }
                if(ship.getX()<=0){
                    ship.setX(0);
                }
            }
            ship.setBounds(ship.getX(), ship.getY(), ship.getWidth(), ship.getHeight());
            
            if(invicible == false){
                ship.getRectangle().setBounds(ship.getX(), ship.getY(), ship.getWidth(), ship.getHeight());
            }
            
            if(invicible){ //if the ship is just respawned
                long previousTimeInvicible = System.currentTimeMillis();
                long app;
                Rectangle r = ship.getRectangle();
                ship.getRectangle().setBounds(10,10,10,10);
                
                while(invicible){ // invicible ship that can move for 5 sec
                    
                    previousTime = System.currentTimeMillis();
                    ship.getPanel().repaint();

                    while (System.currentTimeMillis() - previousTime < frameRate) {
                        try {
                            Thread.sleep(1,15);
                        } catch (InterruptedException ex) {
                            System.out.println("Sleep error");
                        }
                    }
                    
                    if(ship.isMoving()){
                        if(ship.getX()>=0){
                            ship.setX(ship.getX()+10*ship.getDirection()); 
                        }
                        if(ship.getX()+ship.getWidth() >= ship.getRightLimit()-16){
                            ship.setX(ship.getRightLimit()-ship.getWidth()-16);
                        }
                        if(ship.getX()<=0){
                            ship.setX(0);
                        }
                    }
                    ship.setBounds(ship.getX(), ship.getY(), ship.getWidth(), ship.getHeight());
                    
                    app = System.currentTimeMillis() - previousTimeInvicible;
                    
                    if(app >= 5000){
                        ship.setRectangle(r);  
                        previousTimeInvicible = System.currentTimeMillis();
                        invicible = false;
                    }
                } 
            }
            //passed this time it starts with alien living check
            if(System.currentTimeMillis() - previousTimeNextLevel >= 2000){
                if(!fL.livingAlien()){
                    if(fL.isDoNextLevel()) //new level
                        fL.newLevel();
                }
            }  
        }    
    }
    //GETTER E SETTER
    public boolean isInvicible() {return invicible;}
    public void setInvicible(boolean invicible) {this.invicible = invicible;}
    public void setAlive(boolean alive){this.alive = alive;}
}
    
   