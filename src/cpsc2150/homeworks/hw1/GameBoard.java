package cpsc2150.homeworks.hw1;

import java.util.Arrays;

/**
 * Name         : Lukas Herman (lukash)
 * Created on   : 01/28/17 
 */

/**
 * This is an entity class which it's sole responsibility is to
 * keep players' marks in the board and some logics of the game.
 * 
 * @invariant NEED_TO_WIN = 5
 *            and SIZE = 8
 *            and board will never be destroyed or recreated
 */
public class GameBoard {
    private final static int NEED_TO_WIN = 5;
    private final static int SIZE = 8;

    private final char[][] board;

    /**
     * Construct an instance and initialize the 2-D array char board with ' '
     * 
     * @ensures board = a new 2-D char array filled with ' ' character
     */
    public GameBoard() {
        // Initialize the board with ' ' on every law
        board = new char[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++)
            for (int col = 0; col < SIZE; col++)
                board[row][col] = ' ';
    }

    /**
     * returns true if the position specified in pos is available and valid,
     * false otherwise
     *
     * @param pos is the position that needs to be check if it's available
     * @requires pos is not NULL
     * @ensures board's elements will not change
     *          and checkSpace returns true iff board[pos.getRow()][pos.getColumn()] == ' '
     *          and iff 0 <= pos.getRow(), pos.getColumn() < SIZE
     * @return true if there's an available space and valid
     */
    public boolean checkSpace(BoardPosition pos) {
        return (pos.getRow() >= 0 && pos.getRow() < SIZE)
                && (pos.getColumn() >= 0 && pos.getColumn() < SIZE)
                && board[pos.getRow()][pos.getColumn()] == ' ';
    }

    /**
     * places the character in marker on the position specified by
     * marker
     *
     * @param marker is the position needs to be marked in the board.
     * @requires marker is not NULL
     *           and 0 <= marker.getRow(), marker.getColumn() < SIZE
     * @ensures board[marker.getRow()][marker.getColumn()] = marker.getPlayer();
     *          board at row = marker.getRow(), column = marker.getColumn() will
     *          be marked with marker.getPlayer()
     */
    public void placeMaker(BoardPosition marker) {
        board[marker.getRow()][marker.getColumn()] = marker.getPlayer();
    }

    /**
     * this function will check to see if the lastPos placed resulted
     * in a winner. If so it will return true, otherwise false.
     *
     * @param lastPos is the position that needs to be checked
     * @requires lastPos is not NULL
     *           and checkSpace == True
     *           and 0 <= lastPos.getRow(), lastPos.getColumn() < SIZE
     * @ensures board's elements will not change
     *          and checkForWinner returns true if the lastPos makes the player
     *          who makes the move wins
     * @return true if the lastPos wins the game;
     *         checkHorizontal & checkVerticalWin & checkDiagonalWin are true
     */
    public boolean checkForWinner(BoardPosition lastPos) {
        return checkHorizontalWin(lastPos) || checkVerticalWin(lastPos) || checkDiagonalWin(lastPos);
    }

    /**
     * this function will check to see if the game has resulted in a
     * tie. A game is tied if there are no free board positions remaining. It
     * will return true if the game is tied, and false otherwise.
     *
     * @requires board is not NULL
     *           and checkForWinner == False
     * @ensures board's elements will not change
     *          and checkForDraw returns true if the board is filled (draw)
     * @return true if the game ends with a draw
     */
    public boolean checkForDraw() {
        for (int row = 0; row < SIZE; row++)
            for (int col = 0; col < SIZE; col++)
                if (board[row][col] == ' ')
                    return false;

        return true;
    }

    /**
     * An override method for outputting a human-readable information
     * 
     * @requires board is not NULL
     * @ensures board's elements will not change
     *          and toString returns a human-readable information string
     * @return a human-readable information string
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(" ");
        for (int col = 0; col < SIZE; col++)
            builder.append(" " + col);
        builder.append("\n");

        for (int row = 0; row < SIZE; row++) {
            builder.append(row + "|");
            for (int col = 0; col < SIZE; col++)
                builder.append(board[row][col] + "|");
            builder.append("\n");
        }

        return builder.toString();
    }

    /**
     * An override method for comparing between two GameBoard's instances
     * 
     * @param o is the other instance that needs to be compared
     * @requires o is an object
     *           and o is not NULL
     * @ensures board's elements will not change
     *          and equals returns true if the two instances have 
     *          the same board's elements
     * @return true if the two instances have the same board's elements. Else, false
     */
    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        // Return false if the object is not an instance of GameBoard
        if (!(o instanceof GameBoard)) {
            return false;
        }

        // typecast o to BoardPosition so that we can compare data members
        GameBoard other = (GameBoard) o;

