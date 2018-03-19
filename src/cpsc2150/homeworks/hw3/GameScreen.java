package cpsc2150.homeworks.hw3;

import java.util.Scanner;

/**
 * Name         : Lukas Herman (lukash)
 * Created on   : 01/28/17 
 */

/**
 * This is a boundary class which it's sole responsibility is to
 * interact with the user.
 * 
 * @invariant PLAYERS = { 'X', 'O' }
 *            and SCANNER is not NULL
 *            and board is not NULL
 */
public class GameScreen {
    private final static char[] PLAYERS = { 'X', 'O', 'Y', 'Z', 'A', 'K', 'E', 'J', 'N', 'H' };
    private final static Scanner SCANNER = new Scanner(System.in);

    private IGameBoard board;
    private int numPlayers;

    /**
     * This is the main function for this program.
     * 
     * @param args are arguments given by the user before the program runs
     * @ensures construct a GameScreen instance
     *          and call GameScreen's start method
     */
    public static void main(String[] args) {
        GameScreen screen = new GameScreen();
        screen.start();
    }

    /**
     * Construct an instance.
     */
    public GameScreen() {
    }

    /**
     * This is the main gateway to the game, recursively call itself
     * if the user wants to replay.
     * 
     * @ensures board = new GameBoardFast()
     *
     */
    public void start() {
        String ans;

        // Game start!!
        initBoard();
        System.out.println(board.toString());

        // Players start taking turns
        play(0);

        // Ask if the user wants to replay
        System.out.println("Would you like to play again? Y/N");
        ans = SCANNER.next();

        // "y": restart the game. "n": end the game
        if (ans.equalsIgnoreCase("y"))
            start();
        else
            SCANNER.close();
    }

    /**
     * This is a helper function to ask the user to customize the board's 
     * initialization
     * 
     * @ensures board will be initialized using the user's inputs
     *          and needToWin will be initialize using the user's input
     */
    private void initBoard() {
        int numRows, numCols, needToWin;
        String implementationCode;

        // Ask the number of players
        System.out.println("How many players will play? (Max of 10)");
        this.numPlayers = SCANNER.nextInt();
        while (this.numPlayers < 2 || this.numPlayers > 10) {
            System.out.println("Must be between 2 and 10 players");
            System.out.println("How many players will play? (Max of 10)");
            this.numPlayers = SCANNER.nextInt();
        }

        // Ask the number of rows
        System.out.println("How many rows should be on the board?");
        numRows = SCANNER.nextInt();
        while (numRows < 1 || numRows > IGameBoard.MAX_SIZE) {
            System.out.println("Can only have " + IGameBoard.MAX_SIZE + " rows or less");
            System.out.println("How many rows should be on the board?");
            numRows = SCANNER.nextInt();
        }

        // Ask the number of cols
        System.out.println("How many columns should be on the board?");
        numCols = SCANNER.nextInt();
        while (numCols < 1 || numCols > IGameBoard.MAX_SIZE) {
            System.out.println("Can only have " + IGameBoard.MAX_SIZE + " columns or less");
            System.out.println("How many columns should be on the board?");
            numCols = SCANNER.nextInt();
        }

        // Ask the number of in a row to win
        System.out.println("How many in a row to win?");
        needToWin = SCANNER.nextInt();
        while (needToWin > numRows || needToWin > numCols) {
            System.out.println("You can't have that many because thats more than the number of rows or columns");
            System.out.println("How many in a row to win?");
            needToWin = SCANNER.nextInt();
        }

        // Ask for a specific implementation
        System.out.println("Enter F for a (F)ast implementation or M for a (M)emory efficient implementation");
        implementationCode = SCANNER.next().toLowerCase();
        while (!implementationCode.equals("f") && !implementationCode.equals("m")) {
            System.out.println("Enter F for a (F)ast implementation or M for a (M)emory efficient implementation");
            implementationCode = SCANNER.next().toLowerCase();
        }

        switch (implementationCode) {
        case "f":
            board = new GameBoardFast(numRows, numCols, needToWin);
            break;
        case "m":
            board = new GameBoardMem(numRows, numCols, needToWin);
            break;
        default:
            break;
        }
    }

    /**
     * This function keeps asking user where to put the marker alternatively
     * until one of the PLAYERS win the game or the game ends with a draw.
     *
     * @param turn is the current number of turns has been played
     * @requires board is not NULL
     *           and only be called from start() and from itself
     *           and 0 <= turn < Integer.MAX_VALUE
     * @ensures keeps recursing call itself to ask the users to put the mark
     *          alternatively until of the PLAYERS win the game or the game
     *          ends with a draw 
     *          and board object will not be destroyed or recreated
     *  
     */
    private void play(int turn) {
        int row, col;
        char player = PLAYERS[turn % this.numPlayers];
        BoardPosition pos;

        System.out.println("Player " + player + " Please enter your ROW");
        row = SCANNER.nextInt();
        System.out.println("Player " + player + " Please enter your COLUMN");
        col = SCANNER.nextInt();

        pos = new BoardPosition(row, col, player);
        if (board.checkSpace(pos)) {
            if (board.checkForWinner(pos))
                System.out.println("Player " + player + " wins!");
            else {
                board.placeMarker(pos);
                if(board.checkForDraw())
                    System.out.println("The game has been drawn");
                else {
                    System.out.println(board.toString());
                    play(turn + 1);
                }
            }
        } else {
            System.out.println(board.toString());
            System.out.println("That space is unavailable, please pick again");
            play(turn);
        }
    }
}
