package Elements;

import PaintComponent.FirstLevelPaintComponent;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Class for bullet element (JLabel)
 * @author Giacomo Orsucci & Riccardo Pacini 4IC
 */
public class Bullet extends JLabel{
    
    private int height;
    private int width;
    private int x;
    private int y;
    
    private FirstLevelPaintComponent mainPanel;
    
    private Rectangle rectangle;
    
    private ImageIcon bulletImage;
    
    /**
     * Parameterized constructor
     * @param height, bullet height
     * @param width, bullet width
     * @param x, bullet x position
     * @param y, bullet y position
     * @param mainPanel, bullet panel
     * @param bulletImage, bullet image
     * @param rectangle, bullet hitbox 
     */
    public Bullet(int height, int width, int x, int y, FirstLevelPaintComponent mainPanel, ImageIcon bulletImage, Rectangle rectangle){
        
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
        this.mainPanel = mainPanel;
        this.bulletImage = bulletImage;
        this.rectangle = rectangle;
        
        Image j = bulletImage.getImage().getScaledInstance(10,20,10);
        bulletImage.setImage(j);
        setIcon(bulletImage);
        setDoubleBuffered(true);
        
        setBounds(x, y, width, height);
    }
    
    //GETTER E SETTER
    @Override
    public int getY(){return y;}
    public void setY(int y){this.y = y;}
    public FirstLevelPaintComponent getPanel(){return mainPanel;}
    public void setPanel(FirstLevelPaintComponent panel){this.mainPanel = mainPanel;}
    public ImageIcon getBulletImage(){return bulletImage;}
    public void setBulletImage(ImageIcon bulletImage){this.bulletImage = bulletImage;}
    public Rectangle getRectangle(){return rectangle;}   
}