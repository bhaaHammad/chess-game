package Frame;
import Enums.Colors;
import Enums.Type;
import Game.Player;
import Pieces.Piece;
import Pieces.PieceFactory;
public class Board {
    private Spot[] board = new Spot[64];
    private PieceFactory pieceFactory;
    private static Board SingletonBoard = new Board();

    private Board() {
        initializeSpots();
        pieceFactory = new PieceFactory();
    }

    public static Board getBoard() {
        return SingletonBoard;
    }

    private void initializeSpots() {
        board[0] = new Spot(Type.ROOK, Colors.WHITE);
        board[1] = new Spot(Type.KNIGHT, Colors.WHITE);
        board[2] = new Spot(Type.BISHOP, Colors.WHITE);
        board[3] = new Spot(Type.QUEEN, Colors.WHITE);
        board[4] = new Spot(Type.KING, Colors.WHITE);
        board[5] = new Spot(Type.BISHOP, Colors.WHITE);
        board[6] = new Spot(Type.KNIGHT, Colors.WHITE);
        board[7] = new Spot(Type.ROOK, Colors.WHITE);
        for (int i = 8; i <= 15; ++i) {
            board[i] = new Spot(Type.PAWN, Colors.WHITE);
        }

        board[56] = new Spot(Type.ROOK, Colors.BLACK);
        board[57] = new Spot(Type.KNIGHT, Colors.BLACK);
        board[58] = new Spot(Type.BISHOP, Colors.BLACK);
        board[59] = new Spot(Type.QUEEN, Colors.BLACK);
        board[60] = new Spot(Type.KING, Colors.BLACK);
        board[61] = new Spot(Type.BISHOP, Colors.BLACK);
        board[62] = new Spot(Type.KNIGHT, Colors.BLACK);
        board[63] = new Spot(Type.ROOK, Colors.BLACK);
        for (int i = 48; i <= 55; ++i) {
            board[i] = new Spot(Type.PAWN, Colors.BLACK);
        }

        for (int i = 16; i <= 47; ++i) {
            board[i] = new Spot(Type.NULL, Colors.NULL);
        }
    }

    public void printBoard() {
        int startIndex = 56, endIndex = 63;
        System.out.print(" |");
        for (char column = 'a'; column <= 'h'; ++column)
            System.out.print(" " + column + " |");
        System.out.println(" ");

        int row = 8;
        while (startIndex >= 0) {
            char sign = (startIndex == 56 ? '+' : '-');
            for (int i = 0; i < 35; ++i) System.out.print(sign);
            System.out.println();

            System.out.print(row + "|");
            for (int k = startIndex; k <= endIndex; ++k) {
                String pieceShape = getPieceShape(board[k].getPieceType());
                int playerNumber;
                if (board[k].getPieceColor() == Colors.NULL) playerNumber = -1;
                else playerNumber = (board[k].getPieceColor() == Colors.WHITE ? 1 : 2);
                if (playerNumber != -1) pieceShape = (playerNumber + pieceShape);
                if (pieceShape.length() == 3) System.out.print(pieceShape + "|");
                else if (pieceShape.length() == 2) System.out.print(pieceShape + " |");
                else System.out.print(" " + pieceShape + " |");
            }
            System.out.println(row);
            endIndex -= 8;
            startIndex -= 8;
            row--;
        }
        for (int i = 0; i < 35; ++i) System.out.print("+");
        System.out.println();

        System.out.print(" |");
        for (char colum = 'a'; colum <= 'h'; ++colum)
            System.out.print(" " + colum + " |");
        System.out.println(" ");
    }

    private String getPieceShape(Type type) {
        if (type == Type.KING)
            return "KI";
        if (type == Type.BISHOP)
            return "B";
        if (type == Type.KNIGHT)
            return "KN";
        if (type == Type.PAWN)
            return "P";
        if (type == Type.QUEEN)
            return "Q";
        if (type == Type.ROOK)
            return "R";
        return "*";
    }

    public Boolean rightPieceColor(int fromPosition, Player player) {
        return (board[fromPosition].getPieceColor() == player.getPlayerColor());
    }

    public Type occupiedToPosition(int fromPosition, int toPosition) {
        if (board[toPosition].getPieceColor() != board[fromPosition].getPieceColor())
            return board[toPosition].getPieceType();
        return Type.NULL;
    }

    public Boolean pieceValidMove(int fromPosition, int toPosition) {
        Piece piece = pieceFactory.createPiece(board[fromPosition].getPieceType());
        if (piece == null) return false;
        int fromValidate = 1, toValidate = 1;
        if (board[fromPosition].getPieceType() == Type.PAWN && board[fromPosition].getPieceColor() == Colors.BLACK)
            fromValidate = -1;
        if ((board[fromPosition].getPieceType() == Type.PAWN) && (occupiedToPosition(fromPosition, toPosition) != Type.NULL))
            toValidate = -1;

        return piece.isMoveValid(fromValidate * fromPosition, toValidate * toPosition);
    }

    public Boolean pieceBlockedMove(int fromPosition, int toPosition) {
        Piece piece = pieceFactory.createPiece(board[fromPosition].getPieceType());
        if (piece == null) return false;
        int fromValidate = 1, toValidate = 1;
        if (board[fromPosition].getPieceColor() == Colors.BLACK && board[fromPosition].getPieceType() == Type.PAWN)
            fromValidate = -1;
        if ((board[fromPosition].getPieceType() == Type.PAWN) && (occupiedToPosition(fromPosition, toPosition) != Type.NULL))
            toValidate = -1;

        return piece.isMyRoadBlocked((fromPosition * fromValidate), (toPosition * toValidate), board);
    }

    public Boolean checkMate(Player player) {
        int kingPosition = -1;
        int[] moves = {-1, 7, 8, 9, 1, -7, -8, -9};
        for (int i = 0; i <= 63; ++i) {
            if (board[i].getPieceType() == Type.KING && board[i].getPieceColor() == player.getPlayerColor()) {
                kingPosition = i;
                break;
            }
        }

        for (int move : moves) {
            int k = kingPosition;
            while (k >= 0 && k <= 63) {
                if ((move == 7 || move == -9 || move == -1) && k % 8 == 0) break;
                if ((move == 9 || move == -7 || move == 1) && k % 8 == 7) break;
                if (move == 8 && k / 8 == 7) break;
                if (move == -8 && k / 8 == 0) break;
                k += move;
                if (k >= 0 && k <= 63) {
                    if (board[k].getPieceColor() != Colors.NULL && board[k].getPieceColor() != player.getPlayerColor()) {
                        if (pieceValidMove(k, kingPosition))
                            return true;
                        else return false;
                    } else if (board[k].getPieceColor() != Colors.NULL)
                        break;
                }
            }
        }
        return false;
    }

    public void movePiece(int fromPosition, int toPosition) {
        board[toPosition] = new Spot(board[fromPosition].getPieceType(), board[fromPosition].getPieceColor());
        board[fromPosition] = new Spot(Type.NULL, Colors.NULL);
    }
}
