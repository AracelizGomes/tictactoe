# Araceliz Tic-Tac-Toe Game

Hello! Checkout my full-stack TicTacToe game built with Java Spring Boot and vanilla JavaScript/CSS/HTML.

This application enables users to play TicTacToe with a computer. The computer's expert level player capabilites are powered by a [AIMA Java Package](https://aimacode.github.io/aima-java/).

---
### ⬇️ ⬇️ ⬇️ ⬇️ ⬇️ ⬇️ ⬇️ ⬇️ ⬇️ ⬇️ ⬇️ ⬇️ ⬇️ ⬇️ ⬇️ ⬇️ ⬇️ ⬇️ ⬇️ ⬇️ 
### Start Playing Now! https://araceliz-tictactoe-game.onrender.com 
### ⬆️ ⬆️ ⬆️ ⬆️ ⬆️ ⬆️ ⬆️ ⬆️ ⬆️ ⬆️ ⬆️ ⬆️ ⬆️ ⬆️ ⬆️ ⬆️ ⬆️ ⬆️ ⬆️ ⬆️ 
---
## How to Play

### Game Rules

- **Objective:** Get three of your marks (X) in a row (horizontally, vertically, or diagonally) before the computer (O) does.
- **Turns:** You always play as X and go first. The computer plays as O.
- **Board:** The game is played on a 3x3 grid.
- **Winning:** The first player to align three marks wins. If all cells are filled and no one has three in a row, the game ends in a tie.

### Game Modes
-------------------------------------------------------------------------------------------------------------------------
| Mode                   | Description                                                                                  |
|------------------------|----------------------------------------------------------------------------------------------|
| Computer Always Wins   | The computer uses an optimal strategy (AIMA MiniMax) and will never lose. It will win or tie.|
| Fair Play              | The computer makes random moves 70% of the time, giving you a fair chance to win.            |
-------------------------------------------------------------------------------------------------------------------------

- **Switching Modes:**  
  Use the toggle switch above the board to change between "Computer Always Wins" and "Fair Play" modes.  
  - **Computer Always Wins Mode:** The computer is unbeatable.
  - **Fair Play Mode:** The computer sometimes plays randomly, making it possible for you to win.

### Controls

- **Make a Move:** Click on any empty cell to place your X.
- **Reset Game:** Click the "Reset Game" button to start over.
- **Toggle Mode:** Use the switch to change the computer's strategy.

---

## File/Class Overview

| File/Class                                      | Type      | Description                                                                                   |
|-------------------------------------------------|-----------|-----------------------------------------------------------------------------------------------|
| `TicTacToeApplication.java`                     | Java      | Main Spring Boot application entry point and CORS (Cross Origin Resource Sharing) configuration.                              |
| `controller/TicTacToeController.java`           | Java      | REST API controller for game actions (move, reset, toggle mode, health check).                |
| `controller/StaticContentController.java`       | Java      | Serves static frontend files (HTML, CSS, JS) via endpoints.                                   |
| `model/GameState.java`                          | Java      | Represents the current state of the game (board, winner, player, etc.).                       |
| `model/Move.java`                               | Java      | Encapsulates a move (row, column) in the game.                                                |
| `model/TicTacToeAimaGame.java`                  | Java      | Implements game logic and AI interface for Tic Tac Toe.                                       |
| `service/TicTacToeService.java`                 | Java      | Core game logic, manages state, validates moves, applies strategies.                          |
| `strategy/AIStrategy.java`                      | Java      | Interface for AI strategies.                                                                  |
| `strategy/ComputerAlwaysWinsStrategy.java`      | Java      | AI strategy where computer always wins.                                                       |
| `strategy/FairPlayStrategy.java`                | Java      | AI strategy for fair play.                                                                    |
| `static/index.html`                             | HTML      | Main frontend page for the game UI.                                                           |
| `static/style.css`                              | CSS       | Styles for the game board, controls, and cosmic background.                                   |
| `static/app.js`                                 | JavaScript| Handles frontend logic: rendering board, making moves, toggling mode, communicating with API.  |
| `Dockerfile`                                    | Config    | Docker build instructions for backend and static frontend.                                    |
| `docker-compose.yml`                            | Config    | Docker Compose setup for running the application.                                             |
| `pom.xml`                                       | Config    | Maven build configuration and dependencies.                                                   |
| `.mvn/wrapper/*`                                | Config    | Maven wrapper files for portable builds.                                                      |
| `LICENSE`                                       | Legal     | Apache 2.0 license for the project.                                                           |
| `README.md`                                     | Doc       | Project documentation and overview.                                                           |

