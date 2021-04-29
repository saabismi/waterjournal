package com.example.waterjournal;

/**
 * This class is for app user. It will define user's gender and weight and it will use this information to calculate
 * approximately the water amount user has to drink using WaterObject class.
 */
public class UserObject {

    //Creating private variables for class.
    private int weight;
    private double minimumAmount;

    /**
     * This constructor will define values for user either using default values
     * or user's values which are his gender and weight.
     */
    public UserObject() {
        this.weight = 80;
        this.minimumAmount = this.weight * 0.033;
    }

    /**
     * This constructor with parameters will assign user's given values to two private variable and use weight parameter to determine
     * how much user should drink water per day. This will eventually be used when creating WaterObject object for user.
     * @param weight Will assign this parameter to private weight variable value.
     */
    public UserObject(int weight) {
        this.weight = weight;
        this.minimumAmount = weight * 0.033;
    }

    /**
     * This function will change user's weight and at the same time change the minimum amount of water user should drink per day.
     * @param newWeight Will change current weight in private weight variable to a new one.
     */
    public void changeWeight(int newWeight) {
        this.weight = newWeight;
        this.minimumAmount = newWeight * 0.033;
    }

    public void changeMinimumAmount(double newMinimumAmount) {
        this.minimumAmount = newMinimumAmount;
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
    public double getminimumAmount() {
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
