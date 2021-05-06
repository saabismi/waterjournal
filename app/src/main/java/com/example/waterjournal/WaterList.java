package com.example.waterjournal;

import java.util.ArrayList;

public class WaterList {

    public ArrayList<Integer> waters;
    private ArrayList<WaterObject> dailyWater;

    public WaterList() {
        this.waters = new ArrayList<>();
        this.dailyWater = new ArrayList<>();
    }

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


    /**
     * Get the whole list of WaterObjects
     * @return
     */
    public ArrayList<WaterObject>getDailyWaterList() {
        return this.dailyWater;
    }

    /**
     * Add a water object to the list
     * @param object
     */
    public void addObject(WaterObject object) {dailyWater.add(object);}

    /**
     * Get the length of the WaterObject list
     * @return
     */
    public int getObjectSize() {return dailyWater.size();}

    /**
     * Get the WaterObject at specified index
     * @param index
     * @return
     */
    public WaterObject getObject(int index) {return dailyWater.get(index);}

    public void resetObjects() {
        dailyWater = null;
    }
}
