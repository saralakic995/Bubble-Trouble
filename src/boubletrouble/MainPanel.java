package boubletrouble;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.Timer;

public class MainPanel extends javax.swing.JPanel implements ActionListener, KeyListener{
    private static int panelWidht = MainFrame.getFrameWidth()- 10;
    private static int panelHeight = MainFrame.getFrameHeight() - 30;
    public Timer timer = new Timer(100, this);
    private MainCharacter character;
    private ArrayList<ShootingLine> listOfShootingLines;
    private ArrayList<Ball> listOfBalls;
    
    private final int linesSpeed = 10;
    private final int ballReduction = 10;
    private int frameCounter = 0;
    private final int newBallPeriod = 100;
    
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
        
        addKeyListener(this);
                
        listOfBalls = new ArrayList<>();
        listOfShootingLines = new ArrayList<>();
           }

    public void newGame()
    {
        character = new MainCharacter(panelWidht/2);
        listOfBalls.clear();
        listOfShootingLines.clear();
        listOfBalls.add(new Ball(35, 5, 5, 40, 40));
        frameCounter = 0;
        timer.start();
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(character.getCharacterX(), character.getCharacterY(), 
                   character.getCharacterWidth(), character.getCharacterHeight());
        for(int i = 0; i < listOfBalls.size(); i++)
        {
            g.fillOval(listOfBalls.get(i).getBallX(), listOfBalls.get(i).getBallY(), 
                    (int) listOfBalls.get(i).getBallDiameter(), (int) listOfBalls.get(i).getBallDiameter());
        }
        for(int i = 0; i < listOfShootingLines.size(); i++)
        {
            g.fillRect(
            listOfShootingLines.get(i).getLineStartX(), listOfShootingLines.get(i).getLineEndPosition(), 
                    2 , listOfShootingLines.get(i).getLineStartY());
        }
    }
    
    private void checkCrash()
    {
        ArrayList<Integer> removeIndeses = new ArrayList<>();
        for(int ballCounter = 0; ballCounter < listOfBalls.size(); ballCounter++)
            if(listOfBalls.get(ballCounter).getBallOval().intersects(character.getCharacterRectangle()))
                gameOver();
        for(int lineCounter = 0; lineCounter < listOfShootingLines.size(); lineCounter++)
            for(int ballCounter = 0; ballCounter < listOfBalls.size(); ballCounter++)
            {    
                if(listOfBalls.get(ballCounter).getBallOval()
                            .intersects(listOfShootingLines.get(lineCounter).getLine()))
                {
                    listOfShootingLines.remove(lineCounter);
                    removeIndeses.add(ballCounter);
                    lineCounter--;
                    break;
                }
            }
        Ball[] newBalls = new Ball[3];
        for(int i = 0; i < removeIndeses.size(); i++)
        {
            newBalls[0] = listOfBalls.get(removeIndeses.get(i));
            newBalls[1] = new Ball(newBalls[0].getBallDiameter() - ballReduction,
                    newBalls[0].getxMovement() * (-1), newBalls[0].getyMovement(),
                    newBalls[0].getBallX(), newBalls[0].getBallY());
            newBalls[2] = new Ball(newBalls[0].getBallDiameter() - ballReduction,
                    newBalls[0].getxMovement(), newBalls[0].getyMovement(),
                    newBalls[0].getBallX(), newBalls[0].getBallY());
            listOfBalls.remove(newBalls[0]);
            listOfBalls.add(newBalls[1]);
            listOfBalls.add(newBalls[2]);
        }
    }
    private void clearBoard()
    {
        for(int i = 0; i < listOfShootingLines.size(); i++)
             if(listOfShootingLines.get(i).getLineEndPosition() < -20)
                 listOfShootingLines.remove(i);
        for(int i = 0; i < listOfBalls.size(); i++)
            if(listOfBalls.get(i).getBallDiameter() == 0)
                listOfBalls.remove(i);
    }
    
    public void gameOver()
    {
        timer.stop();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        frameCounter++;
        for(int i = 0; i < listOfShootingLines.size(); i++)
            listOfShootingLines.get(i).move();
        for(int i = 0; i < listOfBalls.size(); i++)
            listOfBalls.get(i).move();
        checkCrash();
        clearBoard();
        if(frameCounter%newBallPeriod == 0)
        {
            System.out.println("opai");
            listOfBalls.add(new Ball(35, 5, 5, 40, 40));
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
           if (e.getKeyCode() == KeyEvent.VK_LEFT)
            character.moveLeft();
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            character.moveRight();
        else if (e.getKeyCode() == KeyEvent.VK_SPACE)
            listOfShootingLines.add(
                    new ShootingLine(
                            character.getCharacterX() + character.getCharacterWidth() / 2,
                            panelHeight, linesSpeed));
        }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
