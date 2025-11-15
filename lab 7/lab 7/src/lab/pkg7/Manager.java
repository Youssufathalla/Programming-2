package lab.pkg7;

import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author hassa
 */
public interface Manager {

    public abstract void save(ArrayList<Record> s);
    public abstract ArrayList<Record> read();
    public abstract void add(Record s);
    public abstract void delete(Record s);
    public abstract void update(Record s);
    public abstract  search(Record s);
}
