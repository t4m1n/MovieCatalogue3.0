package com.fiqartamin.moviecatalogue4;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fiqartamin.moviecatalogue4.Model.Favorite;
import com.fiqartamin.moviecatalogue4.data.FavoriteHelper;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;

import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_BACKDROP_PATH;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_CATEGORY;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_MOVIEID;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_PLOT_SYNOPSIS;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_POSTER_PATH;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_RELEASE;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_TITLE;
import static com.fiqartamin.moviecatalogue4.data.FavoriteContract.FavoriteEntry.COLUMN_USERRATING;

public class FavoriteMovieDetActivity extends AppCompatActivity {
    private TextView tvTitle, tvRelease, tvDesc, tvCategory, tvAverage;
    private ImageView imgPhoto, imgThumb;
    private ProgressBar progressBar;

    private FavoriteHelper favoriteHelper;

    public static final String EXTRA_MOVIE = "extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movie_det);

        Favorite selectedFavorite = getIntent().getParcelableExtra(EXTRA_MOVIE);
        favoriteHelper = FavoriteHelper.getInstance(getApplicationContext());
        progressBar = findViewById(R.id.progressBar);

        if (selectedFavorite != null) {
            tvTitle = findViewById(R.id.movie_det_title);
            tvTitle.setText(selectedFavorite.getTitle());

            tvRelease = findViewById(R.id.movie_det_release);
            tvRelease.setText(selectedFavorite.getRelease());

            tvDesc = findViewById(R.id.movie_det_desc);
            tvDesc.setText(selectedFavorite.getOverview());

            tvCategory = findViewById(R.id.movie_det_cat);
            tvCategory.setText(R.string.movie);

            tvAverage = findViewById(R.id.movie_det_average);
            tvAverage.setText(selectedFavorite.getUserRating());

            imgPhoto = findViewById(R.id.movie_det_img);
            String photo1 = "https://image.tmdb.org/t/p/w500" + selectedFavorite.getPosterPath();
            Glide.with(this)
                    .load(photo1)
                    .into(imgPhoto);

            imgThumb = findViewById(R.id.movie_det_thumb);
            String photo2 = "https://image.tmdb.org/t/p/w500" + selectedFavorite.getBackdropPath();
            Glide.with(this)
                    .load(photo2)
                    .into(imgThumb);

            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(selectedFavorite.getTitle());
            }

            ContentValues values = new ContentValues();
            values.put(COLUMN_MOVIEID, selectedFavorite.getId());
            values.put(COLUMN_TITLE, selectedFavorite.getTitle());
            values.put(COLUMN_RELEASE, selectedFavorite.getRelease());
            values.put(COLUMN_PLOT_SYNOPSIS, selectedFavorite.getOverview());
            values.put(COLUMN_USERRATING, selectedFavorite.getUserRating());
            values.put(COLUMN_POSTER_PATH, selectedFavorite.getPosterPath());
            values.put(COLUMN_BACKDROP_PATH, selectedFavorite.getBackdropPath());
            values.put(COLUMN_CATEGORY, "1"); // 1 utk movie, 2 utk TV

            MaterialFavoriteButton materialFavoriteButton = findViewById(R.id.favorite_button);

            Cursor result = favoriteHelper.queryById(String.valueOf(selectedFavorite.getMovieId()));
            if (result.getCount() > 0) {
                materialFavoriteButton.setFavorite(true);
            } else {
                materialFavoriteButton.setFavorite(false);
            }

            materialFavoriteButton.setOnFavoriteChangeListener(
                    new MaterialFavoriteButton.OnFavoriteChangeListener() {
                        @Override
                        public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                            if (favorite) {
                                favoriteHelper.insert(values);
                                Snackbar.make(buttonView, "Added to Favorite",
                                        Snackbar.LENGTH_SHORT).show();
                            } else {
                                favoriteHelper.deleteById(String.valueOf(selectedFavorite.getMovieId()));
                                Snackbar.make(buttonView, "Removed from Favorite",
                                        Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    }
            );

            showLoading(false);
        }
    }

    private void showLoading (Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
