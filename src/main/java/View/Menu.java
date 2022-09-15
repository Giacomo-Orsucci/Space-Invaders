package View;

import javax.sound.sampled.*;
import Controller.MenuController;
import PaintComponent.MenuPaintComponent;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * Class for menu view
 * @author Giacomo Orsucci & Riccardo Pacini 4IC
 */
public class Menu extends JFrame{
   
    private static final int LIVESNUMBER = 3;
    
    private int shipNumber = 1;
    private int record;
    
    private JPanel changeShip, rootPanel;
    private MenuPaintComponent mainPanel;
   
    private JLabel gameTitle, start, left, right, shipLab, livesLab, recordLab, soundLab;
    private JLabel livesArray[] = new JLabel[LIVESNUMBER];
    
    private Font f1, f2, f3, f4;
    
    private Toolkit toolKit = Toolkit.getDefaultToolkit();
    
    private ImageIcon  greenShip1 = new ImageIcon(toolKit.getImage("src/Sprite/green_ship.png"));
    private ImageIcon  greenShip2 = new ImageIcon(toolKit.getImage("src/Sprite/green_ship.png"));
    private ImageIcon  soundImage = new ImageIcon(toolKit.getImage("src/Sprite/sound_img.png"));
    
    private MenuController menuKeyListener;
    
    private boolean mute = false;
    private boolean firstMusic = true;
    
    private Clip ol;
    private Clip lastOL;
    
    private FileReader fr;
    private BufferedReader fIN;
    
    private JLabel htp; // label How to play
    
    /**
     * Parameterized constructor
     * @param title 
     */
    public Menu(String title){
        
        //setting the frame
        super(title);
        music();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(550,150,650,700);
        setVisible(true);
        
        //initializing panels
        mainPanel = new MenuPaintComponent();
        rootPanel = (JPanel) getContentPane();
        changeShip = new JPanel();
        
        //setting panels
        rootPanel.setBackground(Color.black);
        
        mainPanel.setLayout(null);
        mainPanel.setOpaque(false);
        
        changeShip.setBackground(Color.BLACK);
        changeShip.setBounds(170,420,300,150);
        changeShip.setLayout(null);
        
        //setting fonts
        f1 = new Font("Courier", Font.BOLD, 35); // gameTitle
        f2 = new Font("Courier", Font.BOLD, 25); // start
        f3 = new Font("Courier", Font.BOLD, 70); // arrows to select the color of the ship 
        f4 = new Font("Courier", Font.BOLD, 20); // how to play
        
        //menu listeners
        menuKeyListener = new MenuController(this);
        
        //initializing labels
        recordLab = new JLabel("RECORD: " + record);
        gameTitle = new JLabel("S P A C E   I N V A D E R S");
        start = new JLabel("PUSH SPACE TO PLAY");
        left = new JLabel("<");
        right = new JLabel(">");
        htp = new JLabel("HOW TO PLAY (H)");
        Image j = greenShip1.getImage().getScaledInstance(100, 90, 100);
        greenShip1.setImage(j);
        shipLab = new JLabel(greenShip1);
        livesLab = new JLabel("LIVES:");
        
        Image k = soundImage.getImage().getScaledInstance(30, 30, 30);
        soundImage.setImage(k);
        soundLab = new JLabel(soundImage);
        
        //setting labels
        recordLab.setFont(f2);
        recordLab.setForeground(Color.WHITE);
        recordLab.setBounds(20,0,250,50);
        
        gameTitle.setFont(f1);
        gameTitle.setForeground(Color.WHITE);
        gameTitle.setBounds(30,140,600,50);
        
        start.setFont(f2);
        start.setForeground(Color.WHITE);
        start.setBounds(180,350,350,50);
        
        left.setFont(f3);
        left.setForeground(Color.WHITE);
        left.setBounds(2,45,50,50);
        
        right.setFont(f3);
        right.setForeground(Color.WHITE);
        right.setBounds(255,45,50,50);
        
        shipLab.setBounds(97,30,100,60);
        shipLab.setOpaque(true);
        
        livesLab.setFont(f2);
        livesLab.setForeground(Color.WHITE);
        livesLab.setBounds(20,580,150,100);
        
        soundLab.setBounds(570,60,30,30);
        soundLab.setBackground(Color.red);
        soundLab.setOpaque(true);
        
        htp.setFont(f4);
        htp.setForeground(Color.WHITE);
        htp.setBounds(230,280,180,30);
        
        //adding the components to the panels and mainPanel to the frame
        mainPanel.add(recordLab);
        mainPanel.add(gameTitle);
        mainPanel.add(start);
        mainPanel.add(changeShip);
        mainPanel.add(livesLab);
        mainPanel.add(soundLab);
        mainPanel.add(htp);
        
        changeShip.add(left);
        changeShip.add(right);
        changeShip.add(shipLab);
        
        rootPanel.add(mainPanel);
        
        this.addKeyListener(menuKeyListener);
        recordReader();
        livesDrawing(greenShip2);
        flashingLoop(start);
    }
   
