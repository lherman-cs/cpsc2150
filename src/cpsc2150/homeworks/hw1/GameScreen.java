package cpsc2150.homeworks.hw1;

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
    private final static char[] PLAYERS = { 'X', 'O' };
    private final static Scanner SCANNER = new Scanner(System.in);

    private GameBoard board;

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
     * @ensures board = new GameBoard()
     *
     */
    public void start() {
        String ans;

        // Game start!!
        board = new GameBoard();
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
        char player = PLAYERS[turn % PLAYERS.length];
        BoardPosition pos;

        System.out.println("Player " + player + " Please enter your ROW");
        row = SCANNER.nextInt();
        System.out.println("Player " + player + " Please enter your COLUMN");
        col = SCANNER.nextInt();

        pos = new BoardPosition(row, col, player);
        if (board.checkSpace(pos)) {
            if (board.checkForWinner(pos))
                System.out.println("Player " + player + " wins!");
            else if (board.checkForDraw())
                System.out.println("The game has been drawn");
            else {
                board.placeMaker(pos);
                System.out.println(board.toString());
                play(turn + 1);
            }
        } else {
            System.out.println(board.toString());
            System.out.println("That space is unavailable, please pick again");
            play(turn);
        }
    }
}
