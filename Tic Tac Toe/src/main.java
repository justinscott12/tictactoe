import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class main {

    // runs a game of tic tac toe, or simulates one for you and tells you if the board is a win or a draw
    // not a fun gui tho.....
    private static final String O = "O";
    private static final String X = "X";
    private static final String playerToMove = X;
    private static String[] boardValues = new String[10];

    public static void main(String[] args) {

        HashMap<Integer, String> board = new HashMap();
        String currentPlayer = "X";
        Scanner sc = new Scanner(System.in);

        initializeBoard(board, 1, 10, " ");

        System.out.println(board);

        while (!isWin(board)) {
            System.out.println(currentPlayer + "\'s turn:");
            int userInput = sc.nextInt();
            board.put(userInput, currentPlayer);
            currentPlayer = alternatePlayers(currentPlayer);
            System.out.println(board);
        }

        System.out.println(board);
        System.out.println(isWin(board));

    }

    public static String alternatePlayers(String currentPlayer) {
        if (currentPlayer.equalsIgnoreCase(X)) {
            return O;
        }
        return X;
    }

    public static void initializeBoard(HashMap<Integer, String> board, int start, int end, String value) {
        for (int i = start; i < end; i++) {
            board.put(i, value);
        }
        getAll(board);
    }

    public static void getAll(HashMap<Integer, String> board) {
        boardValues = board.values().toArray(new String[0]);
        String[] result = new String[board.size() + 1];
        System.arraycopy(boardValues, 0, result, 1, boardValues.length);
        boardValues = result;
    }

    /**
     * Win combinations:
     * 1 2 3
     * 1 4 7
     * 7 8 9
     * 3 6 9
     * 2 5 8
     * 1 5 9
     * 3 5 7
     * 4 5 6
     */
    public static boolean isWin(HashMap<Integer, String> board) {
        getAll(board);
        if (boardValues.length == 0) {
            System.out.println("Board is empty.");
            return false;
        }

        if (!boardValues[1].equalsIgnoreCase(" ") && boardValues[1].equalsIgnoreCase(boardValues[2]) && boardValues[3].equalsIgnoreCase(boardValues[1])) {
            return true;
        } else if (!boardValues[1].equalsIgnoreCase(" ") && boardValues[1].equalsIgnoreCase(boardValues[4]) && boardValues[7].equalsIgnoreCase(boardValues[1])) {
            return true;
        } else if (!boardValues[7].equalsIgnoreCase(" ") && boardValues[7].equalsIgnoreCase(boardValues[8]) && boardValues[9].equalsIgnoreCase(boardValues[7])) {
            return true;
        } else if (!boardValues[3].equalsIgnoreCase(" ") && boardValues[3].equalsIgnoreCase(boardValues[6]) && boardValues[9].equalsIgnoreCase(boardValues[3])) {
            return true;
        } else if (!boardValues[2].equalsIgnoreCase(" ") && boardValues[2].equalsIgnoreCase(boardValues[5]) && boardValues[8].equalsIgnoreCase(boardValues[2])) {
            return true;
        } else if (!boardValues[1].equalsIgnoreCase(" ") && boardValues[1].equalsIgnoreCase(boardValues[5]) && boardValues[9].equalsIgnoreCase(boardValues[1])) {
            return true;
        } else if (!boardValues[3].equalsIgnoreCase(" ") && boardValues[3].equalsIgnoreCase(boardValues[5]) && boardValues[7].equalsIgnoreCase(boardValues[3])) {
            return true;
        } else
            return !boardValues[4].equalsIgnoreCase(" ") && boardValues[4].equalsIgnoreCase(boardValues[5]) && boardValues[6].equalsIgnoreCase(boardValues[4]);
    }

    public static void simulateBoard(HashMap<Integer, String> board, String player, int possibleMoves) {
        getAll(board);
        for (int i = 1; i <= board.size(); i++) {
            if (boardValues[i].equalsIgnoreCase(" ")) {
                if (isWin(board)) {
                    break;
                }
                board.put(i, player);
                if (possibleMoves > 0) {
                    simulateBoard(board, alternatePlayers(player), possibleMoves - 1);
                }
            }
        }
    }

    public static void simulateBoard(HashMap<Integer, String> board, String player, int possibleMoves, int positionToStart) {
        getAll(board);
        if (positionToStart == 0) {
            System.out.println("hi");
            simulateBoard(board, player, possibleMoves);
        }

        for (int i = positionToStart; i <= board.size(); i++) {
            if (boardValues[i].equalsIgnoreCase(" ")) {
                if (isWin(board)) {
                    break;
                }
                board.put(i, player);
                if (possibleMoves > 0) {
                    simulateBoard(board, alternatePlayers(player), possibleMoves - 1);
                }
            }
        }
    }


}