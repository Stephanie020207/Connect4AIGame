import java.util.*;

public class AIPlayer {
    private final char AI;
    private final char HUMAN;
    private final int DEPTH;
    private final MoveEvaluator evaluator;
    private Minimax minimax;
    private int difficulty;

    public AIPlayer(char ai, char human, int depth, int difficulty) {
        this.AI = ai;
        this.HUMAN = human;
        this.DEPTH = depth;
        this.difficulty = difficulty;
        this.evaluator = new MoveEvaluator(ai, human);
        if (difficulty == 3) {
            this.minimax = new Minimax(ai, human, this.evaluator);
        }
    }


    public int chooseMove(Board board) {
        List<Integer> moves = board.getLegalMoves();
        if (moves.isEmpty()) return -1;
        if (difficulty == 1) {
            return easyMove(board, moves);
        } else if (difficulty == 2) {
            return mediumMove(board, moves);
        } else {
            return hardMove(board, moves);
        }
    }

    private int easyMove(Board board, List<Integer> moves) {
        Random rand = new Random();
        // 100% random
        return moves.get(rand.nextInt(moves.size()));
    }

    // Medium
    private int mediumMove(Board board, List<Integer> moves) {
        // Score all moves
        List<MoveScore> scored = new ArrayList<>();
        for (int c : moves) {
            board.dropDisc(c, AI);
            int s = evaluator.quickScore(board);
            board.undo(c);
            scored.add(new MoveScore(c, s));
        }
        // Sorting (Merge Sort)
        MoveScore[] arr = scored.toArray(new MoveScore[0]);
        MergeSort.sort(arr);

        // 30% chance pick second best move
        Random rand = new Random();
        if (rand.nextInt(100) < 30 && arr.length > 1) {
            return arr[1].col;
        }
        return arr[0].col; // best move
    }

    // Hard
    private int hardMove(Board board, List<Integer> moves) {

        Integer winCol = immediateWin(board, AI);
        if (winCol != null) return winCol;

        Integer blockCol = immediateWin(board, HUMAN);
        if (blockCol != null) return blockCol;

        List<MoveScore> scored = new ArrayList<>();
        for (int c : moves) {
            board.dropDisc(c, AI);
            int s = evaluator.quickScore(board);
            board.undo(c);
            scored.add(new MoveScore(c, s));
        }

        MoveScore[] arr = scored.toArray(new MoveScore[0]);
        MergeSort.sort(arr);

        List<Integer> orderedMoves = new ArrayList<>();
        for (MoveScore ms : arr) orderedMoves.add(ms.col);

        Minimax.Result result =
                minimax.search(board, DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, true, orderedMoves);

        int best = result.bestMove;
        if (best == -1 && !orderedMoves.isEmpty()) best = orderedMoves.get(0);

        return best;
    }

    private Integer immediateWin(Board board, char symbol) {
        for (int c : board.getLegalMoves()) {
            board.dropDisc(c, symbol);
            boolean win = board.checkWin(symbol);
            board.undo(c);
            if (win) return c;
        }
        return null;
    }
}

class MoveScore {
    int col;
    int score;
    MoveScore(int col, int score) {
        this.col = col;
        this.score = score;
    }
}

class MoveEvaluator {
    private final char AI, HUMAN;
    private final int[] weights = {0, 1, 10, 100, 100000};

    public MoveEvaluator(char ai, char human) {
        this.AI = ai;
        this.HUMAN = human;
    }

    public int quickScore(Board board) {
        int score = 0;
        for (int[] line : Board.getAllLines()) {
            int aiCount = 0;
            int humanCount = 0;
            for (int idx : line) {
                int r = idx / Board.COLS;
                int c = idx % Board.COLS;
                char cell = board.getCell(r, c);
                if (cell == AI) aiCount++;
                else if (cell == HUMAN) humanCount++;
            }
            if (aiCount > 0 && humanCount == 0) score += weights[aiCount];
            else if (humanCount > 0 && aiCount == 0) score -= weights[humanCount];
        }
        return score;
    }

