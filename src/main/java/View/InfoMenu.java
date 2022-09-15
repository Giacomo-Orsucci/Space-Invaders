package View;

import Controller.InfoMenuController;
import PaintComponent.InfoPaintComponent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class for info view
 * @author Giacomo Orsucci & Riccardo Pacini 4IC
 */
public class InfoMenu extends JFrame{
   
    private JLabel tit, shot, points1, points2, points3, alienLab1, alienLab2, alienLab3, mute, back, movement1, movement2;
    
    private JPanel rootPanel;
    private JPanel mainPanel;
    
    private Font f1, f2, f3;
    
    private Toolkit toolKit = Toolkit.getDefaultToolkit();
    
    private ImageIcon alienType0 = new ImageIcon(toolKit.getImage("src/Sprite/alien1_0.png"));
    private ImageIcon alienType1 = new ImageIcon(toolKit.getImage("src/Sprite/alien2_0.png"));
    private ImageIcon alienType2 = new ImageIcon(toolKit.getImage("src/Sprite/alien3_0.png"));
    
    private InfoMenuController iMC = new InfoMenuController(this);

    /**
     * Parameterized constructor
     * @param title, title 
     */
    public InfoMenu(String title){
        super(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(550,150,650,700);
        
        //initializing panels
        mainPanel = new InfoPaintComponent();
        rootPanel = (JPanel) getContentPane();
        
        //setting panels
        rootPanel.setBackground(Color.black);
        
        mainPanel.setLayout(null);
        mainPanel.setOpaque(false);
        
        //setting fonts
        f1 = new Font("Courier", Font.BOLD, 25); // JLabels without back and tit
        f2 = new Font("Courier", Font.BOLD, 20); // back
        f3 = new Font("Courier", Font.BOLD, 35); // title
        
        //initializing labels
        tit = new JLabel("HOW TO PLAY");
        shot = new JLabel("SHOT = PUSH SPACE TWICE");
        points1 = new JLabel(" = 30 POINTS");
        points2 = new JLabel(" = 20 POINTS");
        points3 = new JLabel(" = 10 POINTS");
        mute = new JLabel("MUTE = PUSH M");
        movement1 = new JLabel("LEFT = LEFT ARROW");
        movement2 = new JLabel("RIGHT = RIGHT ARROW");
        back = new JLabel("PUSH B TO BACK TO MENU");
        Image j = alienType0.getImage().getScaledInstance(60, 50, 60);
        alienType0.setImage(j);
        alienLab1 = new JLabel(alienType0);
        Image j1 = alienType1.getImage().getScaledInstance(60, 50, 60);
        alienType1.setImage(j1);
        alienLab2 = new JLabel(alienType1);
        Image j2 = alienType2.getImage().getScaledInstance(60, 50, 60);
        alienType2.setImage(j2);
        alienLab3 = new JLabel(alienType2);
        
        //setting labels
        tit.setFont(f3);
        tit.setForeground(Color.WHITE);
        tit.setBounds(190,10,300,50);
        
        alienLab1.setBounds(170,340,60,50);
        alienLab2.setBounds(170,395,60,50);
        alienLab3.setBounds(170,450,60,50);
        
        mute.setFont(f1);
        mute.setForeground(Color.WHITE);
        mute.setBounds(160,90,200,50);
        
        shot.setFont(f1);
        shot.setForeground(Color.WHITE);
        shot.setBounds(160,155,450,50);
        
        movement1.setFont(f1);
        movement1.setForeground(Color.WHITE);
        movement1.setBounds(160,210,300,50);
        
        movement2.setFont(f1);
        movement2.setForeground(Color.WHITE);
        movement2.setBounds(160,265,300,50);
        
        points1.setFont(f1);
        points1.setForeground(Color.WHITE);
        points1.setBounds(240,340,200,50);
        
        points2.setFont(f1);
        points2.setForeground(Color.WHITE);
        points2.setBounds(240,395,200,50);
        
        points3.setFont(f1);
        points3.setForeground(Color.WHITE);
        points3.setBounds(240,450,200,50);
        
        back.setFont(f2);
        back.setForeground(Color.GREEN);
        back.setBounds(180,520,300,50);
        
        //adding the components to the panels and mainPanel to the frame
        mainPanel.add(tit);
        mainPanel.add(mute);
        mainPanel.add(shot);
        mainPanel.add(movement1);
        mainPanel.add(movement2);
        mainPanel.add(alienLab1);
        mainPanel.add(alienLab2);
        mainPanel.add(alienLab3);
        mainPanel.add(points1);
        mainPanel.add(points2);
        mainPanel.add(points3);
        mainPanel.add(back);
        
        rootPanel.add(mainPanel);
        addKeyListener(iMC);
    }
}
