package com.tictactoe.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GameState {
    private String[][] board;
    
    private String winner;
    
    @JsonProperty("current_player")
    private String currentPlayer;
    
    @JsonProperty("game_over")
    private boolean gameOver;
    
    @JsonProperty("computer_always_wins")
    private boolean computerAlwaysWins;
    
    @JsonProperty("waiting_for_computer")
    private boolean waitingForComputer;

    public GameState() {
        this.board = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = "";
            }
        }
        this.currentPlayer = "X";
        this.winner = null;
        this.gameOver = false;
        this.computerAlwaysWins = true;
        this.waitingForComputer = false;
    }
}
