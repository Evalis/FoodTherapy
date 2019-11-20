package com.example.fullbelly.repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.example.fullbelly.apis.MealAPI;
import com.example.fullbelly.apis.MealResponse;
import com.example.fullbelly.apis.MealServiceGenerator;
import com.example.fullbelly.database.MealDao;
import com.example.fullbelly.database.MealDatabase;
import com.example.fullbelly.model.Meal;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealRepository {

    private static MealRepository instance;
   private MutableLiveData<List<Meal>> meals;

    private MealDao mealDao;



    private MealRepository( Application application) {

        meals = new MutableLiveData<>();

        MealDatabase database = MealDatabase.getInstance(application);
        mealDao = database.mealDao();
    }

    public void insert(Meal meal)
    {
        new InsertMealAsyncTask(mealDao).execute(meal);
    }

    public void update(Meal meal)
    {
        new UpdateMealAsyncTask(mealDao).execute(meal);
    }

    public void delete(Meal meal)
    {
        new DeleteMealAsyncTask(mealDao).execute(meal);
    }

    public LiveData<List<Meal>> getAllFavoriteMeals()
    {
        return mealDao.getAllFavoriteMeals() ;
    }

    private static class InsertMealAsyncTask extends AsyncTask<Meal, Void, Void>
    {
        private MealDao mealDao;

        private InsertMealAsyncTask(MealDao mealDao)
        {
            this.mealDao = mealDao;
        }

        @Override
        protected Void doInBackground(Meal... meals)
        {
            mealDao.insert(meals[0]);
           return null;
        }
    }
    private static class UpdateMealAsyncTask extends AsyncTask<Meal, Void, Void>
    {
        private MealDao mealDao;

        private UpdateMealAsyncTask(MealDao mealDao)
        {
            this.mealDao = mealDao;
        }

        @Override
        protected Void doInBackground(Meal... meals)
        {
            mealDao.update(meals[0]);
            return null;
        }
    }
    private static class DeleteMealAsyncTask extends AsyncTask<Meal, Void, Void>
    {
        private MealDao mealDao;

        private DeleteMealAsyncTask(MealDao mealDao)
        {
            this.mealDao = mealDao;
        }

        @Override
        protected Void doInBackground(Meal... meals)
        {
            mealDao.delete(meals[0]);
            return null;
        }
    }


    public static synchronized MealRepository getInstance( Application application) {
        if (instance == null) {
            instance = new MealRepository(application);
        }
        return instance;
    }

    public LiveData<List<Meal>> getAllMeals() {
        return meals;
    }

    public void updateMeals(String query) {
        MealAPI mealAPI = MealServiceGenerator.getMealApi();
        Call<MealResponse> call = mealAPI.getAllMeals(query);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                Log.i("Retrofit", "Response received " + response.code() + ", "+ response.message() + ", "+ response.body() + ", "+ response.errorBody() + ", "+ response.headers() + ", "+ response.raw());
                if (response.code() == 200) {
                    Log.i("Retrofit", "Good response");
                    meals.setValue(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }
}


