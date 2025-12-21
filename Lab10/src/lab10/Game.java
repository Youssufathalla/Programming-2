package lab10;

import java.util.Stack;

public class Game {

    private int[][] board;
    private String difficulty;
    private final Stack<int[][]> history;

    public Game(int[][] board, String difficulty) {
        this.board = board;
        this.difficulty = difficulty;
        this.history = new Stack<>();
        saveState();
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void saveState() {
        int[][] boardCopy = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boardCopy[i][j] = board[i][j];
            }
        }
        history.push(boardCopy);
    }

    public void undo() {
        if (!history.isEmpty()) {
            board = history.pop();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Difficulty: ").append(difficulty).append("\n");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(board[i][j]);
                if (j < 8) {
                    sb.append(", ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}