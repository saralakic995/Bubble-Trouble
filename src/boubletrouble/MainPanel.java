package boubletrouble;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

public class MainPanel extends javax.swing.JPanel {
    private static int panelWidht = MainFrame.getFrameWidth()- 40;
    private static int panelHeight = MainFrame.getFrameHeight() - 30;
    private MainCharacter character;
    private ArrayList<ShootingLine> listOfShootingLines;
    private ArrayList<Ball> listOfBalls;
    
    public static int getPanelWidht() {
        return panelWidht;
    }

    public static int getPanelHeight() {
        return panelHeight;
    }
    
    public MainPanel() {
         setPreferredSize(new Dimension(panelWidht, panelHeight));
        setLayout(null);
        setBackground(Color.WHITE);
        setFocusable(true);
        setVisible(true);
        
        listOfBalls = new ArrayList<>();
        listOfShootingLines = new ArrayList<>();
        character = new MainCharacter(panelWidht);
        listOfBalls.add(new Ball(30, 3, 0, 100, 100));
        listOfShootingLines.add(new ShootingLine(panelWidht/2, panelHeight, 6));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(character.getCharacterX(), character.getCharacterY(), 
                   character.getCharacterWidth(), character.getCharacterHeight());
        for(int i = 0; i < listOfBalls.size(); i++)
        {
            g.fillOval(listOfBalls.get(i).getBallX(), listOfBalls.get(i).getBallY(), 
                    listOfBalls.get(i).getBallDiameter(), listOfBalls.get(i).getBallDiameter());
        }
        for(int i = 0; i < listOfShootingLines.size(); i++)
        {
            g.drawLine(listOfShootingLines.get(i).getLineStartX(), 
                       listOfShootingLines.get(i).getLineStartY(), 
                       listOfShootingLines.get(i).getLineStartX(), 
                       listOfShootingLines.get(i).getLineEndPosition());
        }
    }
    
    
}
