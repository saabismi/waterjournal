package com.example.waterjournal;

import java.text.DecimalFormat;

public class WaterObject {

    private double amountOfWater;
    private double maximumWater;

    public WaterObject() {
        this.maximumWater = 2500;
        this.amountOfWater = 0;
    }

    public WaterObject(double amountOfWater, double maximumWater) {
        this.amountOfWater = amountOfWater;
        this.maximumWater = maximumWater;
    }

    public void AddingWater(double amount) {
        this.amountOfWater += amount;
    }

    public boolean drinkEnough() {
        return !(this.amountOfWater < this.maximumWater);
    }

    public void changeMaximum(double newMaximumAmount) {
        this.maximumWater = newMaximumAmount;
    }

    public double getAmountOfWater() {
        return this.amountOfWater;
    }

    public double drinkingProgress() {
        return (this.amountOfWater / this.maximumWater) * 100;
    }

    public String toString() {
        return "You have drank " + this.amountOfWater + "ml amount of water.\nIt's " + drinkingProgress() + "% of your daily progress.";
    }
}
