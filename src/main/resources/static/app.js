class TicTacToeApp {

    constructor() {
        // Constructor initialization of app class, setting up API URL and game components
        this.apiUrl = '/api';
        this.gameBoard = document.getElementById('game-board');
        this.gameStatus = document.getElementById('game-status');
        this.resetBtn = document.getElementById('reset-game');
        this.modeToggle = document.getElementById('mode-toggle');
        this.modeLabel = document.getElementById('mode-label');

        // Call the initialization method to set up event listeners and load the initial game state
        this.init();
    }
    
    init() {
        // Initialize event listeners and load the initial game state
        this.resetBtn.addEventListener('click', () => this.resetGame());
        this.modeToggle.addEventListener('change', () => this.toggleGameMode());
        this.loadGame();
    }

    async loadGame() {
        // Fetch the current game state from the server and render it on the game board
        try {
            const response = await fetch(`${this.apiUrl}/game`);
            const game = await response.json();
            this.renderGame(game);
        } catch (error) {
            console.error('Error loading game:', error);
        }
    }
    
    renderGame(game) {
        this.gameBoard.innerHTML = '';
        // Create a 3x3 grid for the game board based on the current game
        for (let row = 0; row < 3; row++) {
            for (let col = 0; col < 3; col++) {
                const cell = document.createElement('button');
                cell.className = 'game-cell';
                cell.textContent = game.board[row][col];
                
                if (game.board[row][col]) {
                    cell.classList.add(game.board[row][col].toLowerCase());
                    cell.disabled = true;
                } else if (game.game_over) {
                    cell.disabled = true;
                } else {
                    cell.addEventListener('click', () => this.makeMove(row, col));
                }
                
                this.gameBoard.appendChild(cell);
            }
        }
        
        this.updateGameStatus(game);
    }
    
    updateGameStatus(game) {
        // Update the game status text based on the current state of the game
        if (game.waiting_for_computer) {
            this.gameStatus.textContent = "Computer is thinking...";
        } else if (game.game_over) {
            if (game.winner === 'Tie') {
                this.gameStatus.textContent = "It's a Tie!";
            } else if (game.winner === 'X') {
                this.gameStatus.textContent = "You Win!";
            } else {
                this.gameStatus.textContent = "Computer Wins!";
            }
        } else {
            if (game.current_player === 'X') {
                this.gameStatus.textContent = "Your Turn (X)";
            } else {
                this.gameStatus.textContent = "Computer's Turn (O)";
            }
        }
        
        // Inclusive toggle for players who prefer positive reinforcement over machine always winning
        this.modeToggle.checked = game.computer_always_wins;
        this.modeLabel.textContent = game.computer_always_wins ? 
            "Computer Always Wins Mode" : 
            "Fair Play Mode";
    }

    async makeMove(row, col) {
        // Send the player's move to the server and update the game state based on the response
        try {
            const response = await fetch(`${this.apiUrl}/game/move`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ row, col })
            });
            
            if (response.ok) {
                const game = await response.json();
                this.renderGame(game);
            }
        } catch (error) {
            console.error('Error making move:', error);
        }
    }
    
    async resetGame() {
        // Send a request to reset the game and update the game state based on the response
        try {
            const response = await fetch(`${this.apiUrl}/game/reset`, {
                method: 'POST'
            });
            
            if (response.ok) {
                const game = await response.json();
                this.renderGame(game);
            }
        } catch (error) {
            console.error('Error resetting game:', error);
        }
    }
    
    async toggleGameMode() {
        // Send a request to toggle the game mode and update the game state based on the response
        try {
            const response = await fetch(`${this.apiUrl}/game/toggle-mode`, {
                method: 'POST'
            });
            
            if (response.ok) {
                const game = await response.json();
                this.updateGameStatus(game);
            }
        } catch (error) {
            console.error('Error toggling game mode:', error);
        }
    }
}

const app = new TicTacToeApp();