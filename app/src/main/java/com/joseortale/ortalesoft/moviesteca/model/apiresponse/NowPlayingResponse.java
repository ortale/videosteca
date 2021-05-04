package com.joseortale.ortalesoft.moviesteca.model.apiresponse;

import com.google.gson.annotations.SerializedName;
import com.joseortale.ortalesoft.moviesteca.model.Movie;

import java.util.List;

public class NowPlayingResponse {
    @SerializedName("page")
    private Integer page;

    @SerializedName("results")
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
