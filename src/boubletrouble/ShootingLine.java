package boubletrouble;

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
        lineEndPosition = lineStartY;
    }
    
}
