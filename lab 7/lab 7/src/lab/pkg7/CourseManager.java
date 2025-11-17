package lab.pkg7;

import java.util.ArrayList;

public class CourseManager {

    private ArrayList<Record> records;

    public CourseManager() {
        this.records = new ArrayList<>();
    }

    public void add(Course c) {
        if (c != null) {
            records.add(c);
        }
    }

    public void addCourse(Course c) {
        add(c);
    }

    public Record search(int courseId) {
        for (Record r : records) {
            if (r instanceof Course) {
                Course c = (Course) r;
                if (c.getCourseId() == courseId) {
                    return c;
                }
            }
        }
        return null;
    }

    public boolean contains(int courseId) {
        return search(courseId) != null;
    }

    public ArrayList<Record> read() {
        return records;
    }

    public void save(ArrayList<Record> list) {
        if (list == null) {
            this.records = new ArrayList<>();
        } else {
            this.records = list;
        }
    }

    public ArrayList<Course> getCourses() {
        ArrayList<Course> list = new ArrayList<>();
        for (Record r : records) {
            if (r instanceof Course) {
                list.add((Course) r);
            }
        }
        return list;
    }

    public Course getCourseById(int id) {
        for (Record r : records) {
            if (r instanceof Course) {
                Course c = (Course) r;
                if (c.getCourseId() == id) {
                    return c;
                }
            }
        }
        return null;
    }
}
