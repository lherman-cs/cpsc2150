package cpsc2150.homeworks.hw1;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Lukas Herman(lukash) on 03/01/2018.
 */
public class TestGameBoard {
    private String buildBoard(char[][] board){
        StringBuilder builder = new StringBuilder();

        builder.append(" ");
        for (int col = 0; col < board[0].length; col++)
            builder.append(" " + col);
        builder.append("\n");

        for (int row = 0; row < board.length; row++) {
            builder.append(row + "|");
            for (int col = 0; col < board[0].length; col++)
                builder.append(board[row][col] + "|");
            builder.append("\n");
        }

        return builder.toString();
    }


    /**
     * testCheckSpace starts here
     *
     * There are 3 categories:
     *  - row (out-of-bound, valid)
     *  - col (out-of-bound, valid)
     *  - empty (yes or no)
     *
     * Total Cases = 5 cases (3 cases are excluded because they're impossible)
     */
    @Test
    public void testCheckSpace_ROutOfBound_COutOfBound_Empty(){
        BoardPosition pos = new BoardPosition(-1, -1, 'x');
        GameBoard board = new GameBoard();

        assertFalse(board.checkSpace(pos));
    }

    @Test
    public void testCheckSpace_ROutOfBound_CValid_Empty(){
        BoardPosition pos = new BoardPosition(-1, 5, 'x');
        GameBoard board = new GameBoard();

        assertFalse(board.checkSpace(pos));
    }

    @Test
    public void testCheckSpace_RValid_COutOfBound_Empty(){
        BoardPosition pos = new BoardPosition(4, -1, 'x');
        GameBoard board = new GameBoard();

        assertFalse(board.checkSpace(pos));
    }


    @Test
    public void testCheckSpace_RValid_CValid_Empty(){
        BoardPosition pos = new BoardPosition(4, 2, 'x');
        GameBoard board = new GameBoard();

        assertTrue(board.checkSpace(pos));
    }

    @Test
    public void testCheckSpace_RValid_CValid_NotEmpty(){
        BoardPosition pos = new BoardPosition(2, 3, 'x');
        GameBoard board = new GameBoard();
        board.placeMaker(pos);

        assertFalse(board.checkSpace(pos));
    }


    /**
     * testPlaceMarker starts here
     *
     * First set of categories:
     *  - row (lower-edge or upper-edge)
     *  - col (lower-edge or upper-edge)
     *
     * One routine cases:
     *  - row (valid within the boundary)
     *  - col (valid within the boundary)
     *
     * Second set of categories:
     *  - single (yes or no)
     *  - replace (yes or no)
     *
     * Total Cases = 9 cases
     */
    @Test
    public void testPlaceMarker_R0_C0(){
        int r = 0, c = 0;

        // Create an expected board
        char[][] expectedBoard = new char[8][8];
        for(int r_c = 0; r_c < 8; r_c++)
            for (int c_c = 0; c_c < 8; c_c++)
                expectedBoard[r_c][c_c] = ' ';
        expectedBoard[r][c] = 'x';
        String expected = buildBoard(expectedBoard);

        BoardPosition pos = new BoardPosition(r, c, 'x');
        GameBoard actualBoard = new GameBoard();
        actualBoard.placeMaker(pos);

        assertEquals(expected, actualBoard.toString());
    }

    @Test
    public void testPlaceMarker_R0_C7(){
        int r = 0, c = 7;

        // Create an expected board
        char[][] expectedBoard = new char[8][8];
        for(int r_c = 0; r_c < 8; r_c++)
            for (int c_c = 0; c_c < 8; c_c++)
                expectedBoard[r_c][c_c] = ' ';
        expectedBoard[r][c] = 'x';
        String expected = buildBoard(expectedBoard);

        BoardPosition pos = new BoardPosition(r, c, 'x');
        GameBoard actualBoard = new GameBoard();
        actualBoard.placeMaker(pos);

        assertEquals(expected, actualBoard.toString());
    }

