package boubletrouble;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class MainPanel extends javax.swing.JPanel implements ActionListener, KeyListener {

    private static int panelWidht = MainFrame.getFrameWidth() - 10;
    private static int panelHeight = MainFrame.getFrameHeight() - 50;
    public Timer timer = new Timer(60, this);
    private MainCharacter character;
    private ArrayList<ShootingLine> listOfShootingLines;
    private ArrayList<Ball> listOfBalls;
    private BufferedImage background = null;
    private boolean backgroundImageLoaded = true;
    private boolean characterImageLoaded = false;
    private boolean ballImageLoaded = false;
    public boolean inGame;
    public int inGamePoints;

    private final int linesSpeed = 10;
    private final int ballReduction = 10;
    private final int ballSpeedX = 5;
    private final int ballSpeedY = 7;
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
        loadImages();
        inGame = false;
        addKeyListener(this);

        listOfBalls = new ArrayList<>();
        listOfShootingLines = new ArrayList<>();
    }

    public void newGame() {
        character = new MainCharacter(panelWidht / 2);
        listOfBalls.clear();
        listOfShootingLines.clear();
        listOfBalls.add(new Ball(35, ballSpeedX, ballSpeedY, 40, 40));
        frameCounter = 0;
        inGamePoints = 0;
        timer.start();
        inGame = true;
    }

    private void checkCrash() {
        ArrayList<Integer> removeIndeses = new ArrayList<>();
        for (int ballCounter = 0; ballCounter < listOfBalls.size(); ballCounter++) {
            if (listOfBalls.get(ballCounter).getBallOval().intersects(character.getCharacterRectangle())) {
                gameOver();
            }
        }
        for (int lineCounter = 0; lineCounter < listOfShootingLines.size(); lineCounter++) {
            for (int ballCounter = 0; ballCounter < listOfBalls.size(); ballCounter++) {
                if (listOfBalls.get(ballCounter).getBallOval()
                        .intersects(listOfShootingLines.get(lineCounter).getLine())) {
                    listOfShootingLines.remove(lineCounter);
                    removeIndeses.add(ballCounter);
                    lineCounter--;
                    inGamePoints++;
                    break;
                }
            }
        }
        Ball[] newBalls = new Ball[3];
        for (int i = 0; i < removeIndeses.size(); i++) {
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

    private void clearBoard() {
        for (int i = 0; i < listOfShootingLines.size(); i++) {
            if (listOfShootingLines.get(i).getLineEndPosition() < -20) {
                listOfShootingLines.remove(i);
            }
        }
        for (int i = 0; i < listOfBalls.size(); i++) {
            if (listOfBalls.get(i).getBallDiameter() == 0) {
                listOfBalls.remove(i);
            }
        }
    }

    public void gameOver() {
        timer.stop();
        inGame = false;
        String odgovor;
        do {
            odgovor = JOptionPane.showInputDialog(this, "Game over. Your score: " + inGamePoints
            + "\nEnter your name.");
            if(odgovor != null)
                if(!odgovor.isEmpty())
                    new HighScore(odgovor, inGamePoints, this);
                else
                    JOptionPane.showMessageDialog(this, "Molim vas unesite ime");
            else
                break;
        } while (odgovor.isEmpty());
        
    }

    public void loadImages() {
        characterImageLoaded = MainCharacter.loadImages();
        ballImageLoaded = Ball.loadImages();
        try {
            background = ImageIO.read(new File("src/images/backgroundImage.png"));
            backgroundImageLoaded = true;
        } catch (IOException e) {
            backgroundImageLoaded = false;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (backgroundImageLoaded) {
            g.drawImage(background, 0, 0, panelWidht, panelHeight, this);
        }
        if (ballImageLoaded) {
            for (int i = 0; i < listOfBalls.size(); i++) {
                g.drawImage(Ball.ballImage, listOfBalls.get(i).getBallX(), listOfBalls.get(i).getBallY(),
                        listOfBalls.get(i).getBallDiameter(), listOfBalls.get(i).getBallDiameter(), this);
            }
        } else {
            for (int i = 0; i < listOfBalls.size(); i++) {
                g.fillOval(listOfBalls.get(i).getBallX(), listOfBalls.get(i).getBallY(),
                        listOfBalls.get(i).getBallDiameter(), listOfBalls.get(i).getBallDiameter());
            }
        }
        g.setColor(Color.green);
        for (int i = 0; i < listOfShootingLines.size(); i++) {

            g.fillRect(
                    listOfShootingLines.get(i).getLineStartX(), listOfShootingLines.get(i).getLineEndPosition(),
                    2, listOfShootingLines.get(i).getLineStartY());
        }

        g.setColor(Color.black);
        if (characterImageLoaded) {
            g.drawImage(MainCharacter.getCharacterImage(),
                    character.getCharacterX(), character.getCharacterY(),
                    character.getCharacterWidth(), character.getCharacterHeight(),
                    this);
        } else {
            g.fillRect(character.getCharacterX(), character.getCharacterY(),
                    character.getCharacterWidth(), character.getCharacterHeight());
        }
        if (inGame) {
            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.BOLD, 15));
            g.drawString("Score: " + inGamePoints, 30, 30);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frameCounter++;
        for (int i = 0; i < listOfShootingLines.size(); i++) {
            listOfShootingLines.get(i).move();
        }
        for (int i = 0; i < listOfBalls.size(); i++) {
            listOfBalls.get(i).move();
        }
        checkCrash();
        clearBoard();
        if (frameCounter % newBallPeriod == 0) {
            listOfBalls.add(new Ball(35, ballSpeedX, ballSpeedY, 40, 40));
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            character.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            character.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (listOfShootingLines.size() < 5) {
                listOfShootingLines.add(
                        new ShootingLine(
                                character.getCharacterX() + character.getCharacterWidth() / 2,
                                panelHeight, linesSpeed));
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
