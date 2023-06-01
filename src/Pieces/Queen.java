package Pieces;

import Enums.Colors;
import Enums.Type;
import Frame.Spot;

public class Queen implements Piece {
    public Boolean isMoveValid(int fromPosition, int toPosition) {
        int[] moves = {7, 9};
        if (fromPosition < toPosition) {
            for (int i = 0; i < 2; ++i) {
                if (fromPosition % 8 == 7 && moves[i] == 9) continue;
                if (fromPosition % 8 == 0 && moves[i] == 7) continue;
                int differenceSpot = (toPosition / moves[i]) - (fromPosition / moves[i]);
                if (fromPosition + (differenceSpot * moves[i]) == toPosition) {
                    return true;
                }
            }
        }

        if (fromPosition > toPosition) {
            for (int i = 0; i < 2; ++i) {
                if (fromPosition % 8 == 7 && moves[i] == 7) continue;
                if (fromPosition % 8 == 0 && moves[i] == 9) continue;
                int differenceSpot = (fromPosition / moves[i]) - (toPosition / moves[i]);
                if (fromPosition - (differenceSpot * moves[i]) == toPosition) {
                    return true;
                }

            }
        }
        return ((fromPosition % 8) == (toPosition % 8))
                || ((fromPosition / 8) == (toPosition / 8));
    }

    public int myDestination(int fromPosition, int toPosition) {
        int[] moves = {7, 9};
        if (fromPosition < toPosition) {
            for (int i = 0; i < 2; ++i) {
                if (fromPosition % 8 == 7 && moves[i] == 9) continue;
                if (fromPosition % 8 == 0 && moves[i] == 7) continue;
                int differenceSpot = (toPosition / moves[i]) - (fromPosition / moves[i]);
                if (fromPosition + (differenceSpot * moves[i]) == toPosition) {
                    return moves[i];
                }
            }
        }

        if (fromPosition > toPosition) {
            for (int i = 0; i < 2; ++i) {
                if (fromPosition % 8 == 7 && moves[i] == 7) continue;
                if (fromPosition % 8 == 0 && moves[i] == 9) continue;
                int differenceSpot = (fromPosition / moves[i]) - (toPosition / moves[i]);
                if (fromPosition - (differenceSpot * moves[i]) == toPosition) {
                    return (-1 * moves[i]);
                }

            }
        }
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
