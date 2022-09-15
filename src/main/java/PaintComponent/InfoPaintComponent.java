package PaintComponent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * Class for info paint component and his white lines (JPanel) 
 * @author Giacomo Orsucci & Riccardo Pacini 4IC
 */
public class InfoPaintComponent extends JPanel{
    
    /**
     * To draw in the panel
     * @param g, graphic component 
     */
    @Override
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(4));
        g2.drawLine(20,600,610,600);
        g2.drawLine(20,60,610,60);
    }  
}
