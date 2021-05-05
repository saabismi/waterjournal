package com.example.waterjournal;

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
    private Date currentDate;
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
        this.currentDate = Calendar.getInstance().getTime();
    }

    /**
     * This constructor will define default values to private variables which user has defined.
     *
     * @param minimumWater This parameter will give variable minimumWater value. This value will tell
     *                     how much user should minimum drink during a day.
     */
    public WaterObject(double minimumWater) {
        this.amountOfWater = 0.0;
        this.minimumWater = minimumWater;
        this.drinkAddedText = new ArrayList<>();
        this.timeAdded = new ArrayList<>();
        this.drinksTaken = new ArrayList<>();
        this.currentDate = Calendar.getInstance().getTime();
    }

    /**
     * This function will add water to daily water variable. User will define how much of water will be added to the variable.
     *
     * @param amount This parameter will add a specific amount of water to amountOfWater variable. It will also add the
     *               same parameter value to drinkAdded list
     */
    public void addingWater(double amount) {
        this.amountOfWater += amount;
        String help = "You drank " + amount + " at " + Calendar.getInstance().getTime();
        this.drinkAddedText.add(help);
        this.timeAdded.add(Calendar.getInstance().getTime());
        this.drinksTaken.add(amount);
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
        this.drinksTaken.remove(this.drinksTaken.size() -1);
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
        this.currentDate = Calendar.getInstance().getTime();
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
        return "You have drank " + this.amountOfWater + "ml amount of water.\nIt's " + drinkingProgress() + "% of your daily progress.\n"
        + "Your minimum amount water you should drink daily is " + this.minimumWater + " litres.\nYou have drank " + this.drinkAddedText.size() + " time(s) today.";
    }

    public String toString() {
        return this.currentDate.toString();
    }

    public double getMinimumWater() {
        return this.minimumWater;
    }
}