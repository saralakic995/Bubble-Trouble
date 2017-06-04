package boubletrouble;

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
    
}