        // Compare the data members and return accordingly
        return Arrays.deepEquals(this.board, other.board);
    }

    /**
     * checks to see if the last marker placed resulted in NEED_TO_WIN in a row
     * horizontally. Returns true if it does, otherwise false
     * 
     * @param lastPos is the position that needs to be checked
     * @requires lastPos is not NULL
     *           0 <= lastPos.getRow(), lastPos.getColumn() < SIZE
     * @ensures board's elements will not change
     *          and checkHorizontalWin returns true if the lastPos makes 
     *          NEED_TO_WIN in a row horizontally with the same lastPos' mark.
     * @return true if the lastPos makes NEED_TO_WIN in a row horizontally with the same 
     *         lastPos' mark
     */
    private boolean checkHorizontalWin(BoardPosition lastPos) {
        // Store lastPos row and player in local variables to improve the
        // performance by avoiding the overhead calling the getters.
        int row = lastPos.getRow();
        char player = lastPos.getPlayer();

        // Counter starts at 1 because the lastPos is being positioned.
        int counter = 1;

        // Check the right side
        int col = lastPos.getColumn() + 1;
        while (col < SIZE && board[row][col] == player) {
            counter++;
            if (counter == NEED_TO_WIN)
                return true;
            col++;
        }

        // Check the left side
        col = lastPos.getColumn() - 1;
        while (col >= 0 && board[row][col] == player) {
            counter++;
            if (counter == NEED_TO_WIN)
                return true;
            col--;
        }

        return false;
    }

    /**
     * checks to see if the last marker placed resulted in NEED_TO_WIN in a row
     * vertically. Returns true if it does, otherwise false
     * 
     * @param lastPos is the position that needs to be checked
     * @requires lastPos is not NULL
     *           0 <= lastPos.getRow(), lastPos.getColumn() < SIZE
     * @ensures board's elements will not change
     *          and checkVerticalWin returns true if the lastPos makes 
     *          NEED_TO_WIN in a row vertically with the same lastPos' mark.
     * @return true if the lastPos makes NEED_TO_WIN in a row vertically with the same 
     *         lastPos' mark
     */
    private boolean checkVerticalWin(BoardPosition lastPos) {
        // Store lastPos col and player in local variables to improve the
        // performance by avoiding the overhead calling the getters.
        int col = lastPos.getColumn();
        char player = lastPos.getPlayer();

        // Counter starts at 1 because the lastPos is being positioned.
        int counter = 1;

        // Check the lower side
        int row = lastPos.getRow() + 1;
        while (row < SIZE && board[row][col] == player) {
            counter++;
            if (counter == NEED_TO_WIN)
                return true;
            row++;
        }

        // Check the upper side
        row = lastPos.getRow() - 1;
        while (row >= 0 && board[row][col] == player) {
            counter++;
            if (counter == NEED_TO_WIN)
                return true;
            row--;
        }

        return false;
    }

    /**
     * checks to see if the last marker placed resulted in NEED_TO_WIN in a row
     * diagonally. Returns true if it does, otherwise false
     *
     * @param lastPos is the position that needs to be checked
     * @requires lastPos is not NULL
     *           0 <= lastPos.getRow(), lastPos.getColumn() < SIZE
     * @ensures board's elements will not change
     *          and checkDiagonalWin returns true if the lastPos makes
     *          NEED_TO_WIN in a row diagonally with the same lastPos' mark
     * @return true if the lastPos makes NEED_TO_WIN in a row diagonally with
     *         the same lastPos' mark
     */
    private boolean checkDiagonalWin(BoardPosition lastPos) {
        char player = lastPos.getPlayer();
        int row, col;

        // Counter starts at 1 because the lastPos is being positioned.
        int counter = 1;

        // Check the slash shape: "/"
        row = lastPos.getRow() - 1;
        col = lastPos.getColumn() + 1;
        while (row >= 0 && col < SIZE && board[row][col] == player) {
            counter++;
            if (counter == NEED_TO_WIN)
                return true;
            row--;
            col++;
        }

        row = lastPos.getRow() + 1;
        col = lastPos.getColumn() - 1;
        while (row < SIZE && col >= 0 && board[row][col] == player) {
            counter++;
            if (counter == NEED_TO_WIN)
                return true;
            row++;
            col--;
        }

        // Check the backslash shape: "\"
        counter = 1;

        row = lastPos.getRow() - 1;
        col = lastPos.getColumn() - 1;
        while (row >= 0 && col >= 0 && board[row][col] == player) {
            counter++;
            if (counter == NEED_TO_WIN)
                return true;
            row--;
            col--;
        }

        row = lastPos.getRow() + 1;
        col = lastPos.getColumn() + 1;
        while (row < SIZE && col < SIZE && board[row][col] == player) {
            counter++;
            if (counter == NEED_TO_WIN)
                return true;
            row++;
            col++;
        }

        return false;
    }
}
