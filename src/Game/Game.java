package Game;
import Enums.Colors;
import Enums.Type;
import Frame.Board;
import java.util.HashMap;
import java.util.Scanner;
public class Game {
    private Player whitePlayer;
    private Player blackPlayer;
    private HashMap<String, Integer> spotName= new HashMap<String, Integer>();;
    private HashMap<Type, Integer> aliveTypes= new HashMap<Type, Integer>();;
    Scanner sc = new Scanner(System.in);
    public Game() {
        initializeSpotName();
        initializeAlive();
    }
    private void initializeSpotName() {
        int numberOfSpot = 0;
        for (int i = 1; i <= 8; ++i) {
            for (int ch = 97; ch <= 104; ++ch) {
                String spotAlphabetical = "" + (char) ch + i;
                spotName.put(spotAlphabetical, numberOfSpot);
                numberOfSpot++;
            }
        }
    }
    private void playerInformation() {
        System.out.println("white player name: ");
        whitePlayer = new Player(sc.next(), Colors.WHITE);
        System.out.println("black player name: ");
        blackPlayer = new Player(sc.next(), Colors.BLACK);
    }
    private void initializeAlive() {
        Type[] type = {Type.KING, Type.BISHOP, Type.KNIGHT, Type.QUEEN, Type.PAWN, Type.ROOK};
        for (int i = 0; i < 6; ++i) {
            if (type[i] == Type.KING || type[i] == Type.QUEEN)
                aliveTypes.put(type[i], 2);
            if (type[i] == Type.PAWN)
                aliveTypes.put(type[i], 16);
            else
                aliveTypes.put(type[i], 4);
        }
    }
    private void checkInputs(Player player, Board board) {
        while (true) {
            String fromPosition = sc.next();
            String toPosition = sc.next();
            Boolean validFromPosition = fromPosition.matches("[a-h][1-8]");
            Boolean validToPosition = toPosition.matches("[a-h][1-8]");

            if (validFromPosition && validToPosition) {
                int fromSpotNumber = spotName.get(fromPosition),
                        toSpotNumber = spotName.get(toPosition);

                Boolean rightColor = board.rightPieceColor(fromSpotNumber, player);
                Boolean validMove = (board.pieceValidMove(fromSpotNumber, toSpotNumber));
                Boolean notBlockedMove = !(board.pieceBlockedMove(fromSpotNumber, toSpotNumber));
                if (rightColor && validMove && notBlockedMove) {
                    if (board.occupiedToPosition(fromSpotNumber, toSpotNumber) != Type.NULL) {
                        Type type = board.occupiedToPosition(fromSpotNumber, toSpotNumber);
                        killedPiece(type);
                    }
                    board.movePiece(fromSpotNumber, toSpotNumber);
                    break;
                } else {
                    System.out.println("please enter a valid move: ");
                }
            } else {
                System.out.println("please enter a valid value: ");
            }
        }
    }
    private void killedPiece(Type type) {
        aliveTypes.put(type, aliveTypes.get(type) - 1);
        if (aliveTypes.get(type) == 0)
            aliveTypes.remove(type);
    }
    private void playerTurn(Board board) {
        int wholeNumberOfTurns = 50;
        boolean flag = false;
        while (wholeNumberOfTurns >= 0) {
            if (board.checkMate(blackPlayer))
            {

                System.out.println("white player wins");
                flag = true;
                break;
            }
            System.out.println("white player turn: ");
            if (board.checkMate(whitePlayer))
                System.out.println("king in dangerous !");
            checkInputs(whitePlayer, board);
            if (aliveTypes.size() == 2) {
                break;
            }
            board.printBoard();

            if (board.checkMate(whitePlayer)) {
                System.out.println("black player wins");
                flag = true;
                break;
            }
            System.out.println("black player turn: ");
            if (board.checkMate(blackPlayer))
                System.out.println("king in dangerous !");
            checkInputs(blackPlayer, board);
            if (aliveTypes.size() == 2) {
                break;
            }

            board.printBoard();
            wholeNumberOfTurns--;
        }

        if (!flag)
            System.out.println("draw");
    }
    public void startGame() {
        Board board = Board.getBoard();
        board.printBoard();
        playerInformation();
        playerTurn(board);
    }
}
