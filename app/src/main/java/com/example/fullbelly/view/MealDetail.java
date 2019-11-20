package com.example.fullbelly.view;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.fullbelly.R;
import com.example.fullbelly.model.Meal;
import com.example.fullbelly.viewModel.MealViewModel;



import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProviders;

public class MealDetail extends AppCompatActivity {

    private MealViewModel mealViewModel;
    private Meal meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);

        final int mealIndex;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                mealIndex = -1;
            } else {
                mealIndex = extras.getInt("mealIndex");
            }
        } else {
            mealIndex = (int) savedInstanceState.getSerializable("mealIndex");
        }

        final boolean favorite;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                favorite = false;
            } else {
                favorite = extras.getBoolean("favorite");
            }
        } else {
            favorite = (boolean) savedInstanceState.getSerializable("favorite");
        }


        mealViewModel = ViewModelProviders.of(this).get(MealViewModel.class);

        if (favorite) {
            meal = mealViewModel.getAllFavoriteMeals().getValue().get(mealIndex);
        } else {
            meal = mealViewModel.getAllMeals().getValue().get(mealIndex);
        }


        TextView textView = findViewById(R.id.textView);
        textView.setText(meal.getStrMeal());

        ImageView imageView = findViewById(R.id.iv_icon);
        Glide.with(this)
                .load(meal.getStrMealThumb())
                .into(imageView);

        TextView description = findViewById(R.id.description);
        description.setText(meal.getStrInstructions());
        description.setMovementMethod(new ScrollingMovementMethod());

        final ImageView heartView = findViewById(R.id.heart);
        heartView.setImageResource(R.drawable.ic_favorite_border);
        heartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addToFavorite(view);
                heartView.setImageResource(R.drawable.ic_favorite_full);

            }
        });
    }

        public void addToFavorite(View v)
        {
            TextView textView = findViewById(R.id.textView);
            textView.setText(meal.getStrMeal());
            ImageView imageView = findViewById(R.id.iv_icon);
            Glide.with(this)
                    .load(meal.getStrMealThumb())
                    .into(imageView);

            TextView description = findViewById(R.id.description);
            description.setText(meal.getStrInstructions());
            description.setMovementMethod(new ScrollingMovementMethod());

            Meal meal = new Meal();
            meal.setStrMeal(textView.getText().toString());
            meal.setStrMealThumb(imageView.toString());
            meal.setStrInstructions(description.getText().toString());

            mealViewModel.insert(meal);
        }

    }

