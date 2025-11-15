/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab.pkg7;

/**
 *
 * @author youssufathalla
 */
import java.util.ArrayList;

public class CourseManager implements Manager {

    private ArrayList<Course> courses;

    public CourseManager() {
        courses = new ArrayList<>();
    }

    @Override
    public void save(ArrayList<Record> s) {
        courses.clear();

        for (Record r : s) {
            if (r instanceof Course) {
                courses.add((Course) r);
            } else {
                System.out.println("Error: Non-course object passed to save().");
            }
        }
    }

    @Override
    public ArrayList<Record> read() {
        ArrayList<Record> copy = new ArrayList<>();
        for (Course c : courses) {
            copy.add((Record) c);
        }
        return copy;
    }

    @Override
    public void add(Record s) {
        if (s instanceof Course) {
            courses.add((Course) s);
        } else {
            System.out.println("Error: Only Course objects can be added.");
        }
    }

    @Override
    public void delete(Record s) {
        if (!(s instanceof Course)) {
            System.out.println("Error: Cannot delete non-course object.");
            return;
        }

        Course target = (Course) s;

        courses.removeIf(c -> c.getCourseId() == target.getCourseId());
    }

    @Override
    public void update(Record s) {
        if (!(s instanceof Course)) {
            System.out.println("Error: Cannot update non-course object.");
            return;
        }

        Course updated = (Course) s;

        for (int i = 0; i < courses.size(); i++) {
            Course c = courses.get(i);

            if (c.getCourseId() == updated.getCourseId()) {
                courses.set(i, updated);
                return;
            }
        }
    }

    @Override
    public Record search(int id) {
        for (Course c : courses) {
            if (c.getCourseId() == id) {
                return (Record) c;
            }
        }
        return null;
    }
}
