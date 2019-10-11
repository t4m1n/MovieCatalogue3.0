package com.fiqartamin.moviecatalogue4.adapter;

import android.content.Intent;
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
import com.fiqartamin.moviecatalogue4.FavoriteMovieDetActivity;
import com.fiqartamin.moviecatalogue4.Model.Favorite;
import com.fiqartamin.moviecatalogue4.R;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {
    ArrayList<Favorite> listFavorites;

    public FavoriteAdapter(ArrayList<Favorite> listFavorites) {
        this.listFavorites = listFavorites;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie_favorite, viewGroup, false);

        final FavoriteViewHolder vHolder = new FavoriteViewHolder(view);

        vHolder.clMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(vHolder.itemView.getContext(), listFavorites.get(vHolder.getAdapterPosition()).getTitle(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(vHolder.itemView.getContext(), FavoriteMovieDetActivity.class);
                intent.putExtra(FavoriteMovieDetActivity.EXTRA_MOVIE, listFavorites.get(vHolder.getAdapterPosition()));
                vHolder.itemView.getContext().startActivities(new Intent[]{intent});
            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int i) {
        holder.tvTitle.setText(listFavorites.get(i).getTitle());
        holder.tvDesc.setText(listFavorites.get(i).getOverview());
        holder.tvRelease.setText(listFavorites.get(i).getRelease());

        String photo1 = "https://image.tmdb.org/t/p/w500" + listFavorites.get(i).getPosterPath();
        Glide.with(holder.itemView.getContext())
                .load(photo1)
                .into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return listFavorites.size();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout clMovie;
        private TextView tvTitle, tvDesc, tvRelease;
        private ImageView imgPhoto;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);

            clMovie = itemView.findViewById(R.id.movie_const);
            tvTitle = itemView.findViewById(R.id.movie_title);
            tvDesc = itemView.findViewById(R.id.movie_desc);
            tvRelease = itemView.findViewById(R.id.movie_release);
            imgPhoto = itemView.findViewById(R.id.movie_img);
        }
    }
}
