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
import java.util.HashMap;

public class Student extends User implements Record {

    private ArrayList<Integer> enrolledCourses;
    private HashMap<Integer, Double> progress;

    public Student(int userId, String username, String email, String passwordHash) {
        super(userId, "student", username, email, passwordHash);
        this.enrolledCourses = new ArrayList<>();
        this.progress = new HashMap<>();
    }

    public void enrollCourse(int courseId) {
        if (!enrolledCourses.contains(courseId)) {
            enrolledCourses.add(courseId);
            progress.put(courseId, 0.0);
        }
    }

    public void updateProgress(int courseId, int lessonIndex) {
        if (progress.containsKey(courseId)) {
            progress.put(courseId, (double) lessonIndex);
        }
    }

    public Double getProgress(int courseId) {
        return progress.get(courseId);
    }

    public ArrayList<Integer> getEnrolledCourses() {
        return new ArrayList<>(enrolledCourses);
    }

    @Override
    public void openDashboard() {
       
    }
}
