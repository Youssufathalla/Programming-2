

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hassa
 */
public class StudentManagementSystem extends database{

    public StudentManagementSystem() {
        super();
    }

    @Override
    public Student createRecordFrom(String line) {
           String[] parts = line.split(",", -1);
        if (parts.length != 6) {
            return null;
        }
        Integer id =Integer.valueOf(parts[0].trim());
        String name = parts[1].trim();
        Integer age = Integer.valueOf(parts[2].trim());
        String gender = parts[3].trim();
        String department = parts[4].trim();
        Double gpa=Double.valueOf(parts[5].trim());
        return new Student(id, name, age, gender, department,gpa);
    }
    
    public void update(Student oldStudent,Student newStudent){
        
        deleteStudent(oldStudent.LineRepresentation());
        addStudent(newStudent);
        //error if deleted then addf failed due to input error
    
    }
 
   
    
}
