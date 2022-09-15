package Elements;

import PaintComponent.FirstLevelPaintComponent;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Class for shield part (JLabel)
 * @author Giacomo Orsucci & Riccardo Pacini 4IC
 */
public class Shield extends JLabel {
    
    private ImageIcon shieldImage;
    private ImageIcon shieldDestroyed;
    
    private FirstLevelPaintComponent mainPanel;
    
    private int height;
    private int width;
    
    private int x;
    private int y;
    
    private boolean notHitYet = true;
    
    private Rectangle hitBox;
    
    /**
     * Parameterized constructor for non damaged shield part
     * @param height, shield part height
     * @param width, shield part width
     * @param x, shield part x position
     * @param y, shield part y position
     * @param mainPanel, shield part panel
     * @param shieldImage, shield part image 
     */
    public Shield(int height, int width, int x, int y, FirstLevelPaintComponent mainPanel, ImageIcon shieldImage){
        
        this.shieldImage = shieldImage;
        this.mainPanel = mainPanel;
        
        hitBox = new Rectangle(x, y, width, height);
        
        Image j = shieldImage.getImage().getScaledInstance(30,20,30);
        shieldImage.setImage(j);
        setIcon(shieldImage);
        
        setVisible(true);
        
        setBounds(x, y, width, height);
        mainPanel.add(this);  
    }
    
    /**
     * Second parameterized constructor for damaged shield part
     * @param height, shield part height
     * @param width, shield part width
     * @param x, shield part x position
     * @param y, shield part y position
     * @param mainPanel, shield part panel
     * @param shieldDestroyed, destroyed shield part image
     * @param hitBox, destroyed shield part hitbox 
     */
    public Shield(int height, int width, int x, int y, FirstLevelPaintComponent mainPanel, ImageIcon shieldDestroyed, Rectangle hitBox){
        
        this.shieldDestroyed = shieldDestroyed;
        this.mainPanel = mainPanel;
        
        this.hitBox = hitBox;
        
        Image l = shieldDestroyed.getImage().getScaledInstance(30,20,30);
        shieldDestroyed.setImage(l);
        setIcon(shieldDestroyed);
        
        setVisible(true);
        
        setBounds(x, y, width, height);
        mainPanel.add(this);  
    }
    
    //GETTER E SETTER   
    public Rectangle getHitBox(){return hitBox;}
    public void setHitBox(Rectangle hitBox){this.hitBox = hitBox;}
    public boolean isNotHitYet() {return notHitYet;}
    public void setNotHitYet(boolean notHitYet) {this.notHitYet = notHitYet;}
}
