Low-Level Design: Tic Tac Toe Game 🎲
Tic Tac Toe, known colloquially as "Xs and Os," is a two-player game typically played on a 3x3 grid. The objective is simple: be the first to form a horizontal, vertical, or diagonal line of three of your marks (either "X" or "O"). The elegance of the game lies in its deceptive complexity, while the rules are straightforward, devising an unbeatable strategy demands a keen understanding of the game's dynamics.

‍

Rules of the game : 
Firstly let's understand the rules of the game:

• Setup: The game is played on a 3 * 3 grid. One player uses 'X' another player uses 'O' and each player takes turns making their moves.

‍

• Winner: The game is won by the player placing his or her symbol in a row, column, or diagonal. The first player to get three symbols in a row wins the game. When the player reaches this, the game ends immediately.

‍

• Draw: If all the grid cells are filled and no player has three symbols in a row, the game will be a tie or a draw.

‍

• Illegal Moves: A player cannot place his or her symbol on a tile occupied by an opponent's symbol or their own symbol. The move must be made to an empty cell.


Interview Setting 🤝
Point 1 : Introduction and Vague Problem Statement

🧑‍💼Interviewer: Let's start with a basic problem statement. Design a Tic Tac Toe game system.

‍

🧑‍💻Candidate: Certainly! Let me outline the flow of the game based on my understanding of the Tic Tac Toe game first:

• We have a standard 3x3 grid.

• Two players take turns marking the spaces on the grid with 'X' and 'O'.

• The game continues until one player gets three of their marks in a row (horizontal, vertical, or diagonal), or the grid is filled resulting in a draw.

Is this the kind of game flow you had in mind? 

‍

🧑‍💼Interviewer: Yes, you are in-line with the flow, Please continue ahead.

‍

🧑‍💻Candidate: Sure, I'd like to clarify a few requirements to ensure we're on the same page:

• Are we focusing on a standard 3x3 board?

• Will this be a two-player human game?

• What are the core requirements ?

‍

Point 2 : Clarifying requirements

🧑‍💼Interviewer: We want a simple system that:

• Supports a standard 3x3 Tic Tac Toe game

• Allows two human players to play

• Provides move validation

• Detects win or draw conditions

‍

🧑‍💻Candidate: To ensure we're on the same page, let me write down the key requirements:

1. A 3x3 game board.

2. Two human players.

3. Alternating turns between 'X' and 'O'.

4. Move validation to ensure no wrong moves are made.

5. Detection of win or draw scenarios.

‍

🧑‍💼Interviewer: Perfect, Let's Proceed.

‍

Point 3 : Identifying Key Components : 

🧑‍💻Candidate: Now that we have the requirements clarified, let's identify the key components of our Tic Tac Toe system:

‍

1. Piece: Represents 'X' and 'O'.

○ Enum: Symbol

○ Description: This enum represents the two possible pieces in the game: 'X' and 'O', as well as an empty cell.

public enum Symbol {
	  X, O, EMPTY
	}

2. Board: The 3x3 grid where the game is played.

○ Class: Board

○ Description: This class represents the game board, which can be of any size. It includes methods for validating moves, making moves, and checking the game state.

public class Board {

	}


3. Player: Each player (either X or O) taking turns.

○ Class: Player

○ Description: This class represents a player in the game. It stores the player's symbol and strategy for making moves. 

public class Player {
    Symbol symbol;
    PlayerStrategy playerStrategy;

    public Player(Symbol symbol, PlayerStrategy playerStrategy){
        this.symbol = symbol;
        this.playerStrategy = playerStrategy;
    }

    public Symbol getSymbol(){
        return symbol;
    }

    public PlayerStrategy getStrategy(){
        return playerStrategy;
    }
}