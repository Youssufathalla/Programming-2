
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public abstract class database {

    private ArrayList<item> records = new ArrayList<>();
    private String filename;

    public database(String filename) {
        this.filename = filename;
    }

    public void readFromFile() {
        records.clear();
        File file = new File(filename);
        try {
            if (!file.exists()) {
                file.createNewFile();
                return;
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                item cp = createRecordFrom(line);
                if (cp != null) {
                    records.add(cp);
                }
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    public void saveToFile() {
        if (filename == null || filename.isEmpty()) {
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, false))) {
            for (item p : records) {
                bw.write(p.lineRepresentation());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to write " + filename, e);
        }
    }

    public abstract item createRecordFrom(String line);

    public ArrayList<item> returnAllRecords() {
        return records;
    }

    public boolean contains(String key) {
        String k = key == null ? "" : key.trim();
        for (item cp : records) {
            if (cp.getSearchKey().trim().equals(k)) {
                return true;
            }
        }
        return false;
    }

    public void insertRecord(item record) {
        records.add(record);
    }

    public void deleteRecord(String key) {
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getSearchKey().equals(key)) {
                records.remove(i);
                break;
            }
        }
    }

    public item getRecord(String key) {
        String k = key == null ? "" : key.trim();
        for (item cp : records) {
            if (cp.getSearchKey().trim().equals(k)) {
                return cp;
            }
        }
        return null;
    }
}