    @Test
    public void testPlaceMarker_R7_C0(){
        int r = 7, c = 0;

        // Create an expected board
        char[][] expectedBoard = new char[8][8];
        for(int r_c = 0; r_c < 8; r_c++)
            for (int c_c = 0; c_c < 8; c_c++)
                expectedBoard[r_c][c_c] = ' ';
        expectedBoard[r][c] = 'x';
        String expected = buildBoard(expectedBoard);

        BoardPosition pos = new BoardPosition(r, c, 'x');
        GameBoard actualBoard = new GameBoard();
        actualBoard.placeMaker(pos);

        assertEquals(expected, actualBoard.toString());
    }

    @Test
    public void testPlaceMarker_R7_C7(){
        int r = 7, c = 7;

        // Create an expected board
        char[][] expectedBoard = new char[8][8];
        for(int r_c = 0; r_c < 8; r_c++)
            for (int c_c = 0; c_c < 8; c_c++)
                expectedBoard[r_c][c_c] = ' ';
        expectedBoard[r][c] = 'x';
        String expected = buildBoard(expectedBoard);

        BoardPosition pos = new BoardPosition(r, c, 'x');
        GameBoard actualBoard = new GameBoard();
        actualBoard.placeMaker(pos);

        assertEquals(expected, actualBoard.toString());
    }

    @Test
    public void testPlaceMarker_R3_C5(){
        int r = 3, c = 5;

        // Create an expected board
        char[][] expectedBoard = new char[8][8];
        for(int r_c = 0; r_c < 8; r_c++)
            for (int c_c = 0; c_c < 8; c_c++)
                expectedBoard[r_c][c_c] = ' ';
        expectedBoard[r][c] = 'x';
        String expected = buildBoard(expectedBoard);

        BoardPosition pos = new BoardPosition(r, c, 'x');
        GameBoard actualBoard = new GameBoard();
        actualBoard.placeMaker(pos);

        assertEquals(expected, actualBoard.toString());
    }

    @Test
    public void testPlaceMarker_Single(){
        int r = 4, c = 2;

        // Create an expected board
        char[][] expectedBoard = new char[8][8];
        for(int r_c = 0; r_c < 8; r_c++)
            for (int c_c = 0; c_c < 8; c_c++)
                expectedBoard[r_c][c_c] = ' ';
        expectedBoard[r][c] = 'x';
        String expected = buildBoard(expectedBoard);

        BoardPosition pos = new BoardPosition(r, c, 'x');
        GameBoard actualBoard = new GameBoard();
        actualBoard.placeMaker(pos);

        assertEquals(expected, actualBoard.toString());
    }

    @Test
    public void testPlaceMarker_Single_Replace(){
        int r = 4, c = 2;

        // Create an expected board
        char[][] expectedBoard = new char[8][8];
        for(int r_c = 0; r_c < 8; r_c++)
            for (int c_c = 0; c_c < 8; c_c++)
                expectedBoard[r_c][c_c] = ' ';
        expectedBoard[r][c] = 'x';
        expectedBoard[r][c] = 'z';
        String expected = buildBoard(expectedBoard);

        BoardPosition pos = new BoardPosition(r, c, 'x');
        BoardPosition pos2 = new BoardPosition(r, c, 'z');
        GameBoard actualBoard = new GameBoard();
        actualBoard.placeMaker(pos);
        actualBoard.placeMaker(pos2);

        assertEquals(expected, actualBoard.toString());
    }

    @Test
    public void testPlaceMarker_Multiple(){
        int r1 = 4, c1 = 2;
        int r2 = 7, c2 = 1;

        // Create an expected board
        char[][] expectedBoard = new char[8][8];
        for(int r_c = 0; r_c < 8; r_c++)
            for (int c_c = 0; c_c < 8; c_c++)
                expectedBoard[r_c][c_c] = ' ';
        expectedBoard[r1][c1] = 'x';
        expectedBoard[r2][c2] = 'x';
        String expected = buildBoard(expectedBoard);

        BoardPosition pos = new BoardPosition(r1, c1, 'x');
        BoardPosition pos2 = new BoardPosition(r2, c2, 'x');
        GameBoard actualBoard = new GameBoard();
        actualBoard.placeMaker(pos);
        actualBoard.placeMaker(pos2);

        assertEquals(expected, actualBoard.toString());
    }

