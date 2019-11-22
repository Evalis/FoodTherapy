package com.example.foodTherapy.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.foodTherapy.view.AddNewRecipe;
import com.example.foodTherapy.view.MainActivity;
import com.example.foodTherapy.view.MealDetail;
import com.example.foodTherapy.R;
import com.example.foodTherapy.view.RecipeAdapter;
import com.example.foodTherapy.model.Meal;
import com.example.foodTherapy.viewModel.MealViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
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

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mealViewModel.delete(mRecipeAdapter.getMealAt(viewHolder.getAdapterPosition()));
                Toast.makeText(c,"Recipe deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(mRecipeList);

        FloatingActionButton fab = rootView.findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddNewRecipe.class);
                startActivity(intent);


            }
        });

        mealViewModel.getAllFavoriteMeals().observe(this.getActivity(), new Observer<List<Meal>>() {
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
        intent.putExtra("favorite", true);
        startActivity(intent);


    }
}
