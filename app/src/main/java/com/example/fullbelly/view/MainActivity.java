package com.example.fullbelly.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.fullbelly.R;
import com.example.fullbelly.fragments.FavoriteFragment;
import com.example.fullbelly.fragments.NewRecipeFragment;
import com.example.fullbelly.fragments.HomeFragment;
import com.example.fullbelly.viewModel.MealViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private MealViewModel mealViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mealViewModel = ViewModelProviders.of(this).get(MealViewModel.class);
        mealViewModel.updateMeals("");

        Toolbar toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment(MainActivity.this, mealViewModel)).commit();

   }
   private BottomNavigationView.OnNavigationItemSelectedListener navListener =
           new BottomNavigationView.OnNavigationItemSelectedListener() {
               @Override
               public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                   Fragment selectedFragment = null;

                   switch (menuItem.getItemId())
                   {
                       case R.id.nav_favorite:
                           selectedFragment = new FavoriteFragment(MainActivity.this, mealViewModel);
                           break;
                       case R.id.nav_home:
                           selectedFragment = new HomeFragment(MainActivity.this, mealViewModel);
                           break;
                       case R.id.nav_newRecipe:
                           selectedFragment = new NewRecipeFragment();
                           break;
                   }
                   getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                   return true;
               }
           };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        android.widget.SearchView searchView = (android.widget.SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mealViewModel.updateMeals(s);
                return false;
            }
        });
        return true;
    }



}

