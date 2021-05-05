package com.example.waterjournal;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * This class is for water drinking. It will tell user how much user have been drinking on specific day
 * and will also tell user % daily amount.
 */
public class WaterObject {

    //Creating variables for class.
    private double amountOfWater;
    private double minimumWater;
    private String currentDate;
    private long dateCheck;
    private ArrayList<String> drinkAddedText;
    private ArrayList<Double> drinksTaken;
    private ArrayList<Date> timeAdded;

    /**
     * This default constructor will define default values to private variables.
     */
    public WaterObject() {
        this.minimumWater = 2500;
        this.amountOfWater = 0;
        this.drinkAddedText = new ArrayList<>();
        this.timeAdded = new ArrayList<>();
        this.drinksTaken = new ArrayList<>();
        this.currentDate = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "." + Calendar.getInstance().get(Calendar.MONTH) + "." +
                Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * This constructor will define default values to private variables which user has defined.
     *
     * @param minimumWater This parameter will give variable minimumWater value. This value will tell
     *                     how much user should minimum drink during a day.
     */
    public WaterObject(double minimumWater) {
        this.amountOfWater = 0.0;
        this.minimumWater = minimumWater * 1000;
        this.drinkAddedText = new ArrayList<>();
        this.timeAdded = new ArrayList<>();
        this.drinksTaken = new ArrayList<>();
        this.currentDate = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "." + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "." +
                Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * This function will add water to daily water variable. User will define how much of water will be added to the variable.
     *
     * @param amount This parameter will add a specific amount of water to amountOfWater variable. It will also add the
     *               same parameter value to drinkAdded list
     */
    public void addingWater(double amount) {
        this.amountOfWater += amount;
        int amountInt = (int) amount;
        String help = "You drank " + amountInt + " ml at " + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE);
        this.drinkAddedText.add(help);
        this.timeAdded.add(Calendar.getInstance().getTime());
        this.drinksTaken.add(amount);
        Log.d("Listan sisältö lisäyksen jälkeen", this.drinksTaken.toString());
    }

    /**
     * This function will remove last added amount of water from amountOfWater variable.
     * For example user has miss clicked add water button and wants to remove the extra water.
     */
    public void removingWater() {
        if (this.amountOfWater - this.drinksTaken.get(this.drinksTaken.size() - 1) <= 0) {
            this.amountOfWater = 0;
        } else {
            this.amountOfWater -= this.drinksTaken.get(this.drinksTaken.size() - 1);
        }
        this.drinkAddedText.remove(this.drinkAddedText.size() - 1);
        this.timeAdded.remove(this.timeAdded.size() - 1);
        this.drinksTaken.remove(this.drinksTaken.size() - 1);
        Log.d("Listan sisältö poiston jälkeen", this.drinksTaken.toString());
    }

    public double lastAddedDrink() {
        return this.drinksTaken.get(this.drinksTaken.size() - 1);
    }

    /**
     * This function will tell user if user has been drinking enough water during the day.
     *
     * @return Will return either true of false depending if user has drank enough water during day.
     */
    public boolean drinkEnough() {
        return !(this.amountOfWater < this.minimumWater);
    }

    /**
     * Will change the minimum amount of water user should drink daily.
     *
     * @param newMinimumAmount This parameter will change the minimum amount of water user should drink during day.
     */
    public void changeMinimum(double newMinimumAmount) {
        this.minimumWater = newMinimumAmount;
    }

    /**
     * This function will return the amount user has drank during the day.
     *
     * @return Will return amountOfWater variable.
     */
    public double getAmountOfWater() {
        return this.amountOfWater;
    }

    /**
     * This function will return user's daily drinking process.
     *
     * @return Will return a double value which is a percent of drinking progress.
     */
    public double drinkingProgress() {
        return (this.amountOfWater / this.minimumWater) * 100;
    }

    /**
     * This function will reset amountOfWater variable and drinkAdded list when a new day starts.
     *
     * @return Will return the amount which user drank on that specific day.
     */
    public double dailyReset() {
        double dailyAmount = this.amountOfWater;
        this.amountOfWater = 0;
        this.drinkAddedText.clear();
        this.timeAdded.clear();
        this.drinksTaken.clear();
        this.currentDate = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "." + Calendar.getInstance().get(Calendar.MONTH) + "." +
                Calendar.getInstance().get(Calendar.YEAR);
        return dailyAmount;
    }

    public ArrayList<Date> getTimeAdded() {
        return this.timeAdded;
    }

    public ArrayList<String> getDrinkAdded() {
        return this.drinkAddedText;
    }

    /**
     * This function will tell user how much the user has drank during the day and how much is the daily progress.
     *
     * @return Will return a string variable to user.
     */
    public String getInformation() {
        int amountOfWaterInt = (int) this.amountOfWater;
        int drinkingProgressInt = (int) drinkingProgress();
        int minimumWaterInt = (int) minimumWater;
        return "You (have) drank " + amountOfWaterInt + " ml.\nIt is " + drinkingProgressInt + "% of your daily target which is " + minimumWaterInt + " ml.\nYou (have) drank " + this.drinkAddedText.size() + " time(s) on this day.";
    }

    public String toString() {
        return this.currentDate;
    }

    public double getMinimumWater() {
        return this.minimumWater;
    }
}