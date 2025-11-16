/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab.pkg7;

/**
 *
 * @author hassa
 */
import java.util.ArrayList;

public class InstructorManager implements Manager {

    private ArrayList<Instructor> instructors;

    public InstructorManager() {
        instructors = new ArrayList<>();
    }

    @Override
    public void save(ArrayList<Record> s) {
        instructors.clear();

        for (Record r : s) {
            if (r instanceof Instructor) {
                instructors.add((Instructor) r);
            }
        }
    }

    @Override
    public ArrayList<Record> read() {
        ArrayList<Record> copy = new ArrayList<>();
        for (Instructor inst : instructors) {
            copy.add(inst);
        }
        return copy;
    }

    @Override
    public void add(Record s) {
        if (s instanceof Instructor) {
            instructors.add((Instructor) s);
        }
    }

    @Override
    public void delete(Record s) {
        if (!(s instanceof Instructor)) return;

        Instructor target = (Instructor) s;

        instructors.removeIf(inst -> inst.getUserId() == target.getUserId());
    }

    @Override
    public void update(Record s) {
        if (!(s instanceof Instructor)) return;

        Instructor updated = (Instructor) s;

        for (int i = 0; i < instructors.size(); i++) {
            Instructor inst = instructors.get(i);

            if (inst.getUserId() == updated.getUserId()) {
                instructors.set(i, updated);
                return;
            }
        }
    }

    @Override
    public Record search(int id) {
        for (Instructor inst : instructors) {
            if (inst.getUserId() == id) {
                return inst;
            }
        }
        return null;
    }
}
