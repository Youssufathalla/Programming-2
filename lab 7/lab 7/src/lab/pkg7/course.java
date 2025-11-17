package lab.pkg7;

import java.util.ArrayList;

public class Course implements Record {

    private int courseId;
    private String title;
    private String description;
    private int instructorId;

    private ArrayList<Lesson> lessons;
    private ArrayList<Integer> enrolledStudents;

    public Course(int courseId, String title, String description, int instructorId) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.instructorId = instructorId;
        this.lessons = new ArrayList<>();
        this.enrolledStudents = new ArrayList<>();
    }

    public int getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public ArrayList<Integer> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void addLesson(Lesson lesson) {
        if (lesson != null) {
            lessons.add(lesson);
        }
    }

    public void addStudent(int studentId) {
        enrolledStudents.add(studentId);
    }
}
