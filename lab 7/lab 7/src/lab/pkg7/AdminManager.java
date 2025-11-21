/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab.pkg7;

import java.util.ArrayList;

public class AdminManager implements Manager {

    private ArrayList<admin> admins;

    public AdminManager() {
        admins = new ArrayList<>();
    }

    @Override
    public void save(ArrayList<Record> a) {
        admins.clear();
        for (Record r : a) {
            if (r instanceof admin) {
                admins.add((admin) r);
            }
        }
    }

    @Override
    public ArrayList<Record> read() {
        ArrayList<Record> list = new ArrayList<>();
        list.addAll(admins);
        return list;
    }

    @Override
    public void add(Record r) {
        if (r instanceof admin) {
            admins.add((admin) r);
        }
    }

    @Override
    public void delete(Record r) {
        if (r instanceof admin) {
            admin target = (admin) r;
            admins.removeIf(ad -> ad.getUserId() == target.getUserId());
        }
    }

    @Override
    public void update(Record r) {
        if (r instanceof admin) {
            admin updated = (admin) r;
            for (int i = 0; i < admins.size(); i++) {
                if (admins.get(i).getUserId() == updated.getUserId()) {
                    admins.set(i, updated);
                    return;
                }
            }
        }
    }

    @Override
    public Record search(int id) {
        for (admin a : admins) {
            if (a.getUserId() == id) {
                return a;
            }
        }
        return null;
    }
}
