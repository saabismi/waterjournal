package com.example.waterjournal;

import java.util.ArrayList;

/**
 * This singleton class will add each day water object to an ArrayList which will keep them in memory.
 * This will give user a chance to check his daily or monthly water usage and compare them.
 */
public class DailyDrinkingObject {

    //Creating variables for class.
    private ArrayList<WaterObject> dailyWater;
    private static final DailyDrinkingObject ourInstance = new DailyDrinkingObject();

    /**
     * This constructor will define new ArrayList for singleton class to use.
     */
    private DailyDrinkingObject() {
        this.dailyWater = new ArrayList<>();
        this.dailyWater.add(new WaterObject(2.5));
    }

    public static DailyDrinkingObject getInstance() {
        return ourInstance;
    }

    /**
     * This function will add yesterdays water amount to the ArrayList.
     * @param dailyAmount This parameter will add new WaterObject to private dailyWater list.
     */
    public void addDailyWater(WaterObject dailyAmount) {
        this.dailyWater.add(dailyAmount);
    }

    /**
     * This function will return ArrayList. It can be used in different ways for example in listView. User
     * will be able to check specific days and get information from them.
     * @return Will return an ArrayList<WaterObject> for anyone who is asking for it.
     */
    public ArrayList<WaterObject>getDailyWaterList() {
        return this.dailyWater;
    }

    /**
     * This function will return specific day.
     * @param listPosition Will use this given int number to search for a specific id in dailyWater list.
     * @return Will return the WaterObject which is in this specificDay id.
     */
    public ArrayList<String> getSpecificDay(int listPosition) {
        return this.dailyWater.get(listPosition).getDrinkAdded();
    }

    public WaterObject getSpecificWaterObject(int listPosition) {
        return this.dailyWater.get(listPosition);
    }

    public void createWaterObject(double minimumAmount) {
        this.dailyWater.add(new WaterObject(minimumAmount));
    }
}