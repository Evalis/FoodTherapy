package com.example.fullbelly.database;

import android.content.Context;
import android.os.AsyncTask;

import com.example.fullbelly.model.Meal;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Meal.class}, version = 1)
public abstract class MealDatabase extends RoomDatabase {

    private static MealDatabase instance;
    public abstract MealDao mealDao();

    public static synchronized MealDatabase getInstance(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MealDatabase.class, "meal_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private MealDao mealDao;

       private PopulateDbAsyncTask(MealDatabase db)
        {
            mealDao = db.mealDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Meal m2 = new Meal();
            m2.setStrMeal("Chips");
            m2.setStrInstructions("Long instruction");
            mealDao.insert(m2);
            return null;
        }
    }


}
