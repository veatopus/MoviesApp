package com.example.moviesapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "movie")
public class Movie implements Serializable {



    private String posterUrl;
    private String title;
    private Double imdbRating;
    private int image;
    private boolean like;
    @PrimaryKey(autoGenerate = true)
    private long id;

    public Movie(int image, String title, Double imdbRating) {
        this.image = image;
        this.title = title;
        this.imdbRating = imdbRating;
    }

    public Movie() {
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
