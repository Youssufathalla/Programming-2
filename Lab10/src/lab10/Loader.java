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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose a difficulty (Easy, Medium, Hard): ");
        String difficulty = scanner.nextLine().trim().toLowerCase();

        String fileName = "";
        if ("easy".equals(difficulty)) {
            fileName = "easy.txt";
        } else if ("medium".equals(difficulty)) {
            fileName = "medium.txt";
        } else if ("hard".equals(difficulty)) {
            fileName = "hard.txt";
        } else {
            System.out.println("Invalid difficulty choice. Defaulting to Easy.");
            fileName = "easy.txt";
        }

        File gameFile = new File(fileName);
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
