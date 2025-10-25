package copies;


import java.util.ArrayList;



public class AdminRole {

    private database db;

    public AdminRole() {
        this.db = new EmployeeUserDatabase("Employees.txt");
        this.db.readFromFile();
    }

    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber) {
        if (!db.contains(employeeId)) {
            EmployeeUser e = new EmployeeUser(employeeId, name, email, address, phoneNumber);
            db.insertRecord(e);
        }
    }

      public EmployeeUser[] getListOfEmployees() {
          ArrayList<item> x=db.returnAllRecords();
          EmployeeUser[]y =new EmployeeUser[x.size()];
        return (EmployeeUser[]) db.returnAllRecords().toArray(y);
   
    }

    public void removeEmployee(String key) {
        db.deleteRecord(key);
    }

    public void logout() {
        db.saveToFile();
    }
}
