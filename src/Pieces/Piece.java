package Pieces;
import Frame.Spot;
public interface Piece {
    public Boolean isMoveValid(int form, int to);

    public Boolean isMyRoadBlocked(int fromPosition, int toPosition, Spot[] spot);

    public int myDestination(int form, int to);
}
