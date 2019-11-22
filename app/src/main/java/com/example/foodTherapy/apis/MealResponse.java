package com.example.foodTherapy.apis;

import java.util.List;

import com.example.foodTherapy.model.Meal;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MealResponse {

    @SerializedName("meals")
    @Expose
    private List<Meal> meals = null;

    public List<Meal> getMeals() {
        return meals;
    }



}



