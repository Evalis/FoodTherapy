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
import com.example.fullbelly.view.RecipeAdapter;
import com.example.fullbelly.model.Meal;
import com.example.fullbelly.viewModel.MealViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FavoriteFragment extends Fragment implements RecipeAdapter.OnListItemClickListener{

    RecyclerView mRecipeList;
    RecipeAdapter mRecipeAdapter;
    Context c;

    private MealViewModel mealViewModel;


    public FavoriteFragment(MainActivity mainActivity, MealViewModel mealViewModel) {
        c = mainActivity;
        this.mealViewModel = mealViewModel;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);
        mRecipeList = rootView.findViewById(R.id.rv);
        mRecipeList.hasFixedSize();
        mRecipeList.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        mRecipeAdapter = new RecipeAdapter(this);
        mRecipeList.setAdapter(mRecipeAdapter);


        mealViewModel.getAllFavoriteMeals().observe(this.getActivity(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                Log.i("Retrofit", "observer change triggered");
                mRecipeAdapter.setMeals(meals);
            }
        });

        return rootView;
    }

    public void onListItemClick(int clickedItemIndex) {
        int recipeNumber = clickedItemIndex + 1;


        Intent intent = new Intent(c, MealDetail.class);
        intent.putExtra("mealIndex", clickedItemIndex);
        intent.putExtra("favorite", true);
        startActivity(intent);
        Toast.makeText(c,"Recipe number " + recipeNumber,Toast.LENGTH_SHORT).show();

    }
}
