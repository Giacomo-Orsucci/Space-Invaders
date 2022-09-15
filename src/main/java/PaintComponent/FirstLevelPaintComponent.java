package PaintComponent;

import java.awt.*;
import javax.swing.*;

/**
 * Class for firstLevel paint component and his white lines (JPanel)
 * @author Giacomo Orsucci & Riccardo Pacini 4IC
 */
public class FirstLevelPaintComponent extends JPanel {
    
    private int width;
    private int height;

    /**
     * Parameterized panel
     * @param width, panel width
     * @param height, panel height 
     */
    public FirstLevelPaintComponent(int width, int height) {
        
        this.width = width;
        this.height = height;
    }
    
    /**
     * To draw in the panel
     * @param g, graphic object 
     */
    @Override
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(4));
        g2.drawLine(20,height-100,width-30,height-100);
    }
}