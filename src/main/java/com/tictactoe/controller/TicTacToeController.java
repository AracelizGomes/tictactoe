package com.tictactoe.controller;

import com.tictactoe.model.GameState;
import com.tictactoe.model.Move;
import com.tictactoe.service.TicTacToeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

// REST Controller for TicTacToe game API endpoints
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TicTacToeController {
    
    @Autowired
    private TicTacToeService ticTacToeService;
    
    // Health check endpoint
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        return ResponseEntity.ok(Map.of("status", "healthy"));
    }
    
    // Get current game state
    @GetMapping("/game")
    public ResponseEntity<GameState> getGame() {
        return ResponseEntity.ok(ticTacToeService.getCurrentGame());
    }
    
    // Make a move in the game
    @PostMapping("/game/move")
    public ResponseEntity<?> makeMove(@RequestBody Move move) {
        try {
            GameState gameState = ticTacToeService.makeMove(move);
            return ResponseEntity.ok(gameState);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", "Invalid move"));
        }
    }
    
    // Reset the game
    @PostMapping("/game/reset")
    public ResponseEntity<GameState> resetGame() {
        GameState gameState = ticTacToeService.resetGame();
        return ResponseEntity.ok(gameState);
    }
    

    // Toggle game mode between computer always wins and fair play
    @PostMapping("/game/toggle-mode")
    public ResponseEntity<GameState> toggleGameMode() {
        GameState gameState = ticTacToeService.toggleGameMode();
        return ResponseEntity.ok(gameState);
    }
}