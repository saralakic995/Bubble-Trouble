package boubletrouble;

import java.awt.Rectangle;

public class MainCharacter {
    private int characterHeight = 30;
    private int characterWidth = 30;
    private int xCharacter;
    private int yCharacter;

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
}