    @Test
    public void testPlaceMarker_Multiple_Replace(){
        int r1 = 4, c1 = 2;
        int r2 = 7, c2 = 1;

        // Create an expected board
        char[][] expectedBoard = new char[8][8];
        for(int r_c = 0; r_c < 8; r_c++)
            for (int c_c = 0; c_c < 8; c_c++)
                expectedBoard[r_c][c_c] = ' ';
        expectedBoard[r1][c1] = 'x';
        expectedBoard[r2][c2] = 'x';
        expectedBoard[r1][c1] = 'z';
        String expected = buildBoard(expectedBoard);

        BoardPosition pos = new BoardPosition(r1, c1, 'x');
        BoardPosition pos2 = new BoardPosition(r2, c2, 'x');
        BoardPosition pos3 = new BoardPosition(r1, c1, 'z');
        GameBoard actualBoard = new GameBoard();
        actualBoard.placeMaker(pos);
        actualBoard.placeMaker(pos2);
        actualBoard.placeMaker(pos3);

        assertEquals(expected, actualBoard.toString());
    }


    /**
     * testCheckForWinner starts here
     *
     * There are 3 categories:
     *  - direction (hor, ver, slash, and backslash)
     *  - edge (edge or not edge)
     *  - middle (middle or not middle)
     *  - win (win or close-to-win)
     *
     * Total Cases = 32 cases
     */
    @Test
    public void testCheckForWinner_Horizontal_Edge_Middle_Win(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(0, 0, 'x');
        BoardPosition pos2 = new BoardPosition(0, 1, 'x');
        BoardPosition pos3 = new BoardPosition(0, 2, 'x');
        BoardPosition pos4 = new BoardPosition(0, 3, 'x');
        BoardPosition pos5 = new BoardPosition(0, 4, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos4);
        board.placeMaker(pos5);

        assertTrue(board.checkForWinner(pos3));
    }

    @Test
    public void testCheckForWinner_Horizontal_Edge_Middle_CloseToWin(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(0, 0, 'x');
        BoardPosition pos2 = new BoardPosition(0, 1, 'x');
        BoardPosition pos3 = new BoardPosition(0, 2, 'x');
        BoardPosition pos4 = new BoardPosition(0, 3, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos4);

        assertFalse(board.checkForWinner(pos3));
    }


    @Test
    public void testCheckForWinner_Horizontal_Edge_NotMiddle_Win(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(0, 0, 'x');
        BoardPosition pos2 = new BoardPosition(0, 1, 'x');
        BoardPosition pos3 = new BoardPosition(0, 2, 'x');
        BoardPosition pos4 = new BoardPosition(0, 3, 'x');
        BoardPosition pos5 = new BoardPosition(0, 4, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos3);
        board.placeMaker(pos4);

        assertTrue(board.checkForWinner(pos5));
    }

    @Test
    public void testCheckForWinner_Horizontal_Edge_NotMiddle_CloseToWin(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(0, 0, 'x');
        BoardPosition pos2 = new BoardPosition(0, 1, 'x');
        BoardPosition pos3 = new BoardPosition(0, 2, 'x');
        BoardPosition pos4 = new BoardPosition(0, 3, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos3);

        assertFalse(board.checkForWinner(pos4));
    }

