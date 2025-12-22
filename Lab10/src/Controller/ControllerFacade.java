package Controller;

import Model.Loader;
import java.io.IOException;
import View.UserAction;

public class ControllerFacade implements Controllable {

    private static ControllerFacade instance;
    private final GameController controller;

    private ControllerFacade() {
        controller = new GameController(new Loader());
    }

    public static ControllerFacade getInstance() {
        if (instance == null) {
            instance = new ControllerFacade();
        }
        return instance;
    }

    @Override
    public boolean[] getCatalog() {
        return controller.getCatalog();
    }

    @Override
    public int[][] getGame(char level) throws NotFoundException {
        return controller.getGame(level);
    }

    @Override
    public void driveGames(String sourcePath) throws SolutionInvalidException {
        controller.driveGames(sourcePath);
    }

    @Override
    public boolean[][] verifyGame(int[][] game) {
        return controller.verifyGame(game);
    }

    @Override
    public int[][] solveGame(int[][] game) throws InvalidGame {
        return controller.solveGame(game);
    }

    @Override
    public void logUserAction(UserAction userAction) throws IOException {
        controller.logUserAction(userAction);
    }

    @Override
    public int[][] undo(int[][] board) throws IOException {
        return controller.undo(board);
    }

    @Override
    public void completeCurrentGame() throws IOException {
        controller.completeCurrentGame();
    }

}
