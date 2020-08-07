package com.example.moviesapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.makeramen.roundedimageview.RoundedImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress_bar);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.toolbar_search_menu, menu);
        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                return true;
            }
        };
        menu.findItem(R.id.action_search).setOnActionExpandListener(onActionExpandListener);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint("Поиск");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressBar.setVisibility(View.VISIBLE);
                String url = "https://www.omdbapi.com/?t=" + query + "&apikey=e3dc806f";
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                @SuppressLint("InflateParams") View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_bottom_sheet, null);
                                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
                                bottomSheetDialog.setContentView(view);
                                RoundedImageView roundedImageView = view.findViewById(R.id.roundedimageview);
                                TextView plot = view.findViewById(R.id.bottom_shit_plot_movie);
                                TextView title = view.findViewById(R.id.bottom_shit_name_movie);
                                TextView imdbRating = view.findViewById(R.id.bottom_sheet_imdbRating_movie);
                                TextView year = view.findViewById(R.id.bottom_shit_year_movie);
                                TextView rated = view.findViewById(R.id.bottom_shit_rated_movie);
                                TextView released = view.findViewById(R.id.bottom_shit_released_movie);
                                TextView runtime = view.findViewById(R.id.bottom_shit_runtime_movie);
                                TextView awards = view.findViewById(R.id.bottom_shit_awards_movie);

                                try {
                                    JSONObject movie = new JSONObject(response);
                                    if (!(movie.getBoolean("Response"))){
                                        Toast.makeText(MainActivity.this, "данный фильм не найден", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                        return;
                                    }
                                    progressBar.setVisibility(View.GONE);
                                    title.setText(movie.getString("Title"));
                                    Glide
                                            .with(MainActivity.this)
                                            .load(movie.getString("Poster"))
                                            .into(roundedImageView);
                                    plot.setText(movie.getString("Plot"));
                                    imdbRating.setText(movie.getString("imdbRating"));
                                    year.setText(movie.getString("Year"));
                                    rated.setText(movie.getString("Rated"));
                                    released.setText(movie.getString("Released"));
                                    runtime.setText(movie.getString("Runtime"));
                                    awards.setText(movie.getString("Awards"));
                                    
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                bottomSheetDialog.show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressBar.setVisibility(View.GONE);
                                Log.e("ololo", Objects.requireNonNull(error.getMessage()));
                            }
                        }
                );
                requestQueue.add(stringRequest);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        return true;
    }
}