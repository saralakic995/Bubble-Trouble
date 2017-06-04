package boubletrouble;

public class Ball {
    private int ballDiameter;
    private int xMovement;
    private int yMovement;
    private int ballX;
    private int ballY;

    public int getBallDiameter() {
        return ballDiameter;
    }

    public int getBallX() {
        return ballX;
    }

    public int getBallY() {
        return ballY;
    }

    
    
    public Ball(int ballDiameter, int xMovement, int yMovement, int ballX, int ballY) {
        this.ballDiameter = ballDiameter;
        this.xMovement = xMovement;
        this.yMovement = yMovement;
        this.ballX = ballX;
        this.ballY = ballY;
    }
    
}
