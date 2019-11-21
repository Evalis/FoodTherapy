package com.example.fullbelly.view;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fullbelly.R;
import com.example.fullbelly.model.Meal;
import com.example.fullbelly.viewModel.MealViewModel;


import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProviders;

public class AddNewRecipe  extends AppCompatActivity {

    Button button;

    EditText editTitle;
    EditText editDescription;
    private MealViewModel mealViewModel;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewrecipe);


        mealViewModel = ViewModelProviders.of(this).get(MealViewModel.class);
        editTitle = findViewById(R.id.editTitle);
        editDescription = findViewById(R.id.editDescription);
        button = findViewById(R.id.saveButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewRecipe(view);
                goToMainActivity();

            }
        });

            }


    public void addNewRecipe(View v)
    {

        Meal meal = new Meal();
        meal.setStrMeal(editTitle.getText().toString());
        meal.setStrInstructions(editDescription.getText().toString());

        mealViewModel.insert(meal);
        Toast.makeText(this, "New Recipe was added to Favorites", Toast.LENGTH_LONG).show();
}

    private void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
