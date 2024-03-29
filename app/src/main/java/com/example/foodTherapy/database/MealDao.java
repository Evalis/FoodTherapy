package com.example.foodTherapy.database;


import com.example.foodTherapy.model.Meal;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MealDao {
    @Insert
    void insert(Meal meal);

    @Update
    void update(Meal meal);

    @Delete
    void delete(Meal meal);

    @Query("SELECT * FROM meal_table ORDER BY strMeal")
    LiveData<List<Meal>> getAllFavoriteMeals();
}
