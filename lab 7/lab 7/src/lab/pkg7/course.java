/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab.pkg7;

import java.util.*;

/**
 *
 * @author youssufathalla
 */
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

        // Empty lists instead of null
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
        if (lessons == null) lessons = new ArrayList<>();
        return lessons;
    }

    public ArrayList<Integer> getEnrolledStudents() {
        if (enrolledStudents == null) enrolledStudents = new ArrayList<>();
        return enrolledStudents;
    }

    public void addLesson(Lesson lesson) {
        if (lessons == null) lessons = new ArrayList<>();
        lessons.add(lesson);
    }
}

