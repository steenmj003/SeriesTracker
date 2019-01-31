package com.hva.joris.seriestracker;


import com.hva.joris.seriestracker.models.SerieList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SerieApiService {
    String BASE_URL = "https://api.themoviedb.org/";

    /**
     * Create a retrofit client.
     */
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("/3/tv/popular")
    Call<SerieList> getSeries(@Query("api_key") String apiKey);

    @GET("/3/search/tv")
    Call<SerieList> searchSeries(@Query("api_key") String apiKey, @Query("query") String query);
}
