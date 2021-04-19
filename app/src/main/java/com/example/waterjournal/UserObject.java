package com.example.waterjournal;

//This class is for app user. It will define user's gender and weight and it will use
//this information to calculate approximately the water amount user has to drink using WaterObject class.
public class UserObject {

    //Creating private variables for class.
    private String gender;
    private double weight;
    private double amountYouShouldDrink;

    //These two constructors will define values for user either using default values
    //or user's values which are his gender and weight.
    public UserObject() {
        this.gender = "male";
        this.weight = 80;
        this.amountYouShouldDrink = this.weight * 0.033;
    }

    public UserObject(String gender, double weight) {
        this.gender = gender;
        this.weight = weight;
        this.amountYouShouldDrink = weight * 0.033;
    }

    //This function will change user's gender.
    public void changeGender(String newGender) {
        this.gender = newGender;
    }

    //This function will change user's weight.
    public void changeWeight(double newWeight) {
        this.weight = newWeight;
        this.amountYouShouldDrink = newWeight * 0.033;
    }

    //This function will return user's weight.
    public double getWeight() {
        return this.weight;
    }

    //This function will return user's gender.
    public String getGender() {
        return this.gender;
    }

    //This function will return user's amount of water user should drink.
    public double getAmountYouShouldDrink() {
        return this.amountYouShouldDrink;
    }

    //ToString function for user's information.
    public String toString() {
        return "Your gender is " + this.gender + " and your weight is " + this.weight + ".";
    }
}
