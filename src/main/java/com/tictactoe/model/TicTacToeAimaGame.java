package com.tictactoe.model;

import com.tictactoe.model.Move;
import aima.core.search.adversarial.Game;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeAimaGame implements Game<String[][], Move, String> {

    private final String computer;
    private final String human;

    public TicTacToeAimaGame(String computer, String human) {
        this.computer = computer;
        this.human = human;
    }

    @Override
    public List<Move> getActions(String[][] state) {
        List<Move> moves = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] == null || state[i][j].isEmpty()) {
                    moves.add(new Move(i, j));
                }
            }
        }
        return moves;
    }

    @Override
    public String getPlayer(String[][] state) {
        int xCount = 0, oCount = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if ("X".equals(state[i][j])) xCount++;
                else if ("O".equals(state[i][j])) oCount++;
        return xCount > oCount ? computer : human;
    }

    @Override
    public String[][] getResult(String[][] state, Move action) {
        String[][] newState = new String[3][3];
        for (int i = 0; i < 3; i++)
            System.arraycopy(state[i], 0, newState[i], 0, 3);
        String player = getPlayer(state);
        newState[action.getRow()][action.getCol()] = player;
        return newState;
    }

    @Override
    public boolean isTerminal(String[][] state) {
        return getWinner(state) != null || getActions(state).isEmpty();
    }

    @Override
    public double getUtility(String[][] state, String player) {
        String winner = getWinner(state);
        if (winner == null) return 0;
        if (winner.equals(player)) return 1;
        if ("Tie".equals(winner)) return 0;
        return -1;
    }

    @Override
    public String[][] getInitialState() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInitialState'");
    }

    @Override
    public String[] getPlayers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayers'");
    }

    public String getWinner(String[][] board) {
        // Check rows
        for (int i = 0; i < 3; i++)
            if (board[i][0] != null && !board[i][0].isEmpty() &&
                board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]))
                return board[i][0];
        // Check columns
        for (int j = 0; j < 3; j++)
            if (board[0][j] != null && !board[0][j].isEmpty() &&
                board[0][j].equals(board[1][j]) && board[1][j].equals(board[2][j]))
                return board[0][j];
        // Check diagonals
        if (board[0][0] != null && !board[0][0].isEmpty() &&
            board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]))
            return board[0][0];
        if (board[0][2] != null && !board[0][2].isEmpty() &&
            board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]))
            return board[0][2];
        // Tie
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == null || board[i][j].isEmpty())
                    return null;
        return "Tie";
    }

}
