package lab10;


public class GameController {

    private final Game game;

    public GameController(Game game) {
        this.game = game;
    }

    // Call this method when the user wants to undo the last move
    public void undoMove() {
        game.undo();  // Undo the last move
        System.out.println("Move undone.");
    }

    // Call this method when the user makes a valid move
    public void makeMove(int row, int col, int value) {
        game.saveState();  // Save the state before making the move
        game.getBoard()[row][col] = value;  // Apply the move
        System.out.println("Move made.");
    }
}
