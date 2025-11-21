/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab.pkg7;

import java.util.ArrayList;
import java.util.HashMap;

public class Student extends User implements Record {

    private ArrayList<Integer> enrolledCourses;
    private HashMap<Integer, HashMap<Integer, Integer>> quizScores;

    public Student(int userId, String username, String email, String passwordHash) {
        super(userId, "student", username, email, passwordHash);
        this.enrolledCourses = new ArrayList<>();
        this.quizScores = new HashMap<>();
    }

    public void enrollCourse(int courseId) {
        if (!enrolledCourses.contains(courseId)) {
            enrolledCourses.add(courseId);

        }
    }

    public ArrayList<Integer> getEnrolledCourses() {
        return new ArrayList<>(enrolledCourses);
    }

    public HashMap<Integer, HashMap<Integer, Integer>> getQuizScores() {
        return quizScores;
    }

    public void saveQuizScore(int courseId, int lessonId, int score) {
        quizScores.putIfAbsent(courseId, new HashMap<>());
        quizScores.get(courseId).put(lessonId, score);
    }

    public Integer getQuizScore(int courseId, int lessonId) {
        if (!quizScores.containsKey(courseId)) {
            return null;
        }
        return quizScores.get(courseId).get(lessonId);
    }

    @Override
    public void openDashboard() {

    }

}