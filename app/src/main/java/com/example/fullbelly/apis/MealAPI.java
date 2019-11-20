package com.example.fullbelly.apis;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealAPI {

    @GET("api/json/v1/1/search.php")
    Call<MealResponse> getAllMeals(@Query("s") String query);
}
