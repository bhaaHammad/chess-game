package Pieces;

import Enums.Colors;
import Enums.Type;
import Frame.Spot;

public class King implements Piece {
    public Boolean isMoveValid(int fromPosition, int toPosition) {
        int up = 8, upRight = 9, right = 1, downRight = -7,
                down = -8, downLeft = -9, left = -1, upLeft = 7;

        //check if I can go right
        if (fromPosition % 8 < 7) {
            int[] moves = {right, upRight, downRight};
            for (int i = 0; i < 3; ++i) {
                if (fromPosition + moves[i] == toPosition) {
                    return true;
                }
            }
        }

        //check if I can go left
        if (fromPosition % 8 > 0) {
            int[] moves = {left, upLeft, downLeft};
            for (int i = 0; i < 3; ++i) {
                if (fromPosition + moves[i] == toPosition) {
                    return true;
                }
            }
        }

        //check if I can go up
        if (fromPosition / 8 < 7) {
            int[] moves = {up, upRight, upLeft};
            for (int i = 0; i < 3; ++i) {
                if (fromPosition + moves[i] == toPosition) {
                    return true;
                }
            }
        }

        //check if I can go down
        if (fromPosition / 8 > 0) {
            int[] moves = {down, downLeft, downRight};
            for (int i = 0; i < 3; ++i) {
                if (fromPosition + moves[i] == toPosition) {
                    return true;
                }
            }
        }
        return false;
    }

    public int myDestination(int fromPosition, int toPosition) {
        int up = 8, upRight = 9, right = 1, downRight = -7,
                down = -8, downLeft = -9, left = -1, upLeft = 7;

        //check if piece go right
        if (fromPosition % 8 < 7) {
            int[] moves = {right, upRight, downRight};
            for (int i = 0; i < 3; ++i) {
                if (fromPosition + moves[i] == toPosition) {
                    return moves[i];
                }
            }
        }

        //check if piece go left
        if (fromPosition % 8 > 0) {
            int[] moves = {left, upLeft, downLeft};
            for (int i = 0; i < 3; ++i) {
                if (fromPosition + moves[i] == toPosition) {
                    return moves[i];
                }
            }
        }

        //check if piece go up
        if (fromPosition / 8 < 7) {
            int[] moves = {up, upRight, upLeft};
            for (int i = 0; i < 3; ++i) {
                if (fromPosition + moves[i] == toPosition) {
                    return moves[i];
                }
            }
        }

        //check if piece go down
        if (fromPosition / 8 > 0) {
            int[] moves = {down, downLeft, downRight};
            for (int i = 0; i < 3; ++i) {
                if (fromPosition + moves[i] == toPosition) {
                    return moves[i];
                }
            }
        }
        return 0;
    }

    public Boolean isMyRoadBlocked(int fromPosition, int toPosition, Spot[] spot) {
        int destination = myDestination(fromPosition, toPosition);
        return CheckBlockedRoad.isTheRoadBlocked(fromPosition, toPosition, destination, spot);
    }
}

