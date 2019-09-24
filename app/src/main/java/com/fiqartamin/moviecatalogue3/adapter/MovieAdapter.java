package com.fiqartamin.moviecatalogue3.adapter;


import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fiqartamin.moviecatalogue3.Model.Movie;
import com.fiqartamin.moviecatalogue3.R;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    ArrayList<Movie> movies;

    public MovieAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);

        final MovieViewHolder vHolder = new MovieViewHolder(view);

        vHolder.clMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(vHolder.itemView.getContext(), movies.get(vHolder.getAdapterPosition()).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int i) {
        holder.tvTitle.setText(movies.get(i).getTitle());
        holder.tvDesc.setText(movies.get(i).getOverview());
        holder.tvRelease.setText(movies.get(i).getRelease());

        String photo1 = "https://image.tmdb.org/t/p/w500" + movies.get(i).getPhoto();
        Glide.with(holder.itemView.getContext())
                .load(photo1)
                .into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout clMovie;
        private TextView tvTitle, tvDesc, tvRelease;
        private ImageView imgPhoto;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            clMovie = itemView.findViewById(R.id.movie_const);
            tvTitle = itemView.findViewById(R.id.movie_title);
            tvDesc = itemView.findViewById(R.id.movie_desc);
            tvRelease = itemView.findViewById(R.id.movie_release);
            imgPhoto = itemView.findViewById(R.id.movie_img);
        }
    }
}
