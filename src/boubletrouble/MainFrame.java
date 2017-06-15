package boubletrouble;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends javax.swing.JFrame{
    private final static int frameHeight = 600;
    private final static int frameWidth = 600;
    MainPanel panel;
        
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
        setJMenuBar(initJMenuBar());
        panel = new MainPanel();
        add(panel);
        
        panel.requestFocus();
        panel.newGame();
        pack();
    }
    private JMenuBar initJMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu Files = new JMenu("Files");
        
        JMenuItem newGame = new JMenuItem("New game");
        JMenuItem exit = new JMenuItem("Exit");
        
        JMenuItem highScore = new JMenuItem("High Score");
        
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(panel.inGame)
               {   panel.timer.stop();
                int again = javax.swing.JOptionPane.showConfirmDialog(null, 
                        "Are you sure you want to start new game?",
                        "Question?", javax.swing.JOptionPane.YES_NO_OPTION, 
                                            javax.swing.JOptionPane.WARNING_MESSAGE);
                if(again == javax.swing.JOptionPane.YES_OPTION) 
                    panel.newGame();
                else
                    panel.timer.start();
                }
               else
                   panel.newGame();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             panel.timer.stop();
                int again;
                again = javax.swing.JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
                                            "Question?", javax.swing.JOptionPane.YES_NO_OPTION, 
                                            javax.swing.JOptionPane.WARNING_MESSAGE);
                if(again == javax.swing.JOptionPane.YES_OPTION)
                    System.exit(0);
                else
                    panel.timer.start();
            }        
        });
        
        highScore.addMouseListener(new MouseAdapter() {
              @Override
            public void mouseClicked(MouseEvent e) {
                  new HighScore(panel);
            }
        });
        
        Files.add(newGame);
        Files.add(exit);
        menuBar.add(Files);
        menuBar.add(highScore);
        
        return menuBar;
    }
}
