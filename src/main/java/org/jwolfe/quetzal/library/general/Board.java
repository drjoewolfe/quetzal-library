package org.jwolfe.quetzal.library.general;

import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.Arrays;
import java.util.Objects;

public class Board {

    public int[][] getBoard() {
        return board;
    }

    int[][] board;

    public int getRowCount() {
        return rowCount;
    }

    int rowCount;

    public int getColumnCount() {
        return columnCount;
    }

    int columnCount;

    public int getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(int defaultValue) {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (board[i][j] == this.defaultValue) {
                    board[i][j] = defaultValue;
                }
            }
        }

        this.defaultValue = defaultValue;
    }

    int defaultValue = 0;

    public Board(int size) {
        this(size, size);
    }

    public Board(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;

        this.board = new int[rowCount][columnCount];
        Utilities.fillArray(this.board, defaultValue);
    }

    public Board(int[][] board) {
        set(board);
    }

    public void set(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        int rowCount = board.length;
        int columnCount = board[0].length;
        for (var row : board) {
            if (row.length != columnCount) {
                return;
            }
        }

        this.rowCount = rowCount;
        this.columnCount = columnCount;

        this.board = new int[rowCount][columnCount];
        for (int i = 0; i < this.rowCount; i++) {
            for (int j = 0; j < this.columnCount; j++) {
                this.board[i][j] = board[i][j];
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board1 = (Board) o;
        return rowCount == board1.rowCount &&
                columnCount == board1.columnCount &&
                defaultValue == board1.defaultValue &&
                Arrays.deepEquals(board, board1.board);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(rowCount, columnCount, defaultValue);
        result = 31 * result + Arrays.hashCode(board);
        return result;
    }
}
