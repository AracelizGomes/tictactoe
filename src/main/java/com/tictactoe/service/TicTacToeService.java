package com.tictactoe.service;

import com.tictactoe.model.GameState;
import com.tictactoe.model.Move;
import com.tictactoe.strategy.AIStrategy;
import com.tictactoe.strategy.ComputerAlwaysWinsStrategy;
import com.tictactoe.strategy.FairPlayStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class that manages game logic and which strategy gets used
 */
@Service
public class TicTacToeService {
    
    private GameState currentGame;
    
    @Autowired
    private ComputerAlwaysWinsStrategy computerAlwaysWinsStrategy;
    
    @Autowired
    private FairPlayStrategy fairPlayStrategy;
    
    public TicTacToeService() {
        this.currentGame = new GameState();
    }
    
    // Get current game state
    public GameState getCurrentGame() {
        return currentGame;
    }
    
    // Make a move and trigger computer response
    public GameState makeMove(Move move) {
        // Validate move
        if (currentGame.isGameOver() || !isValidMove(move) || !currentGame.getCurrentPlayer().equals("X")) {
            throw new IllegalArgumentException("Invalid move");
        }
        
        // Make player move
        currentGame.getBoard()[move.getRow()][move.getCol()] = "X";
        
        // Check if player won
        updateGameState();
        if (currentGame.isGameOver()) {
            return currentGame;
        }
        
        // Computer's turn
        currentGame.setCurrentPlayer("O");
        currentGame.setWaitingForComputer(true);
        
        // Get AI strategy based on game mode
        AIStrategy strategy = currentGame.isComputerAlwaysWins() ? computerAlwaysWinsStrategy : fairPlayStrategy;
        
        // Calculate and make computer move
        Move computerMove = strategy.calculateBestMove(currentGame.getBoard());
        if (computerMove != null) {
            currentGame.getBoard()[computerMove.getRow()][computerMove.getCol()] = "O";
        }
        
        // Update game state after computer move
        updateGameState();
        currentGame.setWaitingForComputer(false);
        
        return currentGame;
    }
    
    // Reset the game to initial state
    public GameState resetGame() {
        boolean currentMode = currentGame.isComputerAlwaysWins();
        this.currentGame = new GameState();
        this.currentGame.setComputerAlwaysWins(currentMode);
        return this.currentGame;
    }
    
    // Toggle between game modes
    public GameState toggleGameMode() {
        currentGame.setComputerAlwaysWins(!currentGame.isComputerAlwaysWins());
        return currentGame;
    }
    
    // Check if a move is valid
    private boolean isValidMove(Move move) {
        if (move.getRow() < 0 || move.getRow() > 2 || 
            move.getCol() < 0 || move.getCol() > 2) {
            return false;
        }
        
        String cell = currentGame.getBoard()[move.getRow()][move.getCol()];
        return cell == null || cell.isEmpty();
    }
    
    // Update game state after a move (check winner, game over status)
    private void updateGameState() {
        String winner = checkWinner();
        
        if (winner != null) {
            currentGame.setWinner(winner);
            currentGame.setGameOver(true);
        } else if (isBoardFull()) {
            currentGame.setWinner("Tie");
            currentGame.setGameOver(true);
        } else {
            // Switch players
            currentGame.setCurrentPlayer(currentGame.getCurrentPlayer().equals("X") ? "O" : "X");
        }
    }
    
    // Check for a winner on the current board
    private String checkWinner() {
        String[][] board = currentGame.getBoard();
        
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != null && !board[i][0].isEmpty() &&
                board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) {
                return board[i][0];
            }
        }
        
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != null && !board[0][j].isEmpty() &&
                board[0][j].equals(board[1][j]) && board[1][j].equals(board[2][j])) {
                return board[0][j];
            }
        }
        
        // Check diagonals
        if (board[0][0] != null && !board[0][0].isEmpty() &&
            board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            return board[0][0];
        }
        
        if (board[0][2] != null && !board[0][2].isEmpty() &&
            board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
            return board[0][2];
        }
        
        return null;
    }
    
    // Check if the board is completely filled
    private boolean isBoardFull() {
        String[][] board = currentGame.getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null || board[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
}