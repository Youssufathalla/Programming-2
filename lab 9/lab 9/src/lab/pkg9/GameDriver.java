package lab.pkg9;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GameDriver {

    private final int[][] solvedBoard;

    public static final String EASY = "Easy";
    public static final String MEDIUM = "Medium";
    public static final String HARD = "Hard";

    public GameDriver(int[][] solvedBoard) {
        this.solvedBoard = solvedBoard;
    }

    public Map<String, int[][]> generateAll() throws Exception {

        // 1. Verify the source board
        Verifier verifier = new Verifier(solvedBoard);
        if (verifier.run() != GameState.VALID) {
            throw new Exception("Source board is not valid");
        }

        // 2. Copy the solved board for each difficulty
        int[][] easy = copyBoard(solvedBoard);
        int[][] medium = copyBoard(solvedBoard);
        int[][] hard = copyBoard(solvedBoard);

        // 3. Remove cells for each difficulty
        removeCells(easy, 10);   // Easy: Remove 10 cells
        removeCells(medium, 20); // Medium: Remove 20 cells
        removeCells(hard, 25);   // Hard: Remove 25 cells

        // 4. Save the generated games in a Map
        Map<String, int[][]> games = new HashMap<>();
        games.put(EASY, easy);
        games.put(MEDIUM, medium);
        games.put(HARD, hard);

        return games;
    }

    // Helper method to copy the solved board
    private int[][] copyBoard(int[][] original) {
        int[][] copy = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                copy[i][j] = original[i][j];
            }
        }
        return copy;
    }

    // Method to remove 'count' number of cells from the board using RandomPairs
    private void removeCells(int[][] board, int count) {
        RandomPairs randomPairs = new RandomPairs();
        List<int[]> pairs = randomPairs.generateDistinctPairs(count);

        for (int[] pair : pairs) {
            int r = pair[0];
            int c = pair[1];

            if (board[r][c] != 0) {  // Ensure the cell isn't already empty
                board[r][c] = 0;      // Remove the cell (set to 0)
            }
        }
    }
}
