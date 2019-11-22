package com.example.foodTherapy.viewModel;

import android.app.Application;

import com.example.foodTherapy.model.Meal;
import com.example.foodTherapy.repositories.MealRepository;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MealViewModel extends AndroidViewModel {

    MealRepository repository;

    public MealViewModel( Application application){
        super(application);
        repository = MealRepository.getInstance(application);
    }

    public LiveData<List<Meal>> getAllMeals() {

        return repository.getAllMeals();
    }
    public LiveData<List<Meal>> getAllFavoriteMeals()
    {
        return repository.getAllFavoriteMeals() ;
    }


    public void insert(final Meal meal) {

        repository.insert(meal);
    }
    public void updateMeals(String query) {

        repository.updateMeals(query);
    }

    public void delete(Meal meal)
    {
        repository.delete(meal);
    }
}
