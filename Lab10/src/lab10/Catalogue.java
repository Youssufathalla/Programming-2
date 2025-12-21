package lab10;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Catalogue {

    public boolean hasUnfinishedGame() {
        File currentGameFile = new File("current_game.txt");

        if (currentGameFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(currentGameFile))) {
                String line = br.readLine();
                if (line != null && line.equals("INCOMPLETE")) {
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean hasGamesInDifficultyFiles() {
        return isValidGameFile("easy.txt") && isValidGameFile("medium.txt") && isValidGameFile("hard.txt");
    }

    private boolean isValidGameFile(String fileName) {
        File gameFile = new File(fileName);

        if (!gameFile.exists()) {
            return false;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(gameFile))) {
            String line;
            boolean hasContent = false;
            while ((line = br.readLine()) != null) {
                hasContent = true;
                break;
            }

            return hasContent;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
