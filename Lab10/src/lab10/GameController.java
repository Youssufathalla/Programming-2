package lab10;

import java.io.IOException;

public class GameController {

    private final Game game;
    private final GameStorageManager gameStorageManager;

    public GameController(Game game, GameStorageManager gameStorageManager) {
        this.game = game;
        this.gameStorageManager = gameStorageManager;
    }

    public void undoMove() {
        game.undo();
        System.out.println("Move undone.");

        try {
            gameStorageManager.saveGame(game.getBoard(), game.getDifficulty());
            System.out.println("Game state saved after undo.");
        } catch (IOException e) {
            System.err.println("Error saving game state after undo: " + e.getMessage());
        }
    }

    public void makeMove(int row, int col, int value) {
        game.saveState();
        game.getBoard()[row][col] = value;
        System.out.println("Move made.");

        try {
            gameStorageManager.saveGame(game.getBoard(), game.getDifficulty());
            System.out.println("Game state saved after move.");
        } catch (IOException e) {
            System.err.println("Error saving game state after move: " + e.getMessage());
        }
    }
}