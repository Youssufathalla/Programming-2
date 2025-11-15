/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab.pkg7;

import java.awt.Desktop;
import java.lang.invoke.MethodHandles;
import java.util.*;

/**
 *
 * @author youssufathalla
 */
public class Student extends User {
   

    private ArrayList<Integer> enrolledCourses;
    private HashMap<Integer,Double> progress;

    

    public Student(HashMap<Integer, ArrayList> progress, int userId, String role, String username, String email, String passwordHash,ArrayList enrolledCourses) {
        super(userId, role, username, email, passwordHash);
        this.progress = new HashMap<>();
        this.enrolledCourses = new ArrayList<>();
    }
    public Student(int userId, String role, String username, String email, String passwordHash) {
        super(userId, role, username, email, passwordHash);
    }        
            public void enrollCourse(int courseId) {
        if (!enrolledCourses.contains(courseId)) {
            enrolledCourses.add(courseId);
            progress.put(courseId,Double.valueOf(0));
        }
    }

    public void updateProgress(int courseId, int lessonIndex) {
        if (progress.containsKey(courseId)) {
            progress.put(courseId,Double.valueOf(lessonIndex));
        }
    }

    public Double getProgress(int courseId) {
        return progress.get(courseId);
    }

    public ArrayList<Integer> getEnrolledCourses() {
        return new ArrayList<>(enrolledCourses); //encapsulation
    }

    @Override
    public void openDashboard() {
        System.out.println("Opening Student Dashboard...");
    }
}
        
        
        
        
        
    

