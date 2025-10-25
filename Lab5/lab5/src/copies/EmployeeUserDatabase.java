package copies;


public class EmployeeUserDatabase extends database {

    public EmployeeUserDatabase(String filename) {
        super(filename);
    }

    @Override
    public EmployeeUser createRecordFrom(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length != 5) {
            return null;
        }
        String id = parts[0].trim();
        String name = parts[1].trim();
        String email = parts[2].trim();
        String address = parts[3].trim();
        String phone = parts[4].trim();
        return new EmployeeUser(id, name, email, address, phone);
    }

}
