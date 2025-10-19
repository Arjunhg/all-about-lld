Tic‑Tac‑Toe: A Practical, Extensible Design

Building a small game is a great excuse to practice clean architecture. This document walks through the Tic‑Tac‑Toe design used in this project—what problems it solves, how the pieces fit together, and how you can extend it without breaking what works today.

## Why this design

We want a game that is:
- Easy to read and reason about
- Safe against illegal moves and broken flows
- Simple to extend (AI player, different board sizes, UI swap)

That leads us to a few focused design choices: isolate responsibilities, program to interfaces, and make state transitions explicit.

## Core challenges (what we must get right)

- Game state: who’s turn it is, whether the game is over, and why (win/draw)
- Move validation: only allow in-bounds, empty cells
- Turn alternation: X then O, and so on—unless the game ends
- End conditions: detect wins across rows/columns/diagonals; detect draw when the board fills up with no winner

## Architecture at a glance

```mermaid
graph TD
	Main[Main] --> G[TicTacToeGame]
	G -->|asks for move| PlayerX[Player (X)]
	G -->|asks for move| PlayerO[Player (O)]
	PlayerX -->|makeMove(board)| StratX[PlayerStrategy]
	PlayerO -->|makeMove(board)| StratO[PlayerStrategy]
	G -->|apply + validate| Board[Board]
	G -->|check state| Context[GameContext]
	Context -->|current| State[GameState]
```

- Main: wires strategies and starts the game.
- TicTacToeGame: the conductor—runs the loop, asks the current player for a move, applies it on the board, checks state, switches player.
- Player/PlayerStrategy: how a move is decided (human input today; AI later).
- Board: the source of truth for the grid and rules (validate/apply, detect win/draw, display).
- GameContext/GameState: encapsulates the terminal outcome and message.

## Responsibilities by component

### TicTacToeGame (Controller/Facade)
- Owns the main game loop.
- Calls `currentPlayer.getPlayerStrategy().makeMove(board)` to get a Position.
- Calls `board.makeMove(position, symbol)` to apply it safely.
- Calls `board.checkGameState(context, currentPlayer)` to update the outcome.
- Switches player if the game is not over and repeats.
- Prints the final result via `context.getCurrentState().getResultMessage()`.

### Player and PlayerStrategy (Strategy Pattern)
- `PlayerStrategy` defines a single responsibility: return the next `Position` for a given `Board`.
- `HumanPlayerStrategy` prompts on the console and parses row/column input.
- Swap in a different strategy (e.g., Random, Minimax AI) without touching the controller or board.

### Board
- Holds `rows x cols` grid of `Symbol` values.
- `isValidMove(Position)`: bounds + emptiness check.
- `makeMove(Position, Symbol)`: applies a legal move; throws on invalid ones.
- `checkGameState(GameContext, Player)`: detects wins (rows/cols/diagonals) and draw (no empties). Updates `GameContext` accordingly.
- `displayBoard()`: prints a clean, readable board (e.g., `X | . | O`).

### GameContext and GameState (State Pattern)
- `GameContext` holds the current `GameState` and answers “is the game over?”
- Concrete states: `XWonState`, `OWonState`, `DrawState`—each owns its own message via `getResultMessage()`.
- `TicTacToeGame` doesn’t branch on types; it just prints the message (polymorphism).

## A single turn: end‑to‑end

1) Controller prints the board.
2) Asks the current player’s strategy for a move → `Position`.
3) Board validates and applies the symbol at that position.
4) Board evaluates the outcome and updates `GameContext` (win/draw/continue).
5) If continuing, controller switches the current player; otherwise, it announces the result.

## Patterns in play (and why)

- Strategy: isolate “how to choose a move” from the rest of the game.
- State: make terminal outcomes explicit and self‑describing (`getResultMessage()`).
- Facade/Controller: route decisions through a single orchestrator (`TicTacToeGame`).

> What about Observer and Factory?
> Not needed yet. They’re natural extensions: an observer for logging/telemetry/UI updates; a factory for building players from config. Keep them in mind; don’t add them prematurely.

## Rules recap (today) and evolution

- Win: a full row, column, main diagonal, or anti‑diagonal of the same non‑empty symbol.
- Draw: no empty cells and no winner.

Future‑ready: on larger boards, replace win detection with a generic “k‑in‑a‑row” scan (right, down, diag‑down‑right, diag‑down‑left) and make `k` configurable.

## Quick start

Initialization from `Main`:

```java
PlayerStrategy playerXStrategy = new HumanPlayerStrategy("Player X");
PlayerStrategy playerYStrategy = new HumanPlayerStrategy("Player O");
TicTacToeGame game = new TicTacToeGame(playerXStrategy, playerYStrategy, 3, 3);
game.play();
```

That’s it. Strategies decide moves; the controller orchestrates; the board enforces rules; the context tells the end of the story.

## Extending the game (no rewrites required)

- Add an AI: implement `PlayerStrategy` and pass it at startup.
- Change board size: alter `(rows, cols)` when creating `TicTacToeGame`.
- UI swap: keep logic intact and replace `HumanPlayerStrategy` with one that talks to a GUI or web layer.
- Telemetry/UX: add an observer layer to react to game events without touching the core loop.

