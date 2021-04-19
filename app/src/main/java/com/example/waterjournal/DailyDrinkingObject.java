package com.example.waterjournal;

import java.util.ArrayList;

//This function will add each day water object to an ArrayList which will keep them in memory.
//This will give user a chance to check his daily or monthly water usage and compare them.
public class DailyDrinkingObject {

    //Creating variables for class.
    private ArrayList<WaterObject> dailyWater;

    //This constructor will define the new ArrayList.
    public DailyDrinkingObject() {
        this.dailyWater = new ArrayList<>();
    }

    //This function will add yesterdays water amount to the ArrayList.
    public void addDailyWater(WaterObject dailyAmount) {
        this.dailyWater.add(dailyAmount);
    }

    //This function will return ArrayList. It can be used in different ways for example in listView. User
    //will be able to check specific days and get information from them.
    public ArrayList<WaterObject>getDailyWaterList() {
        return this.dailyWater;
    }

    //This function will return specific day.
    public WaterObject getSpecificDay(int specificDay) {
        return this.dailyWater.get(specificDay);
    }
}
