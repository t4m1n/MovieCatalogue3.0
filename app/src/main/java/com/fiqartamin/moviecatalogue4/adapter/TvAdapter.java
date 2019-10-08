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
import com.fiqartamin.moviecatalogue4.Model.Tv;
import com.fiqartamin.moviecatalogue4.R;
import com.fiqartamin.moviecatalogue4.TvDetActivity;

import java.util.ArrayList;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvViewHolder> {
    ArrayList<Tv> tvs;

    public TvAdapter (ArrayList<Tv> tvs) {
        this.tvs = tvs;
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);

        final TvViewHolder vHolder = new TvViewHolder(view);

        vHolder.clMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(vHolder.itemView.getContext(), tvs.get(vHolder.getAdapterPosition()).getTitle(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(vHolder.itemView.getContext(), TvDetActivity.class);
                intent.putExtra(TvDetActivity.EXTRA_MOVIE, tvs.get(vHolder.getAdapterPosition()));
                vHolder.itemView.getContext().startActivities(new Intent[]{intent});
            }
        });
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TvViewHolder holder, int i) {
        holder.tvTitle.setText(tvs.get(i).getTitle());
        holder.tvDesc.setText(tvs.get(i).getOverview());
        holder.tvRelease.setText(tvs.get(i).getRelease());

        String photo1 = "https://image.tmdb.org/t/p/w500" + tvs.get(i).getPhoto();
        Glide.with(holder.itemView.getContext())
                .load(photo1)
                .into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return tvs.size();
    }

    public class TvViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout clMovie;
        private TextView tvTitle, tvDesc, tvRelease;
        private ImageView imgPhoto;

        public TvViewHolder(@NonNull View itemView) {
            super(itemView);

            clMovie = itemView.findViewById(R.id.movie_const);
            tvTitle = itemView.findViewById(R.id.movie_title);
            tvDesc = itemView.findViewById(R.id.movie_desc);
            tvRelease = itemView.findViewById(R.id.movie_release);
            imgPhoto = itemView.findViewById(R.id.movie_img);
        }
    }
}
