package cpsc2150.homeworks.hw3;

import java.util.Arrays;

/**
 * Name         : Lukas Herman (lukash)
 * Created on   : 01/28/17 
 */

/**
 * This is an entity class which it's sole responsibility is to
 * keep players' marks in the board and some logics of the game.
 * 
 * Correspondence: this = board[0...numRows][0...numCols]. board[0][0] is top left
 *                 NUM_ROWS = numRows
 *                 NUM_COLS = numCols
 *                 
 * @invariant this.numRows, this.numCols, and this.needToWin will never change
 *            and board will never be destroyed or recreated
 */
public class GameBoardFast implements IGameBoard {
    private final int numRows;
    private final int numCols;
    private final int needToWin;
    private final char[][] board;

    /**
     * Construct an instance and initialize the 2-D array char board with ' '
     * 
     * @ensures this.numRows = numRows
     *          and this.numCols = numCols
     *          and this.needToWin = needToWin
     *          and board = a new 2-D char array filled with ' ' character
     */
    public GameBoardFast(int numRows, int numCols, int needToWin) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.needToWin = needToWin;

        // Initialize the board with ' ' on every law
        board = new char[this.numRows][this.numCols];
        for (int row = 0; row < this.numRows; row++)
            for (int col = 0; col < this.numCols; col++)
                board[row][col] = ' ';
    }

    public boolean checkSpace(BoardPosition pos) {
        return (pos.getRow() >= 0 && pos.getRow() < this.numRows)
                && (pos.getColumn() >= 0 && pos.getColumn() < this.numCols)
                && board[pos.getRow()][pos.getColumn()] == ' ';
    }

    public void placeMarker(BoardPosition lastPos) {
        board[lastPos.getRow()][lastPos.getColumn()] = lastPos.getPlayer();
    }

    public boolean checkForWinner(BoardPosition lastPos) {
        return checkHorizontalWin(lastPos) || checkVerticalWin(lastPos) || checkDiagonalWin(lastPos);
    }

    public boolean checkForDraw() {
        for (int row = 0; row < this.numRows; row++)
            for (int col = 0; col < this.numCols; col++)
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

        builder.append("  ");
        for (int col = 0; col < this.numCols; col++) {
            if (col < 10)
                builder.append(" ");
            builder.append(col + "|");
        }
        builder.append("\n");

        for (int row = 0; row < this.numRows; row++) {
            builder.append(row + "|");
            for (int col = 0; col < this.numCols; col++)
                builder.append(board[row][col] + " |");
            builder.append("\n");
        }

        return builder.toString();
    }

    /**
     * An override method for comparing between two GameBoardFast's instances
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

        // Return false if the object is not an instance of GameBoardFast
        if (!(o instanceof GameBoardFast)) {
            return false;
        }

        // typecast o to BoardPosition so that we can compare data members
        GameBoardFast other = (GameBoardFast) o;

        // Compare the data members and return accordingly
        return Arrays.deepEquals(this.board, other.board);
    }

    /**
     * checks to see if the last marker placed resulted in this.needToWin in a row
     * horizontally. Returns true if it does, otherwise false
     * 
     * @param lastPos is the position that needs to be checked
     * @requires lastPos is not NULL
     *           0 <= lastPos.getRow() < this.numRows and 0 <= lastPos.getColumn() < this.numCols
     * @ensures board's elements will not change
     *          and checkHorizontalWin returns true if the lastPos makes 
     *          this.needToWin in a row horizontally with the same lastPos' mark.
     * @return true if the lastPos makes this.needToWin in a row horizontally with the same 
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
        while (col < this.numCols && board[row][col] == player) {
            counter++;
            if (counter == this.needToWin)
                return true;
            col++;
        }

        // Check the left side
        col = lastPos.getColumn() - 1;
        while (col >= 0 && board[row][col] == player) {
            counter++;
            if (counter == this.needToWin)
                return true;
            col--;
        }

        return counter == this.needToWin;
    }

    /**
     * checks to see if the last marker placed resulted in this.needToWin in a row
     * vertically. Returns true if it does, otherwise false
     * 
     * @param lastPos is the position that needs to be checked
     * @requires lastPos is not NULL
     *           0 <= lastPos.getRow() < this.numRows and 0 <= lastPos.getColumn() < this.numCols
     * @ensures board's elements will not change
     *          and checkVerticalWin returns true if the lastPos makes 
     *          this.needToWin in a row vertically with the same lastPos' mark.
     * @return true if the lastPos makes this.needToWin in a row vertically with the same 
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
        while (row < this.numRows && board[row][col] == player) {
            counter++;
            if (counter == this.needToWin)
                return true;
            row++;
        }

        // Check the upper side
        row = lastPos.getRow() - 1;
        while (row >= 0 && board[row][col] == player) {
            counter++;
            if (counter == this.needToWin)
                return true;
            row--;
        }

        return counter == this.needToWin;
    }

    /**
     * checks to see if the last marker placed resulted in this.needToWin in a row
     * diagonally. Returns true if it does, otherwise false
     *
     * @param lastPos is the position that needs to be checked
     * @requires lastPos is not NULL
     *           0 <= lastPos.getRow() < this.numRows and 0 < lastPos.getColumn() < this.numCols
     * @ensures board's elements will not change
     *          and checkDiagonalWin returns true if the lastPos makes
     *          this.needToWin in a row diagonally with the same lastPos' mark
     * @return true if the lastPos makes this.needToWin in a row diagonally with
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
        while (row >= 0 && col < this.numCols && board[row][col] == player) {
            counter++;
            if (counter == this.needToWin)
                return true;
            row--;
            col++;
        }

        row = lastPos.getRow() + 1;
        col = lastPos.getColumn() - 1;
        while (row < this.numRows && col >= 0 && board[row][col] == player) {
            counter++;
            if (counter == this.needToWin)
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
            if (counter == this.needToWin)
                return true;
            row--;
            col--;
        }

        row = lastPos.getRow() + 1;
        col = lastPos.getColumn() + 1;
        while (row < this.numRows && col < this.numCols && board[row][col] == player) {
            counter++;
            if (counter == this.needToWin)
                return true;
            row++;
            col++;
        }

        return counter == this.needToWin;
    }
}
