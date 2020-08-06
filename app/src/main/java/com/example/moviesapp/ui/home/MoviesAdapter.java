package com.example.moviesapp.ui.home;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.App;
import com.example.moviesapp.R;
import com.example.moviesapp.models.Movie;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {
    List<Movie> data;

    public MoviesAdapter(List<Movie> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MoviesViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        holder.onBind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class MoviesViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private RoundedImageView roundedImageView;
        private TextView imdbRating;
        private Button buttonFavorite;
        private Button buttonFavoriteBorder;
        Movie movie;

        public MoviesViewHolder(@NonNull final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            roundedImageView = itemView.findViewById(R.id.roundedimageview);
            imdbRating = itemView.findViewById(R.id.imdbRating);
            buttonFavorite = itemView.findViewById(R.id.button_favorite);
            buttonFavoriteBorder = itemView.findViewById(R.id.button_favorite_border);
            setListener();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View view = LayoutInflater.from(v.getContext()).inflate(R.layout.layout_bottom_sheet, null);
                    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(itemView.getContext());
                    bottomSheetDialog.setContentView(view);
                    bottomSheetDialog.show();
                }
            });
        }

        @SuppressLint("SetTextI18n")
        void onBind(Movie movie){
            this.movie = movie;
            title.setText(movie.getTitle());
            roundedImageView.setImageResource(movie.getImage());
            imdbRating.setText(movie.getImdbRating().toString());
            if (movie.isLike()){
                Log.e("ololo", "onBind");
                buttonFavorite.setVisibility(View.VISIBLE);
                buttonFavoriteBorder.setVisibility(View.GONE);
            }
        }
        void setListener(){
            buttonFavoriteBorder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    like();
                }
            });

            buttonFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    aLike();
                }
            });
        }

        private void like(){
            buttonFavorite.setVisibility(View.VISIBLE);
            buttonFavoriteBorder.setVisibility(View.GONE);
            movie.setLike(true);
            App.getInstance().getDatabase().taskDao().insert(movie);
        }

        private void aLike(){
            buttonFavorite.setVisibility(View.GONE);
            buttonFavoriteBorder.setVisibility(View.VISIBLE);
            movie.setLike(false);
            App.getInstance().getDatabase().taskDao().delete(movie);
        }
    }
}