    @Test
    public void testCheckForWinner_Horizontal_NotEdge_Middle_Win(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(3, 1, 'x');
        BoardPosition pos2 = new BoardPosition(3, 2, 'x');
        BoardPosition pos3 = new BoardPosition(3, 3, 'x');
        BoardPosition pos4 = new BoardPosition(3, 4, 'x');
        BoardPosition pos5 = new BoardPosition(3, 5, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos4);
        board.placeMaker(pos5);

        assertTrue(board.checkForWinner(pos3));
    }


    @Test
    public void testCheckForWinner_Horizontal_NotEdge_Middle_CloseToWin(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(3, 1, 'x');
        BoardPosition pos2 = new BoardPosition(3, 2, 'x');
        BoardPosition pos3 = new BoardPosition(3, 3, 'x');
        BoardPosition pos4 = new BoardPosition(3, 4, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos4);

        assertFalse(board.checkForWinner(pos3));
    }

    @Test
    public void testCheckForWinner_Horizontal_NotEdge_NotMiddle_Win(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(3, 1, 'x');
        BoardPosition pos2 = new BoardPosition(3, 2, 'x');
        BoardPosition pos3 = new BoardPosition(3, 3, 'x');
        BoardPosition pos4 = new BoardPosition(3, 4, 'x');
        BoardPosition pos5 = new BoardPosition(3, 5, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos3);
        board.placeMaker(pos4);

        assertTrue(board.checkForWinner(pos5));
    }

    @Test
    public void testCheckForWinner_Horizontal_NotEdge_NotMiddle_CloseToWin(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(3, 1, 'x');
        BoardPosition pos2 = new BoardPosition(3, 2, 'x');
        BoardPosition pos3 = new BoardPosition(3, 3, 'x');
        BoardPosition pos4 = new BoardPosition(3, 4, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos3);

        assertFalse(board.checkForWinner(pos4));
    }

    @Test
    public void testCheckForWinner_Vertical_Edge_Middle_Win(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(1, 7, 'x');
        BoardPosition pos2 = new BoardPosition(2, 7, 'x');
        BoardPosition pos3 = new BoardPosition(3, 7, 'x');
        BoardPosition pos4 = new BoardPosition(4, 7, 'x');
        BoardPosition pos5 = new BoardPosition(5, 7, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos4);
        board.placeMaker(pos5);

        assertTrue(board.checkForWinner(pos3));
    }

    @Test
    public void testCheckForWinner_Vertical_Edge_Middle_CloseToWin(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(1, 7, 'x');
        BoardPosition pos2 = new BoardPosition(2, 7, 'x');
        BoardPosition pos3 = new BoardPosition(3, 7, 'x');
        BoardPosition pos4 = new BoardPosition(4, 7, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos4);

        assertFalse(board.checkForWinner(pos3));
    }

    @Test
    public void testCheckForWinner_Vertical_Edge_NotMiddle_Win(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(1, 7, 'x');
        BoardPosition pos2 = new BoardPosition(2, 7, 'x');
        BoardPosition pos3 = new BoardPosition(3, 7, 'x');
        BoardPosition pos4 = new BoardPosition(4, 7, 'x');
        BoardPosition pos5 = new BoardPosition(5, 7, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos3);
        board.placeMaker(pos4);

        assertTrue(board.checkForWinner(pos5));
    }

    @Test
    public void testCheckForWinner_Vertical_Edge_NotMiddle_CloseToWin(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(1, 7, 'x');
        BoardPosition pos2 = new BoardPosition(2, 7, 'x');
        BoardPosition pos3 = new BoardPosition(3, 7, 'x');
        BoardPosition pos4 = new BoardPosition(4, 7, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos3);

        assertFalse(board.checkForWinner(pos4));
    }

    @Test
    public void testCheckForWinner_Vertical_NotEdge_Middle_Win(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(1, 3, 'x');
        BoardPosition pos2 = new BoardPosition(2, 3, 'x');
        BoardPosition pos3 = new BoardPosition(3, 3, 'x');
        BoardPosition pos4 = new BoardPosition(4, 3, 'x');
        BoardPosition pos5 = new BoardPosition(5, 3, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos4);
        board.placeMaker(pos5);

        assertTrue(board.checkForWinner(pos3));
    }

