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
public class Student extends User {
   

    private ArrayList<Integer> enrolledCourses = new ArrayList<>(); 
    private HashMap<Integer,ArrayList> progress;
//new hashmap??

    public Student(int userId, String role, String username, String email, String passwordHash) {
        super(userId, role, username, email, passwordHash);
    }
}