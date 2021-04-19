package com.example.waterjournal;

public class UserObject {

    private String gender;
    private double weight;

    public UserObject() {
        this.gender = "male";
        this.weight = 80;
    }

    public UserObject(String gender, double weight) {
        this.gender = gender;
        this.weight = weight;
    }

    public void changeGender(String newGender) {
        this.gender = newGender;
    }

    public void changeWeight(double newWeight) {
        this.weight = newWeight;
    }

    public double getWeight() {
        return this.weight;
    }

    public String getGender() {
        return this.gender;
    }

    public String toString() {
        return "Your gender is " + this.gender + " and your weight is " + this.weight + ".";
    }
}
