package com.example.waterjournal;

/**
 * This class is for app user. It will define user's gender and weight and it will use this information to calculate
 * approximately the water amount user has to drink using WaterObject class.
 */
public class UserObject {

    //Creating private variables for class.
    private String gender;
    private double weight;
    private double amountYouShouldDrink;

    /**
     * This constructor will define values for user either using default values
     * or user's values which are his gender and weight.
     */
    public UserObject() {
        this.gender = "male";
        this.weight = 80;
        this.amountYouShouldDrink = this.weight * 0.033;
    }

    /**
     * This constructor with parameters will assign user's given values to two private variable and use weight parameter to determine
     * how much user should drink water per day. This will eventually be used when creating WaterObject object for user.
     * @param gender Will assign this parameter to private gender variable value.
     * @param weight Will assign this parameter to private weight variable value.
     */
    public UserObject(String gender, double weight) {
        this.gender = gender;
        this.weight = weight;
        this.amountYouShouldDrink = weight * 0.033;
    }

    /**
     * This function will change user's gender if user feels that he chose wrong gender or user has changed user's mind.
     * @param newGender This parameter will change private variable gender value to a new gender value.
     */
    public void changeGender(String newGender) {
        this.gender = newGender;
    }

    /**
     * This function will change user's weight and at the same time change the minimum amount of water user should drink per day.
     * @param newWeight Will change current weight in private weight variable to a new one.
     */
    public void changeWeight(double newWeight) {
        this.weight = newWeight;
        this.amountYouShouldDrink = newWeight * 0.033;
    }

    /**
     * This function will return user's weight.
     * @return Will return a double weight value of a specific user.
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * This function will return user's gender.
     * @return Will return a string value of user's gender.
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * This function will return user's amount of water user should drink.
     * @return Will return a double value about the amount of water user should at least drink per day.
     */
    public double getAmountYouShouldDrink() {
        return this.amountYouShouldDrink;
    }

    /**
     * This toString function will return some certain information about the user.
     * @return Will return a string value of user's information.
     */
    public String toString() {
        return "Your gender is " + this.gender + " and your weight is " + this.weight + ".";
    }
}
