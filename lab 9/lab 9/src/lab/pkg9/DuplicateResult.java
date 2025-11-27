/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab.pkg9;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hassa
 */
public class DuplicateResult {
       private List<String> errors = new ArrayList<>();

    public void addError(String error) {
        errors.add(error);
    }

    public boolean isValid() {
        return errors.isEmpty();
    }
    
    public List<String> getErrors() {
       return errors;
    }

}