    /**
     * Method to draw lives in the menu
     * @param ship, ship reference 
     */
    public void livesDrawing(ImageIcon ship){
        
        int x = 130;
        int y = 610;
        int xVar = 0;
        
        for(int i=0; i < LIVESNUMBER; i++){
            
            JLabel livesPanel = new JLabel();
            livesArray[i] = livesPanel;
            livesPanel.setIcon(ship);
            Image g = ship.getImage().getScaledInstance(50,40,50);
            ship.setImage(g);
            livesPanel.setBounds((x+xVar), y, 50, 30);
            xVar += 60;
            mainPanel.add(livesPanel);
            repaint();
        }   
    }
    
    /**
     * Method to draw lives when ships are scrolled
     * @param ship, ship reference 
     */
    public void livesDrawingUpdate(ImageIcon ship){
       
        int x = 130;
        int y = 610;
        int xVar = 0;
        
        for(int i=0; i < LIVESNUMBER; i++){
            
            livesArray[i].setIcon(ship);
            Image g = ship.getImage().getScaledInstance(50,40,50);
            ship.setImage(g);
            livesArray[i].setBounds((x+xVar), y, 50, 30);
            xVar += 60;
            mainPanel.add(livesArray[i]);
            repaint();
        }      
    }
    
    /**
     * Method to do the flashing effect
     * @param text, text to flash 
     */
    public void flashingLoop(JLabel text){
        
        int i=1;
        
        for(;;){ //infinite loop
            if(i>4){
                i=0;
            }
            if(text.isVisible()){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                text.setVisible(false);
            }
            else{
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                } 
                text.setVisible(true); 
                
                switch(i){
                    case 0:
                        start.setForeground(Color.white);
                        break;
                    case 1:
                        start.setForeground(Color.green);
                        break;
                    case 2:
                        start.setForeground(Color.cyan);
                        break;
                    case 3:
                        start.setForeground(Color.magenta);
                        break;
                    case 4:
                        start.setForeground(Color.red);
                        break;
                }
                i++;
            }
        }
    }
    
    /**
     * Method to play the menu music
     */
    public void music(){
        
        File sf = new File("src/Sound/Menu.wav");
        AudioFileFormat aff;
        AudioInputStream ais;
        
        try
        {
            aff = AudioSystem.getAudioFileFormat(sf);
            ais = AudioSystem.getAudioInputStream(sf);

            AudioFormat af = aff.getFormat();

            DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat(), ((int) ais.getFrameLength() * af.getFrameSize()));

            ol = (Clip) AudioSystem.getLine(info);

            ol.open(ais);
            
            if(firstMusic == true){
                if(mute == false){
                    ol.loop(Clip.LOOP_CONTINUOUSLY);
                }
                lastOL = ol;
            }
            if(firstMusic == false){
                if(mute == false){
                    lastOL.stop();
                    ol.loop(Clip.LOOP_CONTINUOUSLY);   
                }
                if(mute == true){
                    lastOL.stop();
                }
                lastOL = ol;
            }
            firstMusic = false;
        }
        catch(UnsupportedAudioFileException ee){
            System.out.println("File audio not supported");
        }
        catch(IOException ea){
            System.out.println("Audio error");
        }
        catch(LineUnavailableException LUE){
            System.out.println("Audio error");
        }
    }
    
    /**
     * Method to read the saved record
     */
    public void recordReader(){
        // opening file
        try{
            fr = new FileReader("Record.txt");
            fIN = new BufferedReader(fr);
        }catch(Exception ex){
            System.out.println("Opening file error");
        }
        String s;
        try{
            s = fIN.readLine();
            recordLab.setText("Record: "+ s);
            record =Integer.parseInt(s);
            fr.close(); // closing file
        }catch(IOException ex){
            System.out.println("Reading file error");
        }   
    }
    
    //GETTER AND SETTER
    public static int getLIVESNUMBER(){return LIVESNUMBER;}
    public int getShipNumber(){return shipNumber;}
    public void setShipNumber(int shipNumber){this.shipNumber = shipNumber;}
    public JLabel getShipLab(){return shipLab;}
    public void setShipLab(JLabel shipLab){this.shipLab = shipLab;} 
    public ImageIcon getImageIcon(){return greenShip1;}
    public void setImageIcon(ImageIcon greenShip1){this.greenShip1 = greenShip1;}
    public void setMute(boolean mute){this.mute = mute;}
    public boolean isMute(){return mute;}
    public JLabel getSoundLab(){return soundLab;}
    public int getRecord(){return record;}
    public void setRecord(int record){this.record = record;}
}