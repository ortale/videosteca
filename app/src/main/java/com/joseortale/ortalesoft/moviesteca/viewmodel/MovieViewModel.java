package com.joseortale.ortalesoft.moviesteca.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.joseortale.ortalesoft.moviesteca.data.api.MoviesRepository;
import com.joseortale.ortalesoft.moviesteca.model.Movie;
import com.joseortale.ortalesoft.moviesteca.model.apiresponse.ApiResponse;
import com.joseortale.ortalesoft.moviesteca.model.apiresponse.CollectionResponse;

import java.util.List;

public class MovieViewModel extends ViewModel {
    private MutableLiveData<List<Movie>> mutableList;
    private MutableLiveData<Movie> mutableObject;
    private MutableLiveData<CollectionResponse> mutableObjectCollection;
    private MoviesRepository moviesRepository;

    public void init(Context context) {
        moviesRepository = MoviesRepository.getInstance(context);
    }

    public void getAllMovies(Context context) {
        moviesRepository = MoviesRepository.getInstance(context);
        mutableList = moviesRepository.getAllMovies();
    }

    public void getMovieById(Context context, Integer id) {
        moviesRepository = MoviesRepository.getInstance(context);
        mutableObject = moviesRepository.getMovieById(id);
    }

    public void getAllCollectionsById(Context context, Integer id) {
        moviesRepository = MoviesRepository.getInstance(context);
        mutableObjectCollection = moviesRepository.getCollectionById(id);
    }

    public LiveData<List<Movie>> getMoviesRepository() {
        return mutableList;
    }

    public LiveData<Movie> getMovieRepository() {
        return mutableObject;
    }
}
