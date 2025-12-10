# Connect 4 AI Game (Java)
A console-based Connect 4 game featuring AI opponents powered by Minimax with Alpha-Beta pruning and Merge Sort–based move ordering.

## Table of Contents
- Introduction
- Features
- Difficulty Levels
- Algorithms Used
- System Architecture
- Code Structure

## Introduction
This project is a Java-based Connect 4 game implemented in the console.  
It includes a fully functional AI opponent capable of playing at three difficulty levels.  
The AI uses algorithms such as Minimax, Alpha-Beta Pruning, Move Ordering, and Merge Sort to evaluate moves efficiently.  
The game was developed as part of an academic project focusing on search algorithms, heuristics, and game theory.

## Features
- Human vs AI gameplay
- Three difficulty levels (Easy, Medium, Hard)
- AI uses Minimax with Alpha-Beta pruning
- Move ordering using heuristic + Merge Sort
- BFS-based win checking

## Difficulty Levels
### Easy
- AI moves are 100% random.

### Medium
- AI scores all legal moves using a heuristic (quickScore)
- Moves are sorted using **Merge Sort**
- 30% chance of picking the second-best move

### Hard
- Immediate win/block detection
- Move ordering using heuristic
- Minimax + Alpha-Beta pruning
- Depth = 6 search for optimal play

## Algorithms Used
### Linear Search (Move Selection)
Used to iterate through the list of legal moves and evaluate them one by one. <br/>
The AI checks each possible column sequentially to test:
- Whether it leads to an immediate win
- Whether it must block the player
- Whether it produces a good heuristic score <br/>
This is a simple but effective searching strategy for small move lists.

### BFS (Breadth-First Search) for Win Detection
The board uses BFS to explore connected cells in all directions (horizontal, vertical, diagonal) to check whether a player has formed a 4-in-a-row sequence. <br/>
BFS ensures:
- Systematic exploration of the board as a graph
- Fast detection of connected components
- Efficient win detection usable by both AI and human moves

### Merge Sort (Move Ordering)
The AI evaluates every legal move using a heuristic (quickScore), then sorts all moves in descending order using Merge Sort. <br/>
Reasons:
- Merge Sort is stable and O(n log n)
- Ordering strong moves early improves alpha-beta pruning
- Helps Minimax “see” good moves sooner → increases efficiency

### Brute Force One-Depth Evaluation (Medium & Hard Mode)
Before using Minimax, the AI performs a one-depth brute-force simulation: <br/>
For each legal move:
1. Drop the disc
2. Evaluate the board with a heuristic function
3. Undo the move <br/>
This brute force check helps in:
- Medium difficulty scoring
- Hard difficulty immediate-win and immediate-block detection
- Move ordering for Minimax

### Minimax Algorithm (with Alpha-Beta Pruning)
A classic game theory algorithm used to simulate future moves and choose the best possible action. <br/>
Minimax:
- Recursively explores future game states
- Assumes the opponent plays optimally
- Alternates between maximizing (AI) and minimizing (human) <br/>
Alpha-Beta Pruning:
- Eliminates unnecessary branches
- Greatly speeds up Minimax
- Works better when combined with move ordering (Merge Sort) <br/>
This is what enables the Hard Mode AI to play strategically and look several moves ahead.

## System Architecture
The project is structured into four main classes:

- `Main` – Starts the game
- `Game` – Handles player turns, difficulty levels, and game loop
- `Board` – Manages game grid, win checking (BFS), legal moves
- `AIPlayer` – Handles AI logic and difficulty levels

Additional helper classes:
- `MoveEvaluator` – Scoring heuristic
- `Minimax` – Minimax search algorithm with Alpha-Beta pruning
- `MergeSort` – Sorting move scores

## Code Structure
- Main.java
- Game.java
- Board.java
- AIPlayer.java
- README.md
- Clean object-oriented structure (Game, Board, AIPlayer, Minimax classes)
- Error handling for invalid input