    @Test
    public void testCheckForWinner_Vertical_NotEdge_Middle_CloseToWin(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(1, 3, 'x');
        BoardPosition pos2 = new BoardPosition(2, 3, 'x');
        BoardPosition pos3 = new BoardPosition(3, 3, 'x');
        BoardPosition pos4 = new BoardPosition(4, 3, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos4);

        assertFalse(board.checkForWinner(pos3));
    }

    @Test
    public void testCheckForWinner_Vertical_NotEdge_NotMiddle_Win(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(1, 3, 'x');
        BoardPosition pos2 = new BoardPosition(2, 3, 'x');
        BoardPosition pos3 = new BoardPosition(3, 3, 'x');
        BoardPosition pos4 = new BoardPosition(4, 3, 'x');
        BoardPosition pos5 = new BoardPosition(5, 3, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos3);
        board.placeMaker(pos4);

        assertTrue(board.checkForWinner(pos5));
    }

    @Test
    public void testCheckForWinner_Vertical_NotEdge_NotMiddle_CloseToWin(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(1, 3, 'x');
        BoardPosition pos2 = new BoardPosition(2, 3, 'x');
        BoardPosition pos3 = new BoardPosition(3, 3, 'x');
        BoardPosition pos4 = new BoardPosition(4, 3, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos3);

        assertFalse(board.checkForWinner(pos4));
    }

    @Test
    public void testCheckForWinner_Slash_Edge_Middle_Win(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(0, 7, 'x');
        BoardPosition pos2 = new BoardPosition(1, 6, 'x');
        BoardPosition pos3 = new BoardPosition(2, 5, 'x');
        BoardPosition pos4 = new BoardPosition(3, 4, 'x');
        BoardPosition pos5 = new BoardPosition(4, 3, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos4);
        board.placeMaker(pos5);

        assertTrue(board.checkForWinner(pos3));
    }


    @Test
    public void testCheckForWinner_Slash_Edge_Middle_CloseToWin(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(0, 7, 'x');
        BoardPosition pos2 = new BoardPosition(1, 6, 'x');
        BoardPosition pos3 = new BoardPosition(2, 5, 'x');
        BoardPosition pos4 = new BoardPosition(3, 4, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos4);

        assertFalse(board.checkForWinner(pos3));
    }

    @Test
    public void testCheckForWinner_Slash_Edge_NotMiddle_Win(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(0, 7, 'x');
        BoardPosition pos2 = new BoardPosition(1, 6, 'x');
        BoardPosition pos3 = new BoardPosition(2, 5, 'x');
        BoardPosition pos4 = new BoardPosition(3, 4, 'x');
        BoardPosition pos5 = new BoardPosition(4, 3, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos3);
        board.placeMaker(pos4);

        assertTrue(board.checkForWinner(pos5));
    }

    @Test
    public void testCheckForWinner_Slash_Edge_NotMiddle_CloseToWin(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(0, 7, 'x');
        BoardPosition pos2 = new BoardPosition(1, 6, 'x');
        BoardPosition pos3 = new BoardPosition(2, 5, 'x');
        BoardPosition pos4 = new BoardPosition(3, 4, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos3);

        assertFalse(board.checkForWinner(pos4));
    }

    @Test
    public void testCheckForWinner_Slash_NotEdge_Middle_Win(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(2, 5, 'x');
        BoardPosition pos2 = new BoardPosition(3, 4, 'x');
        BoardPosition pos3 = new BoardPosition(4, 3, 'x');
        BoardPosition pos4 = new BoardPosition(5, 2, 'x');
        BoardPosition pos5 = new BoardPosition(6, 1, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos4);
        board.placeMaker(pos5);

        assertTrue(board.checkForWinner(pos3));
    }

