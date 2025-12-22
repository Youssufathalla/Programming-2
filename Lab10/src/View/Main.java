package View;
import Controller.Controllable;
import Controller.ControllerFacade;
import Model.BoardReader;
import Model.Catalog;
import Model.Verifier;

public class Main {

    public static void main(String[] args) throws Exception {

        
Controllable controller = ControllerFacade.getInstance();

    Viewable view = new GameView(controller);

    Catalog catalog = view.getCatalog();
    new StarterFrame(view).setVisible(true);

    System.out.println("Game in progress: " + catalog.hasUnfinishedGame());
    System.out.println("All modes exist: " + catalog.hasGamesInDifficultyFiles());

        int[][] board = BoardReader.read(args[0]);
        Verifier verifier = new Verifier(board);

        System.out.println(verifier.run());
    }
}