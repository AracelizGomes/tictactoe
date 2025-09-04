package com.tictactoe.strategy;

import com.tictactoe.model.Move;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Bonus Strategy: Computer in FairPlayStrategy mode is set to make random moves 70% of the time.
 * This inclusive strategy introduces a game mode that is a bit more fair + fun for human players.
 */
@Component
public class FairPlayStrategy implements AIStrategy {
    
    private static final Random random = new Random();
    private static final double RANDOM_MOVE_PROBABILITY = 0.7;
    private final ComputerAlwaysWinsStrategy optimalStrategy;
    
    public FairPlayStrategy() {
        this.optimalStrategy = new ComputerAlwaysWinsStrategy();
    }

    @Override
    public String getStrategyName() {
        return "Fair Play Mode";
    }
    
    @Override
    public Move calculateBestMove(String[][] board) {
        List<Move> availableMoves = getAvailableMoves(board);
        
        if (availableMoves.isEmpty()) {
            return null;
        }
        
        // Early game (more than 6 moves available) - always play randomly
        if (availableMoves.size() > 6) {
            return getRandomMove(availableMoves);
        }
        
        // 70% chance of random move, 30% chance of optimal move
        if (random.nextDouble() < RANDOM_MOVE_PROBABILITY) {
            return getRandomMove(availableMoves);
        } else {
            // Use optimal strategy for the remaining 30%
            return optimalStrategy.calculateBestMove(board);
        }
    }
    
    private List<Move> getAvailableMoves(String[][] board) {
        List<Move> moves = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null || board[i][j].isEmpty()) {
                    moves.add(new Move(i, j));
                }
            }
        }
        return moves;
    }
    
    private Move getRandomMove(List<Move> availableMoves) {
        return availableMoves.get(random.nextInt(availableMoves.size()));
    }
}