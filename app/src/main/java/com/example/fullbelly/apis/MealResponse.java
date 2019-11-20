package com.example.fullbelly.apis;

import java.util.List;

import com.example.fullbelly.model.Meal;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MealResponse {

    @SerializedName("meals")
    @Expose
    private List<Meal> meals = null;

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

}



