/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab.pkg7;

/**
 *
 * @author youssufathalla
 */
public class admin extends User {

    public admin(int userId, String role, String username, String email, String passwordHash) {
        super(userId, role, username, email, passwordHash);
    }
       @Override
    public void openDashboard() {
       
    }
}
