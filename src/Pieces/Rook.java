package Pieces;

import Enums.Colors;
import Enums.Type;
import Frame.Spot;

public class Rook implements Piece {
    public Boolean isMoveValid(int fromPosition, int toPosition) {
        return (((fromPosition % 8) == (toPosition % 8))
                || ((fromPosition / 8) == (toPosition / 8)));
    }

    public int myDestination(int fromPosition, int toPosition) {
        if ((fromPosition % 8) == (toPosition % 8)) {
            if (fromPosition > toPosition)
                return -8;
            return 8;
        }
        if ((fromPosition / 8) == (toPosition / 8)) {
            if (fromPosition > toPosition)
                return -1;
            return 1;
        }
        return 0;
    }

    public Boolean isMyRoadBlocked(int fromPosition, int toPosition, Spot[] spot) {
        int destination = myDestination(fromPosition, toPosition);
        return CheckBlockedRoad.isTheRoadBlocked(fromPosition, toPosition, destination, spot);
    }
}

