package com.example.moviesapp.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moviesapp.models.Movie;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM Movie")
    List<Movie> getAll();


    @Query("SELECT * FROM Movie")
    LiveData<List<Movie>> getAllLive();

    @Insert
    void insert(Movie taskModel);

    @Update
    void update(Movie taskModel);

    @Delete
    void delete(Movie taskModel);

    @Query("DELETE FROM Movie")
    void nukeTable();
}