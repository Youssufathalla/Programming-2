/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab.pkg7;

import java.util.ArrayList;

/**
 *
 * @author hassa
 */
import java.util.ArrayList;

public class StudentManager implements Manager {

    private ArrayList<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

    @Override
    public void save(ArrayList<Record> s) {
        students.clear();

        for (Record r : s) {
            if (r instanceof Student) {
                students.add((Student) r);
            }
        }
    }

    @Override
    public ArrayList<Record> read() {
        ArrayList<Record> copy = new ArrayList<>();
        for (Student st : students) {
            copy.add(st);
            
            
            
            
            
            
            
        }
        return copy;
    }

    @Override
    public void add(Record s) {
        if (s instanceof Student) {
            students.add((Student) s);
        }
    }

    @Override
    public void delete(Record s) {
        if (!(s instanceof Student)) return;

        Student target = (Student) s;

        students.removeIf(st -> st.getUserId() == target.getUserId());
    }

    @Override
    public void update(Record s) {
        if (!(s instanceof Student)) return;

        Student updated = (Student) s;

        for (int i = 0; i < students.size(); i++) {
            Student st = students.get(i);

            if (st.getUserId() == updated.getUserId()) {
                students.set(i, updated);
                return;
            }
        }
    }

    @Override
    public Record search(int id) {
        for (Student st : students) {
            if (st.getUserId() == id) {
                return st;
            }
        }
        return null;
    }
}
