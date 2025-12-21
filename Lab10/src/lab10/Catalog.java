package lab10;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Catalog {

    private boolean current;
    private boolean allModesExist;

    public Catalog() {
        this.current = checkUnfinishedGame();
        this.allModesExist = checkGamesInDifficultyFiles();
    }

    public boolean hasUnfinishedGame() {
        return current;
    }

    public boolean hasGamesInDifficultyFiles() {
        return allModesExist;
    }

    private boolean checkUnfinishedGame() {
        File currentGameFile = new File("current_game.txt");

        if (currentGameFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(currentGameFile))) {
                String line = br.readLine();
                return line != null && line.equals("INCOMPLETE");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean checkGamesInDifficultyFiles() {
        return isValidGameFile("easy.txt") && isValidGameFile("medium.txt") && isValidGameFile("hard.txt");
    }

    private boolean isValidGameFile(String fileName) {
        File gameFile = new File(fileName);

        if (!gameFile.exists()) {
            return false;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(gameFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
