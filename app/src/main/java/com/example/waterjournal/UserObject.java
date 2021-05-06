package com.example.waterjournal;

import java.text.DecimalFormat;

/**
 * This class is for app user. It will define user's weight and it will use this information to calculate
 * approximately the water amount user has to drink using WaterObject class.
 */
public class UserObject {

    //Creating private variables for class.
    //weight variable is used for keeping track on user's current weight.
    //minimumAmount variable is used for keeping track on user's current weight.
    private int weight;
    private double minimumAmount;

    /**
     * This in default constructor for UserObject if there happens something with the app.
     * It will give default values for two private variables.
     */
    public UserObject() {
        this.weight = 75;
        this.minimumAmount = calcMinimumAmount(this.weight);
    }

    /**
     * This constructor with parameters will assign user's given values to two private variable and use weight parameter to determine
     * how much user should drink water per day. This will eventually be used when creating WaterObject object for user.
     * @param weight Will assign this parameter to private weight variable value.
     */
    public UserObject(int weight) {
        this.weight = weight;
        this.minimumAmount = calcMinimumAmount(weight);
    }

    /**
     * This function will calculate the minimum water amount and convert it to a one decimal double.
     * After that it will return it.
     */
    public double calcMinimumAmount(int weight) {
        // use this to format to one decimal number
        DecimalFormat dec = new DecimalFormat("#.0");

        // calculate default target ( = 75kg)
        double minimumAmount = weight * 0.033;
        return Double.valueOf(dec.format(minimumAmount));
    }


    /**
     * This function will change user's weight and at the same time change the minimum amount of water user should drink per day.
     * @param newWeight Will change current weight in private weight variable to a new one.
     */
    public void changeWeight(int newWeight) {
        this.weight = newWeight;
        this.minimumAmount = calcMinimumAmount(weight);
    }

    /**
     * This function will change user's minimum amount user should be drinking daily without changing user's weight.
     * @param newMinimumAmount This parameter will define new minimum amount of water user should drink at least per day.
     */
    public void changeMinimumAmount(double newMinimumAmount) {
        // use this to format to one decimal number
        DecimalFormat dec = new DecimalFormat("#.0");
        // convert the new minimum amount to one decimal
        this.minimumAmount = Double.valueOf(dec.format(newMinimumAmount));
    }

    /**
     * This function will return user's weight.
     * @return Will return a double weight value of a specific user.
     */
    public int getWeight() {
        return this.weight;
    }

    /**
     * This function will return user's amount of water user should drink.
     * @return Will return a double value about the amount of water user should at least drink per day.
     */
    public double getMinimumAmount() {
        return this.minimumAmount;
    }

    /**
     * This toString function will return some certain information about the user.
     * @return Will return a string value of user's information.
     */
    public String toString() {
        return"Your weight is " + this.weight + ", and drinking target is: " + this.minimumAmount;
    }
}