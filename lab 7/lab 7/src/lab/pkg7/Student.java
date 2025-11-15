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
    private HashMap<Integer,ArrayList> progress;

    

    public Student(HashMap<Integer, ArrayList> progress, int userId, String role, String username, String email, String passwordHash,ArrayList enrolledCourses) {
        super(userId, role, username, email, passwordHash);
        this.progress = progress;
        this.enrolledCourses = enrolledCourses;
    }
    public Student(int userId, String role, String username, String email, String passwordHash) {
        super(userId, role, username, email, passwordHash);
        
        public browseCourse(){}
        public enrollCourse(){}
        public accessLesson(){}
        public trackProgress(){}
        
        
        
        
        
        
    }   
}
