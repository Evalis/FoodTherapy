package com.example.fullbelly.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fullbelly.R;
import com.example.fullbelly.model.Meal;
import com.example.fullbelly.view.RecipeAdapter;
import com.example.fullbelly.viewModel.MealViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class NewRecipeFragment extends Fragment {

    Button button;

    EditText editTitle;
    EditText editDescription;
    private MealViewModel mealViewModel;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragmnet_newrecipe, container,false);

        mealViewModel = ViewModelProviders.of(this).get(MealViewModel.class);
        editTitle = v.findViewById(R.id.editTitle);
        editDescription = v.findViewById(R.id.editDescription);
        button = v.findViewById(R.id.saveButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewRecipe(view);
            }
        });

        return v;
            }


    public void addNewRecipe(View v)
    {

        Meal meal = new Meal();
        meal.setStrMeal(editTitle.getText().toString());
        meal.setStrInstructions(editDescription.getText().toString());

        mealViewModel.insert(meal);
}
}
