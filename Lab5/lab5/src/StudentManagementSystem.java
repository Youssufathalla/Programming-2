

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hassa
 */
public class StudentManagementSystem extends database{

    public StudentManagementSystem(String filename) {
        super(filename);
    }

    @Override
    public Student createRecordFrom(String line) {
           String[] parts = line.split(",", -1);
        if (parts.length != 6) {
            return null;
        }
        Integer id =Integer.parseInt(parts[0].trim());
        String name = parts[1].trim();
        Integer age = Integer.parseInt(parts[2].trim());
        String gender = parts[3].trim();
        String department = parts[4].trim();
        Double gpa=Double.parseDouble(parts[5].trim());
        return new Student(id, name, age, gender, department,gpa);
    }
 
   
    
}
