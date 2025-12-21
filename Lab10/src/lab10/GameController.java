package lab10;

import java.io.IOException;

public class GameController {

    private final Game game;

    public GameController(Game game) {
        this.game = game;
    }

    public void makeMove(int x, int y, int val) {
        try {
            game.makeMove(x, y, val);  
            System.out.println("Move made.");
        } catch (IOException e) {
            System.err.println("Error making move: " + e.getMessage());
        }
    }

    public void undoMove() {
        try {
            game.undo();  
            System.out.println("Move undone.");
        } catch (IOException e) {
            System.err.println("Error undoing move: " + e.getMessage());
        }
    }
}