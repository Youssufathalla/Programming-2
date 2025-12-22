package Model;

import java.util.List;

public class BoardFlyweight {

    private final int[][] board;

    public BoardFlyweight(int[][] board) {
        this.board = board;
    }

    public boolean isValidCombination(int[] combination, List<int[]> emptyCells) {
        for (int i = 0; i < emptyCells.size(); i++) {
            int[] emptyCell = emptyCells.get(i);
            board[emptyCell[0]][emptyCell[1]] = combination[i];
        }
        return isValidBoard();
    }

    private boolean isValidBoard() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    continue;
                }
                if (!isValidMove(row, col, board[row][col])) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidMove(int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num && i != col) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num && i != row) {
                return false;
            }
        }

        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num && (i != row || j != col)) {
                    return false;
                }
            }
        }

        return true;
    }
}
