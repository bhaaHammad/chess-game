package Game;
import Enums.Colors;
public class Player {
    private final String playerName;
    private final Colors playerColor;
    public Player(String playerName, Colors playerColor) {
        this.playerName = playerName;
        this.playerColor = playerColor;
    }
    public String getPlayerName() {
        return playerName;
    }
    public Colors getPlayerColor() {
        return playerColor;
    }
}
