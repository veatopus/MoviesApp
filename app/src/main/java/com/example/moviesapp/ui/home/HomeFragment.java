package com.example.moviesapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.moviesapp.R;
import com.example.moviesapp.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        List<Movie> list = new ArrayList<>();

        addDataList(list, 0);
        addDataList(list, 1);
        addDataList(list, 0);
        addDataList(list, 1);
        addDataList(list, 0);
        addDataList(list, 1);
        addDataList(list, 0);
        addDataList(list, 1);

        recyclerView.setAdapter(new MoviesAdapter(list));
    }

    private void addDataList(List<Movie> list, int i) {
        Movie movie = new Movie(R.drawable.image, "Movie", 7.9);
        Movie movie1 = new Movie(R.drawable.image_1, "Movie_1", 5.1);
        Movie movie2 = new Movie(R.drawable.image_2, "Movie_2", 8.9);
        Movie movie3 = new Movie(R.drawable.image_3, "Movie_3", 6.0);
        Movie movie4 = new Movie(R.drawable.image_4, "Movie_4", 3.8);
        Movie movie5 = new Movie(R.drawable.image_5, "Movie_5", 9.3);
        Movie movie6 = new Movie(R.drawable.image_6, "Movie_6", 5.6);

        list.add(movie3);
        list.add(movie);
        list.add(movie5);
        list.add(movie1);
        list.add(movie2);
        list.add(movie4);
        list.add(movie6);

        if (i > 0) {
            list.add(movie1);
            list.add(movie2);
        }
    }
}