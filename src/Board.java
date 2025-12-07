import java.util.*;

public class Board {

    public static final int ROWS = 6;
    public static final int COLS = 7;

    private final char[][] grid;
    private final int[] heights;
    private static final List<int[]> LINES = generateLines();
    public Board() {
        grid = new char[ROWS][COLS];
        heights = new int[COLS];
        for (int r = 0; r < ROWS; r++) {
            Arrays.fill(grid[r], '.');
        }
        Arrays.fill(heights, 0);
    }

    public Board(Board other) {
        grid = new char[ROWS][COLS];
        heights = new int[COLS];
        for (int r = 0; r < ROWS; r++) {
            System.arraycopy(other.grid[r], 0, this.grid[r], 0, COLS);
        }
        System.arraycopy(other.heights, 0, this.heights, 0, COLS);
    }

    public boolean isValidMove(int col) {
        return heights[col] < ROWS;
    }

    public List<Integer> getLegalMoves() {
        List<Integer> list = new ArrayList<>();
        for (int c = 0; c < COLS; c++) {
            if (isValidMove(c)) list.add(c);
        }
        return list;
    }

    public int dropDisc(int col, char symbol) {
        if (!isValidMove(col)) return -1;
        int row = heights[col];
        int gridRow = ROWS - 1 - row;
        grid[gridRow][col] = symbol;
        heights[col]++;
        return gridRow;
    }

    public boolean undo(int col) {
        if (heights[col] <= 0) return false;
        int row = heights[col] - 1;
        int gridRow = ROWS - 1 - row;
        grid[gridRow][col] = '.';
        heights[col]--;
        return true;
    }

    public char getCell(int r, int c) {
        if (r < 0 || r >= ROWS || c < 0 || c >= COLS) return '.';
        return grid[r][c];
    }

    public boolean checkWin(char symbol) {
        for (int[] line : LINES) {
            boolean win = true;
            for (int idx : line) {
                int r = idx / COLS;
                int c = idx % COLS;
                if (grid[r][c] != symbol) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }
        return false;
    }

    public boolean isFull() {
        for (int h : heights) if (h < ROWS) return false;
        return true;
    }

    public void printBoard() {
        System.out.println();
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                System.out.print("| " + grid[r][c] + " ");
            }
            System.out.println("|");
        }
        System.out.println("-----------------------------");
        System.out.println("  0  " + " 1  " + " 2  " + " 3  " + " 4  " + " 5  " + " 6");
    }

    public static List<int[]> getAllLines() {
        return LINES;
    }

    private static List<int[]> generateLines() {
        List<int[]> lines = new ArrayList<>();
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                // Horizontal
                if (c + 3 < COLS) {
                    lines.add(new int[]{
                            r * COLS + c,
                            r * COLS + c + 1,
                            r * COLS + c + 2,
                            r * COLS + c + 3
                    });
                }
                // Vertical
                if (r + 3 < ROWS) {
                    lines.add(new int[]{
                            r * COLS + c,
                            (r + 1) * COLS + c,
                            (r + 2) * COLS + c,
                            (r + 3) * COLS + c
                    });
                }
                // Diagonal down right
                if (r + 3 < ROWS && c + 3 < COLS) {
                    lines.add(new int[]{
                            r * COLS + c,
                            (r + 1) * COLS + c + 1,
                            (r + 2) * COLS + c + 2,
                            (r + 3) * COLS + c + 3
                    });
                }
                // Diagonal up right
                if (r - 3 >= 0 && c + 3 < COLS) {
                    lines.add(new int[]{
                            r * COLS + c,
                            (r - 1) * COLS + c + 1,
                            (r - 2) * COLS + c + 2,
                            (r - 3) * COLS + c + 3
                    });
                }
            }
        }
        return lines;
    }
}