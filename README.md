# Connect 4 AI Game (Java)
### Implementation of Classical AI Algorithms in a Console-Based Connect 4 Game
*A console-based Connect 4 game featuring AI opponents powered by Minimax with Alpha-Beta pruning and Merge Sort–based move ordering.*

# Course Information
### ES234317 - Data Structure and Algorithm (Q)
### Course Convenor: Renny Pradina Kusumawardani, S.T., M.T. <br/>
### Academic Year: 2024
**By Group 4:**
1. Agha Aryo Utomo / 5026241003
2. Dyandra R-Noor Batari / 5026241051
3. Maria Stephanie Febryana Kristijanto / 5026241052

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

## Proposed Solution
This project provides a console-based Connect 4 game equipped with an intelligent AI opponent.  
By combining classical algorithms such as Linear Search, BFS, Merge Sort, Brute Force evaluation, and Minimax with Alpha-Beta pruning, the system is able to make strategic decisions, detect winning conditions efficiently, and offer multiple difficulty levels for players.

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
4. AIPlayer.java: Implements the AI logic, including decision-making through algorithms like Minimax and heuristic-based evaluations. <br/>

![image_alt](https://github.com/Stephanie020207/Connect4AIGame/blob/master/Connect%204%20AI%20Project%20UML.png)

## Game Leveling
1. Easy: 100% random moves
2. Medium: Heuristic scoring + merge sort + 30% randomness
3. Hard: Brute Force (One Depth) + heuristic sort + minimax + alpha-beta pruning

## Algorithms Used
### 1. Linear Search (Move Selection)
**A. Definition:** <br/>
Linear Search scans elements sequentially, checking each item one by one.

**B. Purpose:** <br/>
Linear Search is used to sequentially examine each column and row on the board to determine valid moves, locate the first empty slot in a column, check whether the board is full, and evaluate each potential move in order. This approach is efficient and appropriate given that Connect 4’s board only contains seven columns, making sequential scanning both simple and fast.

**C. Role in This Project:** <br/> 
In this project, Linear Search enables the AI to examine all legal moves one by one to detect immediate winning opportunities, identify moves that must be Bblocked to prevent the player from winning, and compute heuristic scores for each potential move. It also supports the fundamental mechanics of the game by handling valid move detection, disc placement, and full-board detection.

**D. Code Locations:**
<br/>- Board.getLegalMoves()
<br/>- Board.dropDisc()
<br/>- Board.isFull()
<br/>- AIPlayer.immediateWin()

### 2. BFS (Breadth-First Search) for Win Detection
**A. Definition:** <br/>
BFS explores neighboring cells level-by-level using a queue, making it ideal for detecting connected components in grid-based games such as Connect 4.

**B. Purpose:** <br/>
BFS is used to systematically examine the board for any sequence of four connected discs in horizontal, vertical, or diagonal directions. Its level-by-level exploration ensures that win detection is efficient, accurate, and consistent after every move.

**C. Role in This Project:** <br/>
In this project, BFS is responsible for determining whether a player has won after each disc placement. The board scans for the player’s discs, expands outward using BFS to explore all connected neighbors, counts how many are linked in each direction, and declares a win if four connected discs are found. This process ensures fast and reliable win detection for both the human player and the AI.

**D. Code Locations:**
<br/>- Board.checkWin()
<br/>- Board.bfsCheck()

![image_alt](https://github.com/Stephanie020207/Connect4AIGame/blob/master/BFS%20Graph.jpeg)

### 3. Merge Sort (Move Ordering)
**A. Definition:** <br/>
Merge Sort is a stable O(n log n) divide-and-conquer sorting algorithm that recursively splits a list into halves, sorts them, and merges the results in order.

**B. Purpose:** <br/>
Merge Sort is used to sort all evaluated moves from best to worst based on their heuristic scores. By ordering the strongest moves early, the AI can explore promising options sooner, significantly improving the effectiveness of alpha-beta pruning and making both Medium and Hard difficulty modes more efficient and intelligent.

**C. Role in This Project:** <br/>
In this project, the AI first assigns a heuristic score to every legal move, then applies Merge Sort to arrange these moves from highest priority to lowest. The sorted list is then provided to either the Medium AI decision process or the Minimax algorithm used in Hard mode. This ensures that the AI consistently examines the most advantageous moves first, resulting in smarter and faster decision-making.

**D. Code Locations:**
<br/>- AIPlayer.mediumMove()
<br/>- AIPlayer.hardMove()
<br/>- Minimax.minimax()
<br/>- MergeSort class

### 4. Brute Force One-Depth Evaluation (Medium & Hard Mode)
**A. Definition:** <br/>
Brute-force one-depth search evaluates all possible immediate moves by simulating each option exactly one turn ahead (depth = 1).

**B. Purpose:** <br/>
This algorithm is used to quickly identify any move that results in an instant win for the AI or an immediate threat that must be blocked to prevent the human from winning on the next turn. It also generates fast heuristic scores for Medium mode and prepares an ordered list of strong moves for Hard mode, improving the overall efficiency of the AI’s decision-making.

**C. Role in This Project:** <br/>
In this project, the Medium AI relies on the heuristic scores produced by the one-depth brute-force simulation to choose reasonably strong moves. The Hard AI uses brute-force evaluation to check for instant winning opportunities, detect immediate threats that require blocking, and pre-sort moves before passing them to the Minimax algorithm for deeper strategic analysis. This ensures that the AI avoids simple tactical mistakes and responds intelligently to short-term threats.

**D. Code Locations:**
<br/>- AIPlayer.immediateWin()
<br/>- AIPlayer.mediumMove()
<br/>- AIPlayer.hardMove()

### 5. Minimax Algorithm (with Alpha-Beta Pruning)
**A. Minimax Definition:** <br/>
Minimax is a classic game-tree search algorithm in which the MAX player (AI) attempts to maximize the evaluation score, while the MIN player (human) tries to minimize it. The algorithm recursively explores future game states, alternating between maximizing and minimizing layers, to determine the optimal move.

**B. Alpha-Beta Pruning Definition:** <br/>
Alpha-Beta pruning is an optimization technique applied to Minimax that eliminates branches of the game tree that cannot possibly influence the final decision. By skipping these unnecessary calculations, the algorithm becomes significantly faster without affecting the accuracy of its results.

**C. Purpose:** <br/> 
Minimax with alpha-beta pruning allows the Hard AI to plan several moves ahead by simulating potential future board states. It helps the AI predict and block long-term threats, choose the most advantageous strategic actions, and behave optimally in complex situations. The pruning mechanism ensures this deeper search is computationally efficient.

**D. Role in This Project:** <br/>
In this project, the Hard AI begins by generating all possible legal moves and sorting them using Merge Sort so that promising moves are explored first. It then uses the Minimax algorithm to evaluate deeper future game states and applies alpha-beta pruning to eliminate branches that would not affect the final decision. This combination enables the Hard AI to make strong, strategic, and efficient decisions during gameplay.

**E. Code Locations:**
<br/>- AIPlayer.hardMove()
<br/>- Minimax.search()
<br/>- Minimax.minimax()

![image_alt](https://github.com/Stephanie020207/Connect4AIGame/blob/master/Minimax%20Tree.jpeg)

## Conclusion
1. The project successfully built an intelligent AI opponent for Connect 4 using classical algorithms such as Minimax and Alpha-Beta Pruning.
2. Classical algorithms including Linear Search, Merge Sort, BFS, Brute-Force Evaluation, and Minimax were effectively integrated to support game logic and AI behavior.
3. A fully functional console-based game was created, demonstrating how algorithmic techniques can be applied to real gameplay scenarios.

## Other Projects from Class Q
1. Group 1: github.com/yelllsit-glitch/Final-Project-ASD
2. Group 2: github.com/riozakyrizky/Final-Project-ASD-2025
3. Group 3: github.com/strupwa/RevASD/
4. Group 4: github.com/Stephanie020207/Connect4AIGame
5. Group 5: github.com/Daffa-001/ASD-IUP-FinalProject
