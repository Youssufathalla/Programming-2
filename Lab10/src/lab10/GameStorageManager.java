package lab10;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameStorageManager {

    private static final String CURRENT_GAME_FILE = "current.csv";

    public void saveGame(int[][] board, String difficulty) throws IOException {
        if (hasCurrentGame()) {
            saveCurrentGame(board);
        } else {
            saveBoard(board, difficulty);
        }
    }

    private void saveBoard(int[][] board, String difficulty) throws IOException {
        String filename = difficulty.toLowerCase() + "_" + System.currentTimeMillis() + ".csv"; 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
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

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CURRENT_GAME_FILE))) {
            writer.write(sb.toString());
        }
    }

    public boolean hasCurrentGame() {
        return new java.io.File(CURRENT_GAME_FILE).exists();  
    }

    public void deleteCurrentGame() throws IOException {
        new java.io.File(CURRENT_GAME_FILE).delete();  
    }

    public Game loadGame(String difficulty) throws Exception {
        List<String> games = new ArrayList<>();
        String prefix = difficulty.toLowerCase();  

        for (String fileName : new java.io.File(".").list()) {
            if (fileName.startsWith(prefix) && fileName.endsWith(".csv")) {
                games.add(fileName);  
            }
        }

        if (games.isEmpty()) {
            throw new IOException("No saved games for " + difficulty);  
        }

        String selectedGame = games.get(new Random().nextInt(games.size()));  
        int[][] board = readCsv(selectedGame);  

        return new Game(board, difficulty);  
    }

    private int[][] readCsv(String fileName) throws Exception {
        return BoardReader.read(fileName);  
    }

    public void startNewGame(int[][] newBoard, String difficulty) throws IOException {
        deleteCurrentGame();  
        saveBoard(newBoard, difficulty);  
    }
}