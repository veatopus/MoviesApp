package com.example.moviesapp.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.moviesapp.models.Movie;

@Database(entities = {Movie.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}