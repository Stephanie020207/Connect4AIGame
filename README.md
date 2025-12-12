# Connect 4 AI Game (Java)
## Implementation of Classical AI Algorithms in a Console-Based Connect 4 Game
A console-based Connect 4 game featuring AI opponents powered by Minimax with Alpha-Beta pruning and Merge Sort–based move ordering.

## Table of Contents
- Introduction
- Background
- Project Goals
- Game Features & Flow
- System Architecture
- Game Leveling
- Algorithms Used
- Conclusion

## Introduction
This project is a Java-based Connect 4 game implemented in the console. It implements a console-based Connect 4 game featuring an intelligent AI opponent. <br/>
The AI is built using classical algorithms such as:
- Linear Search
- Breadth-First Search (BFS)
- Merge Sort (for move ordering)
- Brute Force (One Depth)
- Minimax + Alpha-Beta Pruning <br/>

These algorithms are integrated to create a smart, responsive, and adaptive opponent for various difficulty levels.

## Background
Artificial Intelligence enhances gameplay in strategy games such as Connect 4 by:
- Analyzing board states
- Detecting win conditions
- Predicting optimal moves <br/>
Connect 4 is simple but strategic, making it an ideal case study for <br/>

Algorithms Used:
- Linear Search → Scan for valid moves
- Graph-based search (BFS) → Detect winning conditions
- Sorting (Merge Sort) → Rank moves by strength
- Brute Force (One Depth) → Evaluate possible moves
- Minimax + Alpha-Beta Pruning → Decision-making based on future predictions

## Project Goals
1. Design and Implement an Intelligent AI Opponent
2. Apply Classical AI Algorithms
3. Build a Fully Functional Console-Based Strategy Game

## Game Features & Flow
### Features
1. 3 AI Difficulty Levels
   - Easy
   - Medium
   - Hard
2. Interactive Console UI
3. Game End Detection:
   - AI & player turns alternate
   - Win detection using BFS
   - Board-full → draw
### Flow
1. Start Game
   → Player selects difficulty
   → Board initialized
2. Player Turn
   → Human selects column (0–6)
   → Disc 'O' is placed
3. AI Turn
   → Easy: random
   → Medium: heuristic + merge sort
   → Hard: immediate win/block + minimax
4. Win/Draw Check
   → BFS-based win detection
   → Full board → draw

## System Architecture
The system follows an Object-Oriented Programming (OOP) approach with 4 main classes:
1. Main.java: The entry point of the game. It initializes the game and starts the gameplay loop.
2. Game.java: This class controls the game flow, including player turns and alternating between the human player and AI.
3. Board.java: This class manages the board grid and contains methods for placing discs, printing the board, and checking for winning conditions.
4. AIPlayer.java: Implements the AI logic, including decision-making through algorithms like Minimax and heuristic-based evaluations.

## Game Leveling
1. Easy: 100% random moves
2. Medium: Heuristic scoring + merge sort + 30% randomness
3. Hard: Brute Force (One Depth) + heuristic sort + minimax + alpha-beta pruning

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
