package com.example.waterjournal;

import java.text.DecimalFormat;

//This class is for water drinking. It will tell user how much user have been drinking on specific
//day and will also tell user % daily amount.
public class WaterObject {

    //Creating variables for class.
    private double amountOfWater;
    private double minimumWater;

    //These two constructor will define private variables values with either default or
    //user values.
    public WaterObject() {
        this.minimumWater = 2500;
        this.amountOfWater = 0;
    }

    public WaterObject(double minimumWater) {
        this.amountOfWater = 0.0;
        this.minimumWater = minimumWater;
    }

    //This function will add water to daily water variable. User will define how much of water
    //will be added to the variable.
    public void AddingWater(double amount) {
        this.amountOfWater += amount;
    }

    //This function will tell user if user has been drinking enough water during the day.
    public boolean drinkEnough() {
        return !(this.amountOfWater < this.minimumWater);
    }

    //Will change the minimum amount of water user should drink daily.
    public void changeMinimum(double newMaximumAmount) {
        this.minimumWater = newMaximumAmount;
    }

    //This function will return the amount user has drank at specific point.
    public double getAmountOfWater() {
        return this.amountOfWater;
    }

    //This function will return user's daily drinking process.
    public double drinkingProgress() {
        return (this.amountOfWater / this.minimumWater) * 100;
    }

    //ToString function which will tell user the % amount user has drank today.
    public String toString() {
        return "You have drank " + this.amountOfWater + "ml amount of water.\nIt's " + drinkingProgress() + "% of your daily progress.";
    }
}
