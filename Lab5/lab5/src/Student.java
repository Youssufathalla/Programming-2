/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hassa
 */
public class Student {

    private int StudentID;
    private String name;
    private int age;
    private double gpa;
    private String department;
    private String gender;

    public Student(int StudentID, String name, int age, String gender, String department, double gpa) {
        this.StudentID = StudentID;
        this.name = name;
        this.age = age;
        if (gpa < 4 && gpa > 0) {
            this.gpa = gpa;
        } else {
            this.gpa = 0;
        }
        this.department = department;
        this.gender = gender;
    }

    public int getStudentID() {
        return StudentID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGpa() {
        return gpa;
    }

    public String getDepartment() {
        return department;
    }

    public String getGender() {
        return gender;
    }

    public void setStudentID(int StudentID) {
        this.StudentID = StudentID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String LineRepresentation() {
        String s = this.getStudentID() + "," + this.getName() + "," + this.getAge() + "," + this.getGender() + "," + this.getDepartment() + "," + this.getGpa();
        return s;
    }
}
