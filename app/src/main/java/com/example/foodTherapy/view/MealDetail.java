package com.example.foodTherapy.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.foodTherapy.R;
import com.example.foodTherapy.model.Meal;
import com.example.foodTherapy.viewModel.MealViewModel;



import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProviders;

public class MealDetail extends AppCompatActivity {

    private MealViewModel mealViewModel;
    Meal meal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                meal = null;
            } else {
                meal = (Meal)extras.getSerializable("Meal");
            }
        } else {
            meal = (Meal) savedInstanceState.getSerializable("mealIndex");
        }

        mealViewModel = ViewModelProviders.of(this).get(MealViewModel.class);


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
                goToMainActivity();

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
            meal.setStrInstructions(description.getText().toString());

            mealViewModel.insert(meal);
            Toast.makeText(this, "Recipe added to Favorites", Toast.LENGTH_LONG).show();
        }

    private void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    }

