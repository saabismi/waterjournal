package com.example.waterjournal;

import android.app.Application;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import com.example.waterjournal.R;

/**
 * This class is for water drinking. It will tell user how much user have been drinking on specific day
 * and will also tell user % daily amount.
 * @author Andreas Mattson, Vilho Syvähuoko.
 */
public class WaterObject {

    //Creating variables for class.
    //
    //
    //
    //
    //
    /**
     * amountOfWater variable will keep track how much user has been drinking on specific day.
     */
    private double amountOfWater;
    /**
     * minimumWater variable will remember how much user should drink at least per day.
     */
    private double minimumWater;
    /**
     * currentDate variable will keep track which date it is.
     */
    private String currentDate;
    /**
     * drinkAddedText list will add a string sentence about water and what time it was taken.
     */
    private ArrayList<String> drinkAddedText;
    /**
     * drinksTaken list will keep track how much user has taken on specific day and what size drinks.
     */
    private ArrayList<Double> drinksTaken;
    /**
     * timeAdded list will keep track at which time user has taken drinks.
     */
    private ArrayList<Date> timeAdded;
    String drank;

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

        //This if-sentence will put correct timestamp when user adds new drink.
        if (Calendar.getInstance().get(Calendar.MINUTE) > 9) {
            this.drinkAddedText.add("You drank" + amountInt + " ml at " + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE));
        } else {
            this.drinkAddedText.add("You drank " + amountInt + " ml at " + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":0" + Calendar.getInstance().get(Calendar.MINUTE));
        }
        this.timeAdded.add(Calendar.getInstance().getTime());
        this.drinksTaken.add(amount);
        Log.d("Listan sisältö lisäyksen jälkeen", this.drinksTaken.toString());
    }

    /**
     * This function will remove last added amount of water from amountOfWater variable.
     * For example user has miss clicked add water button and wants to remove the extra water.
     * Function will also check that the water amount won't go less than 0 using if-sentence.
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

    /**
     * This function will return user's last added drink.
     *
     * @return Will return last added drink.
     */
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

    /**
     * This function will return timeAdded list which variables inside the list are date times
     * when user drank.
     * @return Will return timeAdded list.
     */
    public ArrayList<Date> getTimeAdded() {
        return this.timeAdded;
    }

    /**
     * This function will return drinkAddedText list for singleton class to use it in SpecificDayView class for listView.
     * @return Will return drinkAddedText list.
     */
    public ArrayList<String> getDrinkAdded() {
        return this.drinkAddedText;
    }

    /**
     * This function will give user information about user. It will tell how user has drank water and daily progress regarding to it.
     * It will also tell the minimum amount which should be drank during the day and how many times user has been drinking during the specific day.
     *
     * @return Will return information using string class.
     */
    public String getInformation() {
        int amountOfWaterInt = (int) this.amountOfWater;
        int drinkingProgressInt = (int) drinkingProgress();
        int minimumWaterInt = (int) minimumWater;
        return "You (have) drank " + amountOfWaterInt + " ml.\nIt is " + drinkingProgressInt + "% of your daily target which is " + minimumWaterInt + " ml.\nYou (have) drank " + this.drinkAddedText.size() + " time(s) on this day.";
    }

    /**
     * This toString function will return current date for HistoryActivity to use in listView.
     * @return Will return current date.
     */
    public String toString() {
        return this.currentDate;
    }

    /**
     * This function will return current date for user.
     * @return Will return current date.
     */
    public Calendar getDate() {
        return Calendar.getInstance();
    }

    /**
     * This function will return list of water types user took during day. For example user drank 5 times and this function
     * will return those five times in drinksTaken ArrayList.
     * @return Will return user's specific day drinks.
     */
    public ArrayList<Double> getDrinksTaken() {
        return this.drinksTaken;
    }

    /**
     * This function will return minimum amount water user should drink daily.
     * @return Will return minimum amount of water.
     */
    public double getMinimumWater() {
        return this.minimumWater;
    }
}