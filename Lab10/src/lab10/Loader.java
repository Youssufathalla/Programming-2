package lab10;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

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

    private Game loadGameBasedOnDifficulty() throws IOException {
        String difficulty = getDifficultyFromFile();
        
        if (difficulty == null) {
            System.out.println("No valid difficulty files found. Defaulting to Easy.");
            difficulty = "easy";  // Default to easy if no files are found
        }
        
        String fileName = difficulty + ".txt";
        File gameFile = new File(fileName);

        if (!gameFile.exists()) {
            System.out.println("Game file for " + difficulty + " is missing.");
            return null;
        }

        return loadGameFromFile(gameFile);
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

    private String getDifficultyFromFile() {
        File easyFile = new File("easy.txt");
        File mediumFile = new File("medium.txt");
        File hardFile = new File("hard.txt");

        if (easyFile.exists()) {
            return "easy";
        } else if (mediumFile.exists()) {
            return "medium";
        } else if (hardFile.exists()) {
            return "hard";
        } else {
            return null;
        }
    }

    private String askForSolvedSudokuPath() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide the path to a solved Sudoku board file: ");
        return scanner.nextLine().trim();
    }

    private Game generateGamesFromSolved(String solvedGamePath) throws Exception {
        String solvedBoard = readFile(new File(solvedGamePath));
        return new Game(solvedBoard);
    }

    private Game loadNewGame() throws Exception {
        String solvedGamePath = askForSolvedSudokuPath();
        return generateGamesFromSolved(solvedGamePath);
    }
}
