package Pieces;

import Enums.Colors;
import Enums.Type;
import Frame.Spot;

public class Knight implements Piece {
    public Boolean isMoveValid(int fromPosition, int toPosition) {
        int[] up = {17, 15};
        int[] down = {-17, -15};
        int[] right = {10, -6};
        int[] left = {6, -10};

        //check if I can do up
        if (fromPosition / 8 <= 5) {
            for (int i = 0; i < 2; ++i) {
                if (fromPosition + up[i] == toPosition)
                    return true;
            }
        }

        //check if I can do down
        if (fromPosition / 8 >= 2) {
            for (int i = 0; i < 2; ++i) {
                if (fromPosition + down[i] == toPosition)
                    return true;
            }
        }

        //check if I can do right
        if (fromPosition % 8 <= 5) {
            for (int i = 0; i < 2; ++i) {
                if (fromPosition + right[i] == toPosition)
                    return true;
            }
        }

        //check if I can do left
        if (fromPosition % 8 >= 2) {
            for (int i = 0; i < 2; ++i) {
                if (fromPosition + left[i] == toPosition)
                    return true;
            }
        }
        return false;
    }

    public int myDestination(int form, int to) {
        return 0;
    }

    public Boolean isMyRoadBlocked(int from, int to, Spot[] board) {
        return false;
    }
}
