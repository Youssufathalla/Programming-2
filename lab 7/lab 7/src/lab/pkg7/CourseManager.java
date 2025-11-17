package lab.pkg7;

import java.util.ArrayList;

public class CourseManager {

    private ArrayList<Record> records;

    public CourseManager() {
        this.records = new ArrayList<>();
    }

    // Used by CreateCourse
    public void add(Course c) {
        if (c != null && !contains(c.getCourseId())) {
            records.add(c);
        }
    }

    // Used optionally by other screens (same as add)
    public void addCourse(Course c) {
        add(c);
    }

    // Check if a course already exists
    public boolean contains(int id) {
        return getCourseById(id) != null;
    }

    // Find a course safely
    public Record search(int id) {
        return getCourseById(id);
    }

    // Return all records (used by CreateCourse idExists)
    public ArrayList<Record> read() {
        return records;
    }

    // Replace entire list (used ONLY by JsonDatabase.loadCourses)
    public void save(ArrayList<Record> list) {
        if (list == null) {
            this.records = new ArrayList<>();
        } else {
            this.records = list;
        }
    }

    // Return real Course objects for browsing
    public ArrayList<Course> getCourses() {
        ArrayList<Course> list = new ArrayList<>();
        for (Record r : records) {
            if (r instanceof Course) {
                list.add((Course) r);
            }
        }
        return list;
    }

    // Safely get a course
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

    // Remove a course from system
    public boolean removeCourse(int id) {
        Course c = getCourseById(id);
        if (c != null) {
            return records.remove(c);
        }
        return false;
    }
}