    public int evaluate(Board board) {
        if (board.checkWin(AI)) return 1000000;
        if (board.checkWin(HUMAN)) return -1000000;
        return quickScore(board);
    }
}

// Merge Sort (Descending)
class MergeSort {
    public static void sort(MoveScore[] arr) {
        if (arr.length <= 1) return;
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(MoveScore[] a, int l, int r) {
        if (l >= r) return;
        int m = (l + r) / 2;
        mergeSort(a, l, m);
        mergeSort(a, m + 1, r);
        merge(a, l, m, r);
    }

    private static void merge(MoveScore[] a, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        MoveScore[] L = new MoveScore[n1];
        MoveScore[] R = new MoveScore[n2];
        System.arraycopy(a, l, L, 0, n1);
        System.arraycopy(a, m + 1, R, 0, n2);
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i].score >= R[j].score) {
                a[k++] = L[i++];
            } else {
                a[k++] = R[j++];
            }
        }
        while (i < n1) a[k++] = L[i++];
        while (j < n2) a[k++] = R[j++];
    }
}

// Minimax + alpha beta
class Minimax {
    private final char AI, HUMAN;
    private final MoveEvaluator evaluator;

    public Minimax(char ai, char human, MoveEvaluator eval) {
        this.AI = ai;
        this.HUMAN = human;
        this.evaluator = eval;
    }

    static class Result {
        int bestMove, bestScore;
        Result(int m, int s) { bestMove = m; bestScore = s; }
    }

    public Result search(Board board, int depth, int alpha, int beta,
                         boolean maximizing, List<Integer> orderedMoves) {
        int bestMove = -1;
        int bestScore;
        if (maximizing) {
            bestScore = Integer.MIN_VALUE;
            for (int col : orderedMoves) {
                if (!board.isValidMove(col)) continue;
                board.dropDisc(col, AI);
                int val = minimax(board, depth - 1, alpha, beta, false);
                board.undo(col);
                if (val > bestScore) {
                    bestScore = val;
                    bestMove = col;
                }
                alpha = Math.max(alpha, bestScore);
                if (alpha >= beta) break;
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (int col : orderedMoves) {
                if (!board.isValidMove(col)) continue;
                board.dropDisc(col, HUMAN);
                int val = minimax(board, depth - 1, alpha, beta, true);
                board.undo(col);
                if (val < bestScore) {
                    bestScore = val;
                    bestMove = col;
                }
                beta = Math.min(beta, bestScore);
                if (alpha >= beta) break;
            }
        }
        return new Result(bestMove, bestScore);
    }

    private int minimax(Board board, int depth, int alpha, int beta, boolean maximizing) {
        if (depth == 0 || board.isFull() ||
                board.checkWin(AI) || board.checkWin(HUMAN)) {
            return evaluator.evaluate(board);
        }
        List<Integer> moves = board.getLegalMoves();
        // reorder moves using heuristic -> helps alpha-beta
        MoveScore[] arr = new MoveScore[moves.size()];
        int idx = 0;
        for (int col : moves) {
            board.dropDisc(col, maximizing ? AI : HUMAN);
            arr[idx++] = new MoveScore(col, evaluator.quickScore(board));
            board.undo(col);
        }
        MergeSort.sort(arr);
        // collect ordered moves
        List<Integer> ordered = new ArrayList<>();
        for (MoveScore ms : arr) ordered.add(ms.col);
        if (maximizing) {
            int value = Integer.MIN_VALUE;
            for (int col : ordered) {
                board.dropDisc(col, AI);
                value = Math.max(value, minimax(board, depth - 1, alpha, beta, false));
                board.undo(col);
                alpha = Math.max(alpha, value);
                if (alpha >= beta) break;
            }
            return value;
        }
        else {
            int value = Integer.MAX_VALUE;
            for (int col : ordered) {
                board.dropDisc(col, HUMAN);
                value = Math.min(value, minimax(board, depth - 1, alpha, beta, true));
                board.undo(col);
                beta = Math.min(beta, value);
                if (alpha >= beta) break;
            }
            return value;
        }
    }
}