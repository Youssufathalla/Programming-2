/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab.pkg7;

import java.util.*;

/**
 *
 * @author youssufathalla
 */
public interface ManagementOperations<T> {
    void add(T obj);
    void delete(int id);
    void update(T obj);
    T search(int id);
    ArrayList<T> getAll();
    void saveToJson();
    void loadFromJson();
}