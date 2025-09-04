package com.tictactoe.model;

import lombok.Data;

@Data
public class Move {
    private int row;
    private int col;

    public Move() {}

    public Move(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return row == move.row && col == move.col;
    }

    @Override
    public int hashCode() {
        return 31 * row + col;
    }

    @Override
    public String toString() {
        return String.format("Move{row=%d, col=%d}", row, col);
    }
}
