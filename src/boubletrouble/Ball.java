package boubletrouble;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Ball {
    private int ballDiameter;
    private int xMovement;
    private int yMovement;
    private int ballX;
    private int ballY;
    
    static BufferedImage ballImage;

    public int getBallDiameter() {
        return ballDiameter;
    }

    public int getBallX() {
        return ballX;
    }

    public int getBallY() {
        return ballY;
    }

    public int getxMovement() {
        return xMovement;
    }

    public int getyMovement() {
        return yMovement;
    }

    public Ball(int ballDiameter, int xMovement, int yMovement, int ballX, int ballY) {
        if(ballDiameter > 5) 
        {   
            this.ballDiameter = ballDiameter;
            this.xMovement = xMovement;
            this.yMovement = yMovement;
            this.ballX = ballX;
            this.ballY = ballY;
        }
        else
            this.ballDiameter = 0;
         
    }
    
    public void move()
    {
        ballX += xMovement;
        ballY += yMovement;
        if(ballX + ballDiameter >= MainPanel.getPanelWidht() || ballX <= 0)
        {    
            xMovement *= -1;
        }
        if(ballY + ballDiameter>= MainPanel.getPanelHeight()|| ballY <= 0)
        {
            yMovement *= -1;
        }
        
    }
    
    public java.awt.geom.Ellipse2D getBallOval()
    {
        return new java.awt.geom.Ellipse2D.Float(ballX, ballY, ballDiameter, ballDiameter);
    }
    
    public static boolean loadImages() {
        try {
            ballImage = ImageIO.read(new File("src/images/ball.png"));
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
