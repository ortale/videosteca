package com.joseortale.ortalesoft.moviesteca.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.joseortale.ortalesoft.moviesteca.model.Movie;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM Movie")
    List<Movie> getAll();

    @Query("SELECT * FROM Movie WHERE id IN (:id)")
    List<Movie> loadAllById(int id);

    @Insert
    void insertAll(Movie... movies);

    @Update
    void update(Movie movie);
}
