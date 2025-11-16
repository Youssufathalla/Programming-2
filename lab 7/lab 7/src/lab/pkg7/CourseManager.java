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

    public ArrayList<Course> getCourses() {
        return courses;
    }

    @Override
    public void save(ArrayList<Record> s) {
        courses.clear();

        for (Record r : s) {
            if (r instanceof Course) {
                courses.add((Course) r);
            }
        }
    }

    @Override
    public ArrayList<Record> read() {
        ArrayList<Record> copy = new ArrayList<>();
        for (Course c : courses) {
            copy.add(c);
        }
        return copy;
    }

    @Override
    public void add(Record s) {
        if (s instanceof Course) {
            courses.add((Course) s);
        }
    }

    @Override
    public void delete(Record s) {
        if (!(s instanceof Course)) return;

        Course target = (Course) s;
        courses.removeIf(c -> c.getCourseId() == target.getCourseId());
    }

    @Override
    public void update(Record s) {
        if (!(s instanceof Course)) return;

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
                return c;
            }
        }
        return null;
    }
}
