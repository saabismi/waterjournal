package com.example.waterjournal;

import java.util.ArrayList;

/**
 * @author Andeass Mattson, Vilho Syvähuoko.
 */
public class WaterList {

    public ArrayList<Integer> waters = new ArrayList<>();

    public WaterList() {}

    public void addWater(int amount) {
        waters.add(amount);
    }

    public void removeLatestwater() {
        waters.remove(waters.size()-1);
    }

    public void reset() {
        waters = null;
    }

    public int getSize() {
        return waters.size();
    }

    public ArrayList<Integer> getValues() {
        return waters;
    }

    public int getValue(int index) {
        return waters.get(index);
    }
}
