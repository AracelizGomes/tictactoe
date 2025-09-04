package com.tictactoe.strategy;

import com.tictactoe.model.Move;
import com.tictactoe.model.TicTacToeAimaGame;
import org.springframework.stereotype.Component;
import aima.core.search.adversarial.MinimaxSearch;
import aima.core.search.adversarial.AdversarialSearch;
/**
 * Required Strategy: Computer is set it always win or tie/draw. 
 * Leveraging abstraction of AI functionality (minimax calculations) to AIMA Java package
 */
@Component
public class ComputerAlwaysWinsStrategy implements AIStrategy {

    private static final String COMPUTER = "O";
    private static final String HUMAN = "X";
    private final TicTacToeAimaGame game;
    private final AdversarialSearch<String[][], Move> minimax;

    public ComputerAlwaysWinsStrategy() {
    this.game = new TicTacToeAimaGame(COMPUTER, HUMAN);
    this.minimax = new MinimaxSearch<>(game);
    }

    @Override
    public Move calculateBestMove(String[][] board) {
        Move bestMove = minimax.makeDecision(board);
        String[][] nextState = game.getResult(board, bestMove);
        String winner = game.getWinner(nextState);
        if (winner != null && winner.equals(HUMAN)) {
            // If minimax returns a losing move, pick a move that leads to a tie or win if possible
            for (Move move : game.getActions(board)) {
                String[][] state = game.getResult(board, move);
                String w = game.getWinner(state);
                if (w == null || w.equals(COMPUTER) || w.equals("Tie")) {
                    return move;
                }
            }
        }
        return bestMove;
    }

    @Override
    public String getStrategyName() {
        return "Computer Always Wins";
    }
}