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

public class Instructor extends User {

    private ArrayList<Integer> createdCourses;

    public Instructor(int userId, String username, String email, String passwordHash) {
        super(userId, username, email, passwordHash, "instructor");
        this.createdCourses = new ArrayList<>();
    }

    public void addCreatedCourse(int courseId) {
        if (!createdCourses.contains(courseId)) {
            createdCourses.add(courseId);
        }
    }

    public ArrayList<Integer> getCreatedCourses() {
        return new ArrayList<>(createdCourses); //encapsulation
    }

    @Override
    public void openDashboard() {
        System.out.println("Opening Instructor Dashboard...");
    }
}