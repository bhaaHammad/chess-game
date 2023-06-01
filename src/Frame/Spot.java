package Frame;
import Enums.Colors;
import Enums.Type;
public class Spot {
    private Type pieceType;
    private Colors pieceColor;
    public Spot(Type pieceType, Colors pieceColor) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
    }
    public Type getPieceType() {
        return pieceType;
    }
    public Colors getPieceColor() {
        return pieceColor;
    }
}
