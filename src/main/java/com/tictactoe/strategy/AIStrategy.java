package com.tictactoe.strategy;

import com.tictactoe.model.Move;

public interface AIStrategy {
    
    String getStrategyName();

    Move calculateBestMove(String[][] board);
}
