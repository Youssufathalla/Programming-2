import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Loader {

    public Game loadGame(Catalogue catalogue) throws Exception {
        if (catalogue.hasUnfinishedGame()) {
            return loadUnfinishedGame();
        } else if (catalogue.hasGamesInDifficultyFiles()) {
            return loadGameBasedOnDifficulty();
        } else {
            return loadNewGame();
        }
    }

    private Game loadUnfinishedGame() throws IOException {
        File currentGameFile = new File("current_game.txt");
        
        try (BufferedReader br = new BufferedReader(new FileReader(currentGameFile))) {
            StringBuilder boardContent = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                boardContent.append(line).append("\n");
            }
            return new Game(boardContent.toString());
        }
    }

    private Game loadGameBasedOnDifficulty() {
        File easyFile = new File("easy.txt");
        return loadGameFromFile(easyFile);
    }

    private Game loadNewGame() throws Exception {
        String solvedGamePath = askForSolvedSudokuPath();
        return generateGamesFromSolved(solvedGamePath);
    }

    private Game loadGameFromFile(File gameFile) {
        String boardContent = readFile(gameFile);
        return new Game(boardContent);
    }

    private String readFile(File file) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    private String askForSolvedSudokuPath() {
        return "path_to_solved_sudoku.txt";
    }

    private Game generateGamesFromSolved(String solvedGamePath) throws Exception {
        String solvedBoard = readFile(new File(solvedGamePath));
        return new Game(solvedBoard);
    }
}
