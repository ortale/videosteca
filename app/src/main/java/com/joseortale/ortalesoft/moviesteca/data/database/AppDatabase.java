package com.joseortale.ortalesoft.moviesteca.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.joseortale.ortalesoft.moviesteca.model.Movie;

public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieDao submissionDao();
}
