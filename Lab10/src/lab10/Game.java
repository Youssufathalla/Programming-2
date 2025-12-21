package lab10;

import java.io.*;
import java.nio.file.*;

public class Game {

    private int[][] board;
    private String difficulty;
    private final Path logFilePath;

    public Game(int[][] board, String difficulty) {
        this.board = board;
        this.difficulty = difficulty;
        this.logFilePath = Paths.get("incomplete", "log.txt");  // Log file path
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void makeMove(int x, int y, int val) throws IOException {
        int prev = board[x][y];

        board[x][y] = val;

        logMove(x, y, val, prev);
    }

    private void logMove(int x, int y, int val, int prev) throws IOException {
        String logEntry = String.format("(%d, %d, %d, %d)\n", x, y, val, prev);

        Files.createDirectories(Paths.get("incomplete"));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath.toFile(), true))) {
            writer.write(logEntry);
        }
    }

    public void undo() throws IOException {
        String lastLog = readLastLogEntry();
        if (lastLog != null) {
            String[] parts = lastLog.replace("(", "").replace(")", "").split(", ");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            int val = Integer.parseInt(parts[2]);
            int prev = Integer.parseInt(parts[3]);

            board[x][y] = prev;

            removeLastLogEntry();
        }
    }

    private String readLastLogEntry() throws IOException {
        if (!Files.exists(logFilePath)) {
            return null;
        }

        try (BufferedReader reader = Files.newBufferedReader(logFilePath)) {
            String lastLine = null;
            String line;
            while ((line = reader.readLine()) != null) {
                lastLine = line;
            }
            return lastLine;
        }
    }

    private void removeLastLogEntry() throws IOException {
        Path tempFile = Files.createTempFile("log", ".tmp");
        try (BufferedReader reader = Files.newBufferedReader(logFilePath); BufferedWriter writer = Files.newBufferedWriter(tempFile)) {
            String line;
            boolean lastLine = false;
            while ((line = reader.readLine()) != null) {
                if (lastLine) {
                    break;
                }
                writer.write(line + System.lineSeparator());
                lastLine = true;
            }
        }
        Files.move(tempFile, logFilePath, StandardCopyOption.REPLACE_EXISTING);
    }
}