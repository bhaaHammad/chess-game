package Pieces;
import Enums.Type;
import Frame.Spot;
public class CheckBlockedRoad {
    public static Boolean isTheRoadBlocked(int fromPosition, int toPosition, int destination, Spot[] spot) {
        int originFromPosition = fromPosition;
        while (fromPosition != toPosition && (fromPosition >= 0 && fromPosition <= 63)) {
            fromPosition += destination;
            if (fromPosition == toPosition && spot[toPosition].getPieceColor() != spot[originFromPosition].getPieceColor())
                return false;
            if (spot[fromPosition].getPieceType() != Type.NULL)
                return true;
        }
        return false;
    }
}