---
## Repository Structure

<pre>
tictactoe/
├── .gitignore
├── docker-compose.yml
├── Dockerfile
├── LICENSE
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
├── .mvn/
│   └── wrapper/
│       ├── maven-wrapper.jar
│       ├── maven-wrapper.properties
│       └── MavenWrapperDownloader.java
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── tictactoe/
│   │   │           ├── TicTacToeApplication.java
│   │   │           ├── controller/
│   │   │           │   ├── TicTacToeController.java
│   │   │           │   └── StaticContentController.java
│   │   │           ├── model/
│   │   │           │   ├── GameState.java
│   │   │           │   ├── Move.java
│   │   │           │   └── TicTacToeAimaGame.java
│   │   │           ├── service/
│   │   │           │   └── TicTacToeService.java
│   │   │           └── strategy/
│   │   │               ├── AIStrategy.java
│   │   │               ├── ComputerAlwaysWinsStrategy.java
│   │   │               └── FairPlayStrategy.java
│   │   └── resources/
│   │       └── static/
│   │           ├── index.html
│   │           ├── style.css
│   │           └── app.js
│   └── test/
│       ├── java/
│       └── resources/
</pre>

---

## Getting Started

1. **Build and run locally:**

Using Maven:
> ./mvnw clean package
> java -jar target/tictactoe-1.0.0.jar

Using Docker (preferred)
> docker-compose up --build

2. **Access the game:**
- Open http://localhost:5001 in your browser.

## Java Spring REST API Endpoints
- `GET /api/game` — Get current game state
- `POST /api/game/move` — Make a move ({row, col})
- `POST /api/game/reset` — Reset the game
- `POST /api/game/toggle-mode` — Toggle AI mode
- `GET /api/health`— Health check

## License
This project is licensed under the Apache 2.0 License. See LICENSE for details.


## Referenced files:
- [`TicTacToeApplication.java`](src/main/java/com/tictactoe/TicTacToeApplication.java)
- [`TicTacToeController.java`](src/main/java/com/tictactoe/controller/TicTacToeController.java)
- [`StaticContentController.java`](src/main/java/com/tictactoe/controller/StaticContentController.java)
- [`GameState.java`](src/main/java/com/tictactoe/model/GameState.java)
- [`Move.java`](src/main/java/com/tictactoe/model/Move.java)
- [`TicTacToeAimaGame.java`](src/main/java/com/tictactoe/model/TicTacToeAimaGame.java)
- [`TicTacToeService.java`](src/main/java/com/tictactoe/service/TicTacToeService.java)
- [`AIStrategy.java`](src/main/java/com/tictactoe/strategy/AIStrategy.java)
- [`ComputerAlwaysWinsStrategy.java`](src/main/java/com/tictactoe/strategy/ComputerAlwaysWinsStrategy.java)
- [`FairPlayStrategy.java`](src/main/java/com/tictactoe/strategy/FairPlayStrategy.java)
- [`index.html`](src/main/resources/static/index.html)
- [`style.css`](src/main/resources/static/style.css)
- [`app.js`](src/main/resources/static/app.js)
- [`Dockerfile`](Dockerfile)
- [`docker-compose.yml`](docker-compose.yml)
- [`pom.xml`](pom.xml)
- [`LICENSE`](LICENSE)
- [`README.md`](README.md)