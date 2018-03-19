package cpsc2150.homeworks.hw3;

/**
 * IGameBoard represents a 2-dimensional gameboard that has characters
 * on it as markers. No space on the board can have multiple
 * players, and there can be a clear winner. Board is NUM_ROWS x
 * NUM_COLS in size. Indexing of the gameboard starts in the top left
 * corner with 0,0 and goes to NUM_ROWS-1, NUM_COLS-1.
 *
 * Initialization ensures: the Board does not have any markers on it
 * Defines: NUM_ROWS: Z
 * Defines: NUM_COLS: Z
 * Constraints: 0< NUM_ROWS <= MAX_SIZE
 *              0< NUM_COLS <= MAX_SIZE
 */
public interface IGameBoard {
    int MAX_SIZE = 100;

    /**
     * returns true if the position specified in pos is available and valid,
     * false otherwise
     *
     * @param pos is the position that needs to be check if it's available
     * @requires pos is not NULL
     * @ensures board's elements will not change
     *          and checkSpace returns true iff board[pos.getRow()][pos.getColumn()] == ' '
     *          and iff 0 <= pos.getRow() < NUM_ROWS and 0 <= pos.getColumn() < NUM_COLS
     * @return true if there's an available space and valid
     */
    boolean checkSpace(BoardPosition pos);

    /**
     * places the character in marker on the position specified by
     * marker
     *
     * @param marker is the position needs to be marked in the board.
     * @requires marker is not NULL
     *           and checkSpace(lastPos) == true
     * @ensures board[marker.getRow()][marker.getColumn()] = marker.getPlayer();
     *          board at row = marker.getRow(), column = marker.getColumn() will
     *          be marked with marker.getPlayer()
     */
    void placeMarker(BoardPosition lastPos);

    /**
     * this function will check to see if the lastPos placed resulted
     * in a winner. If so it will return true, otherwise false.
     *
     * @param lastPos is the position that needs to be checked
     * @requires lastPos is not NULL
     *           and checkSpace == True
     *           and 0 <= lastPos.getRow() < NUM_ROWS and 0 <= lastPos.getColumn() < NUM_COLS
     * @ensures board's elements will not change
     *          and checkForWinner returns true if the lastPos makes the player
     *          who makes the move wins
     * @return true if the lastPos wins the game
     */
    boolean checkForWinner(BoardPosition lastPos);

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
    boolean checkForDraw();

}
