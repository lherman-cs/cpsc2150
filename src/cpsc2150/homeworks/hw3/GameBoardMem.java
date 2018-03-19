package cpsc2150.homeworks.hw3;

/**
 * Name         : Lukas Herman (lukash)
 * Created on   : 03/18/17
 */

import java.util.*;
import java.util.function.Predicate;

/**
 * This is an entity class which it's sole responsibility is to
 * keep players' marks in the board and some logics of the game. This implementation
 * uses Map to reduce memory usage.
 *
 * Correspondence: this = board
 *                 NUM_ROWS = numRows
 *                 NUM_COLS = numCols
 *
 * @invariant this.numRows, this.numCols, and this.needToWin will never change
 *            and board will never be destroyed or recreated
 */
public class GameBoardMem implements IGameBoard {

    private final int numRows;
    private final int numCols;
    private final int needToWin;
    private final Map<Character, ArrayList<BoardPosition>> board;

    /**
     * Construct an instance and set the properties accordingly
     *
     * @ensures this.numRows = numRows
     *          and this.numCols = numCols
     *          and this.needToWin = needToWin
     *          and board = new HashMap
     */
    GameBoardMem(int numRows, int numCols, int needToWin) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.needToWin = needToWin;
        board = new HashMap<>();
    }

    @Override
    public boolean checkSpace(BoardPosition pos) {
        // Check if pos is out of bound
        if ((pos.getRow() < 0 || pos.getRow() >= this.numRows)
                || (pos.getColumn() < 0 || pos.getColumn() >= this.numCols))
            return false;

        // Check if there is a free space
        Predicate<Map.Entry<Character, ArrayList<BoardPosition>>> notFree = e -> {
            Character player = e.getKey();
            ArrayList<BoardPosition> list = e.getValue();
            return list.contains(new BoardPosition(pos.getRow(), pos.getColumn(), player));
        };
        return this.board.entrySet().stream().noneMatch(notFree);
    }

    @Override
    public void placeMarker(BoardPosition lastPos) {
        ArrayList<BoardPosition> currentList = board.getOrDefault(lastPos.getPlayer(), new ArrayList<>());
        currentList.add(lastPos);
        board.put(lastPos.getPlayer(), currentList);
    }

    @Override
    public boolean checkForWinner(BoardPosition lastPos) {
        return checkHorizontalWin(lastPos) || checkVerticalWin(lastPos) || checkDiagonalWin(lastPos);
    }

    @Override
    public boolean checkForDraw() {
        // Since the contract requires the client to call checkForWinner has to return false
        // and placeMarker has to always call checkForSpace, it's safe to count the number of BoardPosition in
        // board.
        int numPositions = board.values().stream().map(a -> a.size()).reduce(0, (a, b) -> a + b);
        return this.numRows * this.numCols == numPositions;
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
        Comparator<BoardPosition> comparator = (pos1, pos2) -> {
            int diff = pos1.getRow() - pos2.getRow();
            if (diff == 0)
                return pos1.getColumn() - pos2.getColumn();
            return diff;
        };

        List<BoardPosition> positions = new ArrayList<>();
        this.board.values().forEach(positions::addAll);
        positions.sort(comparator);

        StringBuilder builder = new StringBuilder();

        builder.append("  ");
        for (int col = 0; col < this.numCols; col++) {
            if (col < 10)
                builder.append(" ");
            builder.append(col + "|");
        }
        builder.append("\n");

        int lastRow = 0;
        int row;
        ArrayList<BoardPosition> cols = new ArrayList<>();
        for (BoardPosition pos : positions) {
            row = pos.getRow();

            if (lastRow < row) {
                builder.append(renderRow(lastRow++, cols));
                cols.clear();

                while (lastRow < row)
                    builder.append(renderRow(lastRow++, cols));
            }
            cols.add(pos);
        }

        if (lastRow < this.numRows) {
            builder.append(renderRow(lastRow++, cols));
            cols.clear();

            while (lastRow < this.numRows)
                builder.append(renderRow(lastRow++, cols));
        }

        builder.append("\n");

        return builder.toString();
    }

    /**
     * This is a helper function to render each row in the board
     * @param row is the row index to be rendered
     * @param cols are the board positions in row
     * @requires 0 <= row < this.numRows
     *           and cols != NULL
     *           and 0 <= every BoardPosition's row in cols < this.numRows
     *           and 0 <= every BoardPosition's col in cols < this.numCols
     * @ensures rendered row prints all the marked positions and empty spaces
     * @return rendered row
     */
    private String renderRow(int row, ArrayList<BoardPosition> cols) {
        StringBuilder builder = new StringBuilder();
        builder.append(row + "|");

        int lastCol = 0;
        for (BoardPosition pos : cols) {
            int col = pos.getColumn();
            while (lastCol++ < col)
                builder.append("  |");
            builder.append(pos.getPlayer() + " |");
        }

        while (lastCol++ < this.numCols)
            builder.append("  |");
        builder.append("\n");
        return builder.toString();
    }

    /**
     * An override method for comparing between two GameBoardMem's instances
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
        if (!(o instanceof GameBoardMem)) {
            return false;
        }

        // typecast o to BoardPosition so that we can compare data members
        GameBoardMem other = (GameBoardMem) o;

        // Compare the data members and return accordingly
        return this.board.equals(other.board);
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
        ArrayList<BoardPosition> currentList = board.getOrDefault(player, new ArrayList<>());

        // Counter starts at 1 because the lastPos is being positioned.
        int counter = 1;

        // Check the right side
        int col = lastPos.getColumn() + 1;
        while (col < this.numCols && currentList.contains(new BoardPosition(row, col, player))) {
            counter++;
            if (counter == this.needToWin)
                return true;
            col++;
        }

        // Check the left side
        col = lastPos.getColumn() - 1;
        while (col >= 0 && currentList.contains(new BoardPosition(row, col, player))) {
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
        ArrayList<BoardPosition> currentList = board.getOrDefault(player, new ArrayList<>());

        // Counter starts at 1 because the lastPos is being positioned.
        int counter = 1;

        // Check the lower side
        int row = lastPos.getRow() + 1;
        while (row < this.numRows && currentList.contains(new BoardPosition(row, col, player))) {
            counter++;
            if (counter == this.needToWin)
                return true;
            row++;
        }

        // Check the upper side
        row = lastPos.getRow() - 1;
        while (row >= 0 && currentList.contains(new BoardPosition(row, col, player))) {
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
        ArrayList<BoardPosition> currentList = board.getOrDefault(player, new ArrayList<>());

        // Counter starts at 1 because the lastPos is being positioned.
        int counter = 1;

        // Check the slash shape: "/"
        row = lastPos.getRow() - 1;
        col = lastPos.getColumn() + 1;
        while (row >= 0 && col < this.numCols && currentList.contains(new BoardPosition(row, col, player))) {
            counter++;
            if (counter == this.needToWin)
                return true;
            row--;
            col++;
        }

        row = lastPos.getRow() + 1;
        col = lastPos.getColumn() - 1;
        while (row < this.numRows && col >= 0 && currentList.contains(new BoardPosition(row, col, player))) {
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
        while (row >= 0 && col >= 0 && currentList.contains(new BoardPosition(row, col, player))) {
            counter++;
            if (counter == this.needToWin)
                return true;
            row--;
            col--;
        }

        row = lastPos.getRow() + 1;
        col = lastPos.getColumn() + 1;
        while (row < this.numRows && col < this.numCols && currentList.contains(new BoardPosition(row, col, player))) {
            counter++;
            if (counter == this.needToWin)
                return true;
            row++;
            col++;
        }

        return counter == this.needToWin;
    }
}
