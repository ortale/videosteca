package com.joseortale.ortalesoft.moviesteca.data.api;

import com.joseortale.ortalesoft.moviesteca.model.apiresponse.ApiResponse;
import com.joseortale.ortalesoft.moviesteca.model.apiresponse.CollectionResponse;
import com.joseortale.ortalesoft.moviesteca.model.apiresponse.DetailsResponse;
import com.joseortale.ortalesoft.moviesteca.model.apiresponse.NowPlayingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndpoints {
    @GET("movie/now_playing")
    Call<NowPlayingResponse> getAll(@Query("api_key") String apiKey);

    @GET("movie")
    Call<DetailsResponse> getMovieById(@Query("movie_id") Integer id, @Query("api_key") String apiKey);

    @GET("collection")
    Call<CollectionResponse> getCollectionById(@Query("collection_id") Integer id, @Query("api_key") String apiKey);
}
