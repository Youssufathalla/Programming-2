package lab10;



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameStorageManager {

    private static final String CURRENT_GAME_FILE = "current.csv";

    public void saveGame(int[][] board, String difficulty) throws IOException {
        if (difficulty != null) {
            saveBoard(board, difficulty);
        } else {
            saveCurrentGame(board);
        }
    }

    private void saveBoard(int[][] board, String difficulty) throws IOException {
        String filename = difficulty.toLowerCase() + "_" + System.currentTimeMillis() + ".csv"; // Save with difficulty as string
        Path filePath = Paths.get(filename);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (j > 0) writer.write(",");
                    writer.write(board[i][j] + "");
                }
                writer.newLine();
            }
        }
    }

    private void saveCurrentGame(int[][] board) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j > 0) sb.append(",");
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }

        Files.write(Paths.get(CURRENT_GAME_FILE), sb.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public void moveCurrentGameToFile(String difficulty) throws IOException {
        if (Files.exists(Paths.get(CURRENT_GAME_FILE))) {
            int[][] board = readCsv(CURRENT_GAME_FILE);
            saveBoard(board, difficulty);
            Files.delete(Paths.get(CURRENT_GAME_FILE));
        }
    }

    private int[][] readCsv(String fileName) throws IOException {
        return BoardReader.read(fileName);
    }

    public boolean hasCurrentGame() {
        return Files.exists(Paths.get(CURRENT_GAME_FILE));
    }

    public void deleteCurrentGame() throws IOException {
        Files.deleteIfExists(Paths.get(CURRENT_GAME_FILE));
    }

    public Game loadGame(String difficulty) throws IOException {
        List<String> games = listGamesForDifficulty(difficulty);
        if (games.isEmpty()) {
            throw new IOException("No saved games for " + difficulty);
        }

        String selectedGame = games.get(new Random().nextInt(games.size()));
        int[][] board = readCsv(selectedGame);

        return new Game(board, difficulty);
    }

    private List<String> listGamesForDifficulty(String difficulty) throws IOException {
        List<String> games = new ArrayList<>();
        String prefix = difficulty.toLowerCase();  

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("."), prefix + "_*.csv")) {
            for (Path entry : stream) {
                games.add(entry.toString());
            }
        }

        return games;
    }
}
