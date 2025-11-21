/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab.pkg7;

/**
 *
 * @author youssufathalla
 */

public class admin extends User implements Record{

    public admin(int userId, String role, String username, String email, String passwordHash) {
        super(userId, role, username, email, passwordHash);
    }
    public Course approve(Course c){
    c.setApproval("APPROVED") ;
    return c;
    }
    public Course reject(Course c){
    c.setApproval("REJECTED") ;
    return c;
    }
    
    
    
    
       @Override
    public void openDashboard() {
       
    }
    
}
