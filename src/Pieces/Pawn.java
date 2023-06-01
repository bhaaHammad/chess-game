package Pieces;
import Enums.Type;
import Frame.Spot;
public class Pawn implements Piece {
    public Boolean isMoveValid(int fromPosition, int toPosition) {
        Boolean blackPlayer = (fromPosition < 0);
        if (toPosition < 0) {
            return specialMove(fromPosition, (-1 * toPosition));
        }
        if (fromPosition < 0) fromPosition *= -1;

        int[] moves = {8, 16};
        int sizeOfMoves;

        if (fromPosition >= 8 && fromPosition <= 15 || fromPosition >= 48 && fromPosition <= 55)
            sizeOfMoves = 2;
        else
            sizeOfMoves = 1;

        if (blackPlayer) {
            for (int i = 0; i < sizeOfMoves; ++i) {
                moves[i] *= -1;
            }
        }
        for (int i = 0; i < sizeOfMoves; ++i) {
            int dist = fromPosition + moves[i];
            if (dist == toPosition)
                return true;
        }
        return false;
    }

    private Boolean specialMove(int fromPosition, int toPosition) {
        boolean blackPlayer = (fromPosition < 0);
        if (blackPlayer) fromPosition *= -1;

        int[] moves = {7, 9};
        if (blackPlayer) {
            for (int i = 0; i < 2; ++i) {
                moves[i] *= -1;
            }
        }

        for (int i = 0; i < 2; ++i) {
            if ((moves[i] == 9 || moves[i] == -7) && fromPosition % 8 == 7) continue;
            if ((moves[i] == 7 || moves[i] == -9) && fromPosition % 8 == 0) continue;
            int dist = moves[i] + fromPosition;
            if (dist == toPosition) {
                return true;
            }
        }
        return false;
    }

    public int myDestination(int fromPosition, int toPosition) {
        Boolean blackPlayer = (fromPosition < 0);
        if (toPosition < 0) {
            return specialDestination(fromPosition, (-1 * toPosition));
        }
        if (fromPosition < 0) fromPosition *= -1;


        int[] moves = {8, 16};
        int sizeOfMoves;

        if (fromPosition >= 8 && fromPosition <= 15 || fromPosition >= 48 && fromPosition <= 55)
            sizeOfMoves = 2;
        else
            sizeOfMoves = 1;

        if (blackPlayer) {
            for (int i = 0; i < sizeOfMoves; ++i) {
                moves[i] *= -1;
            }
        }
        for (int i = 0; i < sizeOfMoves; ++i) {
            int dist = fromPosition + moves[i];
            if (dist == toPosition)
                return moves[i];
        }

        return 0;
    }

    private int specialDestination(int fromPosition, int toPosition) {
        Boolean blackPlayer = (fromPosition < 0);
        if (fromPosition < 0) fromPosition *= -1;
        if (toPosition < 0) toPosition *= -1;

        int[] moves = {7, 9};
        if (blackPlayer) {
            for (int i = 0; i < 2; ++i) {
                moves[i] *= -1;
            }
        }

        for (int i = 0; i < 2; ++i) {
            if ((moves[i] == 9 || moves[i] == -7) && fromPosition % 8 == 7) continue;
            if ((moves[i] == 7 || moves[i] == -9) && fromPosition % 8 == 0) continue;
            int dist = moves[i] + fromPosition;
            if (dist == toPosition) {
                return moves[i];
            }
        }
        return 0;
    }

    public Boolean isMyRoadBlocked(int fromPosition, int toPosition, Spot[] spot) {
        int destination = myDestination(fromPosition, toPosition);
        if (fromPosition < 0)
            fromPosition = fromPosition * -1;
        if (toPosition < 0)
            toPosition = toPosition * -1;
        return CheckBlockedRoad.isTheRoadBlocked(fromPosition, toPosition, destination, spot);
    }
}
