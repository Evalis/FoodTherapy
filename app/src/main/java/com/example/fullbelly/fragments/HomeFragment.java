package com.example.fullbelly.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.fullbelly.view.MainActivity;
import com.example.fullbelly.view.MealDetail;
import com.example.fullbelly.R;
import com.example.fullbelly.view.AddNewRecipe;
import com.example.fullbelly.view.RecipeAdapter;
import com.example.fullbelly.model.Meal;
import com.example.fullbelly.viewModel.MealViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



public class HomeFragment extends Fragment implements RecipeAdapter.OnListItemClickListener {
    RecyclerView mRecipeList;
    RecipeAdapter mRecipeAdapter;
    Context c;

    private MealViewModel mealViewModel;


    public HomeFragment(MainActivity mainActivity, MealViewModel mealViewModel) {
        c = mainActivity;
        this.mealViewModel = mealViewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        mRecipeList = rootView.findViewById(R.id.rv);
        mRecipeList.hasFixedSize();
        mRecipeList.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        mRecipeAdapter = new RecipeAdapter(this);
        mRecipeList.setAdapter(mRecipeAdapter);

        FloatingActionButton fab = rootView.findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddNewRecipe.class);
                startActivity(intent);
            }
        });
        mealViewModel.getAllMeals().observe(this.getActivity(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
               mRecipeAdapter.setMeals(meals);

            }
        });

        return rootView;
    }

    public void onListItemClick(Meal meal) {


        Intent intent = new Intent(c, MealDetail.class);
        intent.putExtra("Meal", meal);
        startActivity(intent);


    }

    }
