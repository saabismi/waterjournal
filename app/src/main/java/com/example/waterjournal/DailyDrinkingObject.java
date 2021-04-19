package com.example.waterjournal;

import java.util.ArrayList;

public class DailyDrinkingObject {

    private ArrayList<WaterObject> dailyWater;

    public DailyDrinkingObject() {
        this.dailyWater = new ArrayList<>();
    }

    public void addDailyWater(WaterObject dailyAmount) {
        this.dailyWater.add(dailyAmount);
    }

    public ArrayList<WaterObject>getDailyWaterList() {
        return this.dailyWater;
    }

    public WaterObject getSpecificDay(int specificDay) {
        return this.dailyWater.get(specificDay);
    }
}
