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

public class Instructor extends User implements Record {

    private ArrayList<Integer> createdCourses;

    public Instructor(int userId, String username, String email, String passwordHash) {
        super(userId, "instructor", username, email, passwordHash);
        this.createdCourses = new ArrayList<>();
    
    }
    public void addCreatedCourse(int courseId) {
        if (!createdCourses.contains(courseId)) {
            createdCourses.add(courseId);
        }
    }

    public ArrayList<Integer> getCreatedCourses() {
        return createdCourses;
    }

    
    public void addLessonToCourse(Course course, String title, String content,Boolean completed) {
    int newId = course.getLessons().size() + 1;
    Lesson lesson = new Lesson(newId, title, content,completed,0,0);
    course.addLesson(lesson);
}


    @Override
    public void openDashboard() {
    }
}
