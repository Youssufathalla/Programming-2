/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab.pkg7;

/**
 *
 * @author youssufathalla
 */
import java.security.MessageDigest;
import java.util.ArrayList;

public class UserManager {

    private StudentManager studentManager;
    private InstructorManager instructorManager;

    public UserManager(StudentManager sm, InstructorManager im) {
        this.studentManager = sm;
        this.instructorManager = im;
    }

    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();

            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    private boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    public boolean signup(String role, int id, String username, String email, String password) {

        if (isEmpty(username) || isEmpty(email) || isEmpty(password) || isEmpty(role))
            return false;

        if (!isValidEmail(email))
            return false;

        String hashed = hashPassword(password);
        if (hashed == null) return false;

        if (role.equalsIgnoreCase("student")) {
            Student s = new Student(id, username, email, hashed);
            studentManager.add(s);
            return true;
        }

        if (role.equalsIgnoreCase("instructor")) {
            Instructor inst = new Instructor(id, username, email, hashed);
            instructorManager.add(inst);
            return true;
        }

        return false;
    }

    public Record login(String email, String password) {

        if (isEmpty(email) || isEmpty(password))
            return null;

        String hashed = hashPassword(password);
        if (hashed == null) return null;

        ArrayList<Record> students = studentManager.read();
        for (Record r : students) {
            Student s = (Student) r;
            if (s.getEmail().equals(email) && s.getPasswordHash().equals(hashed)) {
                return s;
            }
        }

        ArrayList<Record> instructors = instructorManager.read();
        for (Record r : instructors) {
            Instructor inst = (Instructor) r;
            if (inst.getEmail().equals(email) && inst.getPasswordHash().equals(hashed)) {
                return inst;
            }
        }

        return null;
    }

    public void logout() {}
}
