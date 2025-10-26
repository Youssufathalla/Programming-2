
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author hassa
 */
public class Users {

    private ArrayList<User> users = new ArrayList<>();

    public Users() {
        readFromFile();
    }

    public void readFromFile() {
        users.clear();
        File file = new File("users.txt");
        try {

            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                User u = createUserFrom(line);
                if (u != null) {
                    users.add(u);
                }
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    public User createUserFrom(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length != 2) {
            return null;
        }
        String username = parts[0].trim();
        String password = parts[1].trim();
        return new User(username, password);
    }

    public boolean contains(String uname, String password) {

        for (User u : users) {
            if (u.getUsername().equals(uname) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

}
