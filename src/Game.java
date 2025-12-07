import java.util.*;

public class Game {

    private final Board board;
    private final AIPlayer ai;
    private final Scanner scanner;
    private final char HUMAN = 'O';
    private final char AI = 'X';
    private final int AI_DEPTH = 6;

    public Game() {
        board = new Board();
        ai = new AIPlayer(AI, HUMAN, AI_DEPTH);
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("=== CONNECT 4 (CONSOLE VERSION) ===");
        System.out.println("You are 'O' ; AI is 'X'");
        board.printBoard();

        char current = HUMAN;
        while (true) {
            if (current == HUMAN) {
                humanTurn();
                board.printBoard();
                if (board.checkWin(HUMAN)) {
                    System.out.println("You win! Congratulations!");
                    break;
                }
                current = AI;
            }
            else {
                System.out.println("AI is thinking...");
                int move = ai.chooseMove(board);
                if (move < 0) {
                    System.out.println("No valid moves left. It's a draw!");
                    break;
                }
                board.dropDisc(move, AI);
                System.out.println("AI chooses column: " + move);
                board.printBoard();
                if (board.checkWin(AI)) {
                    System.out.println("AI wins!");
                    break;
                }
                current = HUMAN;
            }
            if (board.isFull()) {
                System.out.println("Board full. Game ends in a draw.");
                break;
            }
        }
        scanner.close();
    }

    private void humanTurn() {
        while (true) {
            System.out.print("Enter column (0–6): ");
            int col;
            try {
                col = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
                continue;
            }
            if (col < 0 || col >= Board.COLS) {
                System.out.println("Column out of range!");
                continue;
            }
            if (!board.isValidMove(col)) {
                System.out.println("Column is full!");
                continue;
            }
            board.dropDisc(col, HUMAN);
            break;
        }
    }
}