/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab.pkg7;

/**
 *
 * @author youssufathalla
 */
public class Lab7 {

    public static StudentManager studentManager;
    public static InstructorManager instructorManager;
    public static CourseManager courseManager;
    public static UserManager userManager;

    public static void main(String[] args) {
        studentManager = new StudentManager();
        instructorManager = new InstructorManager();
        courseManager = new CourseManager();

        JsonDatabase.loadUsers(studentManager, instructorManager);
        JsonDatabase.loadCourses(courseManager);

        userManager = new UserManager(studentManager, instructorManager);

        new LoginFrame().setVisible(true);
    }
}
