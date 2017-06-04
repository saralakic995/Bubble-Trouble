package boubletrouble;

import java.awt.Dimension;
import java.awt.HeadlessException;

public class MainFrame extends javax.swing.JFrame{
    private final static int frameHeight = 600;
    private final static int frameWidth = 600;

    public static int getFrameHeight() {
        return frameHeight;
    }

    public static int getFrameWidth() {
        return frameWidth;
    }
    
    public MainFrame() throws HeadlessException {
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setResizable(false);                        //da se ne moze mjenjati velicina  
        setVisible(true);                           //da je vidljivo 
        setDefaultCloseOperation(EXIT_ON_CLOSE);    // da se na X gasi
        setLocation(0, 0);
        MainPanel panel = new MainPanel();
        add(panel);
        
        panel.requestFocus();
        panel.newGame();
        pack();
    }
    
        
}
