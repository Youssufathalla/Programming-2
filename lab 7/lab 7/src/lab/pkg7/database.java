/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab.pkg7;

/**
 *
 * @author youssufathalla
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public abstract class Database {

    private ArrayList<Database> records;
    private String filename;

    public Database() {
        this.records = new ArrayList<>();
        readFromFile();
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
                Database cp = createRecordFrom(line);
                if (cp != null) {
                    records.add(cp);
                }
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
     public abstract String LineRepresentation();

    public void saveToFile() {
        if (filename == null || filename.isEmpty()) {
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, false))) {
            for (Database p : records) {
                bw.write(p.LineRepresentation());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to write " + filename, e);
        }
    }

    public abstract Database createRecordFrom(String line);

    public ArrayList<Database> returnAllRecords() {
        return records;
    }

    public boolean contains(int id) {
        int k = id;
        for (Database cp : records) {
            if (cp.getId()== k) {
                return true;
            }
        }
        return false;
    }

    public void add(Database record) {
        records.add(record);
        saveToFile();
    }

    public void delete(String key) {
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getId() == Integer.parseInt(key)) {
                records.remove(i);
                saveToFile();
                break;
            }
        }

    }
    public abstract int getId();
    public abstract String getName();

    public ArrayList<Database> getRecordByName(String name) {
        ArrayList<Database> database = new ArrayList<>();
        for (Database s : records) {
            if (s.getName().equals(name)) {
                database.add(s);
            }
        }
        if (!database.isEmpty()) {
            return database;
        }
        return null;
    }

    public Database getRecord(int key) {

        for (Database cp : records) {
            if (cp.getId() == key) {
                return cp;
            }
        }
        return null;
    }
}
