package boubletrouble;

import java.awt.geom.Rectangle2D;

public class ShootingLine {
    private int lineStartX;
    private int lineStartY;
    private int lineSpeed;
    private int lineEndPosition;

    public int getLineStartX() {
        return lineStartX;
    }

    public int getLineStartY() {
        return lineStartY;
    }

    public int getLineEndPosition() {
        return lineEndPosition;
    }

    
    public ShootingLine(int lineStartX, int lineStartY, int lineSpeed) {
        this.lineStartX = lineStartX;
        this.lineStartY = lineStartY;
        this.lineSpeed = lineSpeed;
        this.lineEndPosition = lineStartY;
    }
    
    public void move()
    {
        lineEndPosition -= lineSpeed;
    }
    
    public Rectangle2D getLine()
    {
        return new Rectangle2D.Float(lineStartX, lineEndPosition, 2 , lineStartY);
    }
}
