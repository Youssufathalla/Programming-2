package lab.pkg9;
import java.io.BufferedReader;
import java.io.FileReader;

public class BoardReader {

    // Reads a 9x9 CSV file, values 1–9 (no sanitization needed as per lab)
    public static int[][] read(String path) throws Exception {
        int[][] board = new int[9][9];
        BufferedReader br = new BufferedReader(new FileReader(path));

        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            if (line == null) {
                throw new RuntimeException("File has less than 9 lines");
            }
            String[] parts = line.split(",");
            if (parts.length != 9) {
                throw new RuntimeException("Line " + (i + 1) + " does not have 9 values");
            }
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(parts[j].trim());
            }
        }

        br.close();
        return board;
    }
}
