package lab.pkg7;

import java.util.ArrayList;
import java.util.HashMap;

public class Student extends User implements Record {

    private ArrayList<Integer> enrolledCourses;
    private double progress;
    private HashMap<Integer, HashMap<Integer, Integer>> quizScores;
    private ArrayList<Certificate> certificates;
    private HashMap<Integer, HashMap<Integer, Boolean>> lessonCompleted;
    private HashMap<Integer, HashMap<Integer, Boolean>> quizCompleted;

    public Student(int userId, String username, String email, String passwordHash) {
        super(userId, "student", username, email, passwordHash);
        this.enrolledCourses = new ArrayList<>();
        this.quizScores = new HashMap<>();
        this.certificates = new ArrayList<>();
        this.lessonCompleted = new HashMap<>();
        this.quizCompleted = new HashMap<>();
    }

    public void enrollCourse(int courseId) {
        if (!enrolledCourses.contains(courseId)) {
            enrolledCourses.add(courseId);
        }
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
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

    public ArrayList<Certificate> getCertificates() {
        if (certificates == null) {
            certificates = new ArrayList<>();
        }
        return certificates;
    }

    public void addCertificate(Certificate c) {
        getCertificates().add(c);
    }

    public boolean hasCertificateForCourse(int courseId) {
        for (Certificate c : getCertificates()) {
            if (c.getCourseId() == courseId) {
                return true;
            }
        }
        return false;
    }

    public HashMap<Integer, HashMap<Integer, Boolean>> getLessonCompleted() {
        return lessonCompleted;
    }

    public HashMap<Integer, HashMap<Integer, Boolean>> getQuizCompletedMap() {
        return quizCompleted;
    }

    public void markLessonCompleted(int courseId, int lessonId) {
        lessonCompleted.putIfAbsent(courseId, new HashMap<>());
        lessonCompleted.get(courseId).put(lessonId, true);
    }

    public boolean isLessonCompleted(int courseId, int lessonId) {
        return lessonCompleted.containsKey(courseId)
                && lessonCompleted.get(courseId).getOrDefault(lessonId, false);
    }

    public void markQuizCompleted(int courseId, int lessonId) {
        quizCompleted.putIfAbsent(courseId, new HashMap<>());
        quizCompleted.get(courseId).put(lessonId, true);
    }

    public boolean isQuizCompleted(int courseId, int lessonId) {
        return quizCompleted.containsKey(courseId)
                && quizCompleted.get(courseId).getOrDefault(lessonId, false);
    }

    public boolean isEnrolled(int courseId) {
    return enrolledCourses.contains(courseId);
}
    
    @Override
    public void openDashboard() {
    }
}