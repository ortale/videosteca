package com.joseortale.ortalesoft.moviesteca.data.api;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.joseortale.ortalesoft.moviesteca.R;
import com.joseortale.ortalesoft.moviesteca.data.database.AppDatabase;
import com.joseortale.ortalesoft.moviesteca.data.database.MovieDao;
import com.joseortale.ortalesoft.moviesteca.model.Movie;
import com.joseortale.ortalesoft.moviesteca.model.apiresponse.ApiResponse;
import com.joseortale.ortalesoft.moviesteca.model.apiresponse.CollectionResponse;
import com.joseortale.ortalesoft.moviesteca.model.apiresponse.DetailsResponse;
import com.joseortale.ortalesoft.moviesteca.model.apiresponse.NowPlayingResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository {
    private static MoviesRepository instance;

    private final ApiEndpoints apiEndpoints;
    private AppDatabase database;
    private MovieDao movieDao;
    private List<Movie> movies;
    private Movie movie;

    private Context context;

    private MutableLiveData<List<Movie>> moviesData;
    private MutableLiveData<Movie> movieData;
    private MutableLiveData<CollectionResponse> collectionData;

    private MoviesRepository(Context context) {
        apiEndpoints = RetrofitClient.getClient();
        database = Room.databaseBuilder(context, AppDatabase.class, "movies").allowMainThreadQueries().build();
        movieDao = database.submissionDao();

        this.context = context;
    }

    public static MoviesRepository getInstance(Context context) {
        if (instance == null) {
            instance = new MoviesRepository(context);
        }

        return instance;
    }

    public MutableLiveData<List<Movie>> getAllMovies() {
        MutableLiveData<List<Movie>> moviesData = new MutableLiveData<>();

        apiEndpoints.getAll(context.getResources().getString(R.string.api_key)).enqueue(new Callback<NowPlayingResponse>() {
            @Override
            public void onResponse(Call<NowPlayingResponse> call, Response<NowPlayingResponse> response) {
                if (response.isSuccessful()) {
                    NowPlayingResponse apiResponse = response.body();

                    if (apiResponse != null) {
                        movies = apiResponse.getMovies();

                        moviesData.setValue(movies);
                    }

                    else {
                        Log.v("SubmissionsRepository", "Error status " + response.code());
                    }
                }

                else {
                    movies = movieDao.getAll();
                    moviesData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<NowPlayingResponse> call, Throwable t) {
                movies = movieDao.getAll();
                moviesData.setValue(movies);
            }
        });

        return moviesData;
    }

    public MutableLiveData<Movie> getMovieById(Integer id) {
        movie = new Movie();

        movieData = new MutableLiveData<>();

        apiEndpoints.getMovieById(id, context.getResources().getString(R.string.api_key)).enqueue(new Callback<DetailsResponse>() {
            @Override
            public void onResponse(Call<DetailsResponse> call, Response<DetailsResponse> response) {
                if (response.isSuccessful()) {
                    DetailsResponse apiResponse = response.body();

                    if (apiResponse != null) {
                        String homepage = apiResponse.getHomepage();
                        Object belongsToCollection = apiResponse.getBelongsToCollection();

                        movie.setHomepage(homepage);
                        movie.setId(id);
                        movie.setBelongsToCollection(belongsToCollection != null ? belongsToCollection.toString() : "");

                        movieData.setValue(movie);
                    }

                    else {
                        Log.v("SubmissionsRepository", "Error status " + response.code());
                    }
                }

                else {
                    Log.v("SubmissionsRepository", "Error occurred");
                }
            }

            @Override
            public void onFailure(Call<DetailsResponse> call, Throwable t) {
                Log.v("SubmissionsRepository", "Error occurred");
            }
        });

        return movieData;
    }

    public MutableLiveData<CollectionResponse> getCollectionById(Integer id) {
        collectionData = new MutableLiveData<>();

        apiEndpoints.getCollectionById(id, context.getResources().getString(R.string.api_key)).enqueue(new Callback<CollectionResponse>() {
            @Override
            public void onResponse(Call<CollectionResponse> call, Response<CollectionResponse> response) {
                if (response.isSuccessful()) {
                    CollectionResponse apiResponse = response.body();

                    if (apiResponse != null) {
                        collectionData.setValue(apiResponse);
                    }

                    else {
                        Log.v("SubmissionsRepository", "Error status " + response.code());
                    }
                }

                else {
                    Log.v("SubmissionsRepository", "Error occurred");
                }
            }

            @Override
            public void onFailure(Call<CollectionResponse> call, Throwable t) {
                Log.v("SubmissionsRepository", "Error occurred");
            }
        });

        return collectionData;
    }
}
