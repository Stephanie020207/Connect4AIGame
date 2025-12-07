import java.util.*;

public class Board {
    public static final int ROWS = 6;
    public static final int COLS = 7;
    private static final List<int[]> LINES = generateLines();
    private final char[][] grid;
    private final int[] heights;
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
        for (int r = ROWS - 1; r >= 0; r--) {
            if (grid[r][col] == '.') {
                grid[r][col] = symbol;
                heights[col]++;
                return r;
            }
        }
        return -1; // column is full
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
        boolean[][] visited = new boolean[ROWS][COLS];
        // Directions: horizontal, vertical, and diagonals
        int[][] directions = {
                {0, 1},
                {1, 0},
                {1, 1},
                {1, -1}
        };
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (grid[r][c] == symbol && !visited[r][c]) {
                    if (bfsCheck(r, c, symbol, visited, directions)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean bfsCheck(int startRow, int startCol, char symbol,
                             boolean[][] visited, int[][] directions) {

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0];
            int c = cell[1];
            for (int[] dir : directions) {
                int count = 1;
                int nr = r + dir[0];
                int nc = c + dir[1];
                while (nr >= 0 && nr < ROWS && nc >= 0 && nc < COLS
                        && grid[nr][nc] == symbol) {
                    count++;
                    nr += dir[0];
                    nc += dir[1];
                }
                if (count >= 4) {
                    return true;
                }
            }
            // BFS expansion to neighbors
            int[][] neighbors = {
                    {0, 1}, {1, 0}, {-1, 0}, {0, -1},
                    {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
            };
            for (int[] n : neighbors) {
                int nr = r + n[0];
                int nc = c + n[1];
                if (nr >= 0 && nr < ROWS && nc >= 0 && nc < COLS
                        && !visited[nr][nc]
                        && grid[nr][nc] == symbol) {

                    visited[nr][nc] = true;
                    queue.add(new int[]{nr, nc});
                }
            }
        }
        return false;
    }

    public static List<int[]> getAllLines() {
        return LINES;
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