    @Test
    public void testCheckForWinner_Slash_NotEdge_Middle_CloseToWin(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(2, 5, 'x');
        BoardPosition pos2 = new BoardPosition(3, 4, 'x');
        BoardPosition pos3 = new BoardPosition(4, 3, 'x');
        BoardPosition pos4 = new BoardPosition(5, 2, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos4);

        assertFalse(board.checkForWinner(pos3));
    }

    @Test
    public void testCheckForWinner_Slash_NotEdge_NotMiddle_Win(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(2, 5, 'x');
        BoardPosition pos2 = new BoardPosition(3, 4, 'x');
        BoardPosition pos3 = new BoardPosition(4, 3, 'x');
        BoardPosition pos4 = new BoardPosition(5, 2, 'x');
        BoardPosition pos5 = new BoardPosition(6, 1, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos3);
        board.placeMaker(pos4);

        assertTrue(board.checkForWinner(pos5));
    }

    @Test
    public void testCheckForWinner_Slash_NotEdge_NotMiddle_CloseToWin(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(2, 5, 'x');
        BoardPosition pos2 = new BoardPosition(3, 4, 'x');
        BoardPosition pos3 = new BoardPosition(4, 3, 'x');
        BoardPosition pos4 = new BoardPosition(5, 2, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos3);

        assertFalse(board.checkForWinner(pos4));
    }



    @Test
    public void testCheckForWinner_BackSlash_Edge_Middle_Win(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(0, 3, 'x');
        BoardPosition pos2 = new BoardPosition(1, 4, 'x');
        BoardPosition pos3 = new BoardPosition(2, 5, 'x');
        BoardPosition pos4 = new BoardPosition(3, 6, 'x');
        BoardPosition pos5 = new BoardPosition(4, 7, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos4);
        board.placeMaker(pos5);

        assertTrue(board.checkForWinner(pos3));
    }


    @Test
    public void testCheckForWinner_BackSlash_Edge_Middle_CloseToWin(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(0, 3, 'x');
        BoardPosition pos2 = new BoardPosition(1, 4, 'x');
        BoardPosition pos3 = new BoardPosition(2, 5, 'x');
        BoardPosition pos4 = new BoardPosition(3, 6, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos4);

        assertFalse(board.checkForWinner(pos3));
    }

    @Test
    public void testCheckForWinner_BackSlash_Edge_NotMiddle_Win(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(0, 3, 'x');
        BoardPosition pos2 = new BoardPosition(1, 4, 'x');
        BoardPosition pos3 = new BoardPosition(2, 5, 'x');
        BoardPosition pos4 = new BoardPosition(3, 6, 'x');
        BoardPosition pos5 = new BoardPosition(4, 7, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos3);
        board.placeMaker(pos4);

        assertTrue(board.checkForWinner(pos5));
    }

    @Test
    public void testCheckForWinner_BackSlash_Edge_NotMiddle_CloseToWin(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(0, 3, 'x');
        BoardPosition pos2 = new BoardPosition(1, 4, 'x');
        BoardPosition pos3 = new BoardPosition(2, 5, 'x');
        BoardPosition pos4 = new BoardPosition(3, 6, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos3);

        assertFalse(board.checkForWinner(pos4));
    }

    @Test
    public void testCheckForWinner_BackSlash_NotEdge_Middle_Win(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(2, 1, 'x');
        BoardPosition pos2 = new BoardPosition(3, 2, 'x');
        BoardPosition pos3 = new BoardPosition(4, 3, 'x');
        BoardPosition pos4 = new BoardPosition(5, 4, 'x');
        BoardPosition pos5 = new BoardPosition(6, 5, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos4);
        board.placeMaker(pos5);

        assertTrue(board.checkForWinner(pos3));
    }

    @Test
    public void testCheckForWinner_BackSlash_NotEdge_Middle_CloseToWin(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(2, 1, 'x');
        BoardPosition pos2 = new BoardPosition(3, 2, 'x');
        BoardPosition pos3 = new BoardPosition(4, 3, 'x');
        BoardPosition pos4 = new BoardPosition(5, 4, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos4);

        assertFalse(board.checkForWinner(pos3));
    }

    @Test
    public void testCheckForWinner_BackSlash_NotEdge_NotMiddle_Win(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(2, 1, 'x');
        BoardPosition pos2 = new BoardPosition(3, 2, 'x');
        BoardPosition pos3 = new BoardPosition(4, 3, 'x');
        BoardPosition pos4 = new BoardPosition(5, 4, 'x');
        BoardPosition pos5 = new BoardPosition(6, 5, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos3);
        board.placeMaker(pos4);

        assertTrue(board.checkForWinner(pos5));
    }

    @Test
    public void testCheckForWinner_BackSlash_NotEdge_NotMiddle_CloseToWin(){
        GameBoard board = new GameBoard();

        BoardPosition pos1 = new BoardPosition(2, 1, 'x');
        BoardPosition pos2 = new BoardPosition(3, 2, 'x');
        BoardPosition pos3 = new BoardPosition(4, 3, 'x');
        BoardPosition pos4 = new BoardPosition(5, 4, 'x');

        board.placeMaker(pos1);
        board.placeMaker(pos2);
        board.placeMaker(pos3);

        assertFalse(board.checkForWinner(pos4));
    }


    /**
     * testCheckForDraw starts here
     *
     * There are 2 categories:
     *  - Filled (full or almost or no)
     *  - English (yes or no)
     *
     * One unique test:
     *  - The board is blank
     *
     *
     * Total Cases = 7 cases
     */
    @Test
    public void testCheckForDraw_Blank(){
        GameBoard board = new GameBoard();

        assertFalse(board.checkForDraw());
    }

    @Test
    public void testCheckForDraw_Filled_English() {
        Random rand = new Random();
        char randChar;
        GameBoard board = new GameBoard();

        for (int r = 0; r < 8; r++){
            for (int c = 0; c < 8; c++){
                randChar = (char) rand.nextInt(26);
                randChar += (int)'a';

                board.placeMaker(new BoardPosition(r, c, randChar));
            }
        }

        assertTrue(board.checkForDraw());
    }

    @Test
    public void testCheckForDraw_AlmostFilled_English() {
        Random rand = new Random();
        char randChar;
        GameBoard board = new GameBoard();

        for (int r = 0; r < 8; r++){
            for (int c = 0; c < 8; c++){
                if(r != 7 || c != 7){
                    randChar = (char) rand.nextInt(26);
                    randChar += (int)'a';

                    board.placeMaker(new BoardPosition(r, c, randChar));
                }
            }
        }

        assertFalse(board.checkForDraw());
    }

    @Test
    public void testCheckForDraw_NotFilled_English(){
        GameBoard board = new GameBoard();
        BoardPosition pos = new BoardPosition(0, 0, 'x');
        board.placeMaker(pos);

        assertFalse(board.checkForDraw());
    }

    @Test
    public void testCheckForDraw_Filled_NotEnglish(){
        Random rand = new Random();
        GameBoard board = new GameBoard();

        for (int r = 0; r < 8; r++)
            for (int c = 0; c < 8; c++)
                board.placeMaker(new BoardPosition(r, c, (char)rand.nextInt()));

        assertTrue(board.checkForDraw());
    }

    @Test
    public void testCheckForDraw_AlmostFilled_NotEnglish(){
        Random rand = new Random();
        GameBoard board = new GameBoard();

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (r != 7 || c != 7)
                    board.placeMaker(new BoardPosition(r, c, (char) rand.nextInt()));
            }
        }

        assertFalse(board.checkForDraw());
    }

    @Test
    public void testCheckForDraw_NotFilled_NotEnglish(){
        GameBoard board = new GameBoard();
        BoardPosition pos = new BoardPosition(7, 7, (char)999);
        board.placeMaker(pos);

        assertFalse(board.checkForDraw());
    }
}
