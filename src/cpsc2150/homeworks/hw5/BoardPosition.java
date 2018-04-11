package cpsc2150.homeworks.hw5;

/**
 * Name         : Lukas Herman (lukash)
 * Created on   : 01/28/17 
 */

/**
 * This is an entity class which it's sole responsibility is to 
 * hold a player's position information in the GameBoardFast.
 * 
 * @invariant row >= 0
 *            and column >= 0
 *            and player is a valid UTF-8 char
 */
public class BoardPosition {
    private int row, column;
    private char player;

    /**
     * Construct an instance and set all the private properties
     * accordingly to the given input parameters
     * 
     * @param row is the row index of the player in the board
     * @param column is the column index of the player in the board
     * @param player is a player's mark on the board
     * @requires row >= 0
     *           and column >= 0
     *           and player is a valid UTF-8 char
     * @ensures this.row = row
     *          and this.column = column
     *          and this.player = player
     */
    public BoardPosition(int row, int column, char player) {
        this.row = row;
        this.column = column;
        this.player = player;
    }

    /**
     * A getter for row.
     * 
     * @ensures all the properties will stay the same
     *          and getRow returns the value of row
     * @return the value of row
     */
    public int getRow() {
        return row;
    }

    /**
     * A getter for column
     * 
     * @ensures all the properties will stay the same
     *          and getColumn returns the value of column
     * @return the value of column
     */
    public int getColumn() {
        return column;
    }

    /**
     * A getter for player
     * 
     * @ensures all the properties will stay the same
     *          and getPlayer returns the value of player
     * @return the value of player
     */
    public char getPlayer() {
        return player;
    }

    /**
     * An override method for comparing between two BoardPosition's instances
     * 
     * @param o is the other instance that needs to be compared
     * @requires o is an object
     *           and o is not NULL
     * @ensures all the properties will stay the same
     *          and equals returns true if the two instances are equal
     * @return true if the two instances are equal. Else, false
     * 
     */
    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (o == this)
            return true;

        // Return false if the object is not an instance of BoardPosition
        if (!(o instanceof BoardPosition))
            return false;

        // typecast o to BoardPosition so that we can compare data members
        BoardPosition other = (BoardPosition) o;

        // Compare the data members and return accordingly
        return this.row == other.row && this.column == other.column && this.player == other.player;
    }

    /**
     * An override method for outputting a humanreadable information
     * 
     * @requires row >= 0
     *           and column >= 0
     *           and player is a valid UTF-8 char
     * @ensures all the properties will stay the same
     *          and toString returns a human-readable information string
     * @return a human-readable information string
     */
    @Override
    public String toString() {
        return "Player " + player + " at position " + row + "," + column;
    }
}
