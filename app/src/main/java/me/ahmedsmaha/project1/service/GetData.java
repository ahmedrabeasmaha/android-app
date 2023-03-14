package me.ahmedsmaha.project1.service;

import java.util.List;

import me.ahmedsmaha.project1.Model.RetroNews;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetData {
    @GET("everything")
    Call<RetroNews> getAllNews(@Query("q") String search, @Query("from") String date, @Query("sortBy") String sort, @Query("apiKey") String key);
}
