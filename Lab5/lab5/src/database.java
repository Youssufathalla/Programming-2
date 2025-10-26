
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public abstract class database {

    private ArrayList<Student> records ;
    private String filename="students.txt";

    public database() {
        this.records= new ArrayList<>();
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
                Student cp = createRecordFrom(line);
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
            for (Student p : records) {
                bw.write( p.LineRepresentation());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to write " + filename, e);
        }
    }

    public abstract Student createRecordFrom(String line);

    public ArrayList<Student> returnAllRecords() {
        return records;
    }

    public boolean contains(int id) {
        int k=id;
        for (Student cp : records) {
            if (cp.getStudentID()==k) {
                return true;
            }
        }
        return false;
    }

    public void addStudent(Student record) {
        records.add(record);
        saveToFile();
    }

    public void deleteStudent(String key) {
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getStudentID()==Integer.parseInt(key)) {
                records.remove(i);
                saveToFile();
                break;
            }
        }
        
    }
    public ArrayList<Student> getRecordByName(String name){
        ArrayList<Student> students=new ArrayList<>();
        for(Student s:records){
        if(s.getName().equals(name))
        {
            students.add(s);
        }
        }
       if(!students.isEmpty()){
       return students;}
       return null;
    }
    public Student getRecord(int key) {
        
        for (Student cp : records) {
            if (cp.getStudentID()==key) {
                return cp;
            }
        }
        return null;
    }
}
