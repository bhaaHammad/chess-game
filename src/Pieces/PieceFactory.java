package Pieces;
import Enums.Type;
public class PieceFactory {
    public Piece createPiece(Type type) {
        if (type == Type.KING)
            return new King();
        if (type == Type.BISHOP)
            return new Bishop();
        if (type == Type.KNIGHT)
            return new Knight();
        if (type == Type.PAWN)
            return new Pawn();
        if (type == Type.QUEEN)
            return new Queen();
        if (type == Type.ROOK)
            return new Rook();
        return null;
    }
}
