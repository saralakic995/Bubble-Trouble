package boubletrouble;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MainCharacter {
    private int characterHeight = 30;
    private int characterWidth = 30;
    private int xCharacter;
    private int yCharacter;

    static BufferedImage characterImage = null;
    
    public int getCharacterHeight() {
        return characterHeight;
    }

    public int getCharacterWidth() {
        return characterWidth;
    }

    public int getCharacterX() {
        return xCharacter;
    }

    public int getCharacterY() {
        return yCharacter;
    }
    
    public MainCharacter(int x) {
        this.xCharacter = x;
        this.yCharacter = MainPanel.getPanelHeight() - characterHeight;
    }
    
    public void moveLeft()
    {
        if(xCharacter > 10)
            xCharacter -= 10;
        else
            xCharacter = 0;
    }
    
    public void moveRight()
    {
        if(xCharacter < MainPanel.getPanelWidht() - characterWidth)
            xCharacter += 10;
        else
            xCharacter = MainPanel.getPanelWidht() - characterWidth;
    }
    public Rectangle getCharacterRectangle()
    {
        return new Rectangle(xCharacter, yCharacter, characterWidth, characterHeight);
    }

    public static BufferedImage getCharacterImage() {
        return characterImage;
    }
    
    
    public static boolean loadImages() {
        try {
            characterImage = ImageIO.read(new File("src/images/slik.jpg"));
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
