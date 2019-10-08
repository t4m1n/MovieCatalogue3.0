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
import com.fiqartamin.moviecatalogue4.Model.Tv;
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

public class TvDetActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    private TextView tvTitle, tvRelease, tvDesc, tvCategory, tvAverage;
    private ImageView imgPhoto, imgThumb;
    private ProgressBar progressBar;

    private FavoriteHelper favoriteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_det);

        Tv selectedTv = getIntent().getParcelableExtra(EXTRA_MOVIE);
        favoriteHelper = FavoriteHelper.getInstance(getApplicationContext());
        progressBar = findViewById(R.id.progressBar);

        showLoading(true);

        if (selectedTv != null) {
            tvTitle = findViewById(R.id.movie_det_title);
            tvTitle.setText(selectedTv.getTitle());

            tvRelease = findViewById(R.id.movie_det_release);
            tvRelease.setText(selectedTv.getRelease());

            tvDesc = findViewById(R.id.movie_det_desc);
            tvDesc.setText(selectedTv.getOverview());

            tvCategory = findViewById(R.id.movie_det_cat);
            tvCategory.setText(R.string.tv_show);

            tvAverage = findViewById(R.id.movie_det_average);
            tvAverage.setText(selectedTv.getAverage());

            imgPhoto = findViewById(R.id.movie_det_img);
            String photo1 = "https://image.tmdb.org/t/p/w500" + selectedTv.getPhoto();
            Glide.with(this)
                    .load(photo1)
                    .into(imgPhoto);

            imgThumb = findViewById(R.id.movie_det_thumb);
            String photo2 = "https://image.tmdb.org/t/p/w500" + selectedTv.getBackdrop();
            Glide.with(this)
                    .load(photo2)
                    .into(imgThumb);

            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(selectedTv.getTitle());
            }

            ContentValues values = new ContentValues();
            values.put(COLUMN_MOVIEID, selectedTv.getId());
            values.put(COLUMN_TITLE, selectedTv.getTitle());
            values.put(COLUMN_RELEASE, selectedTv.getRelease());
            values.put(COLUMN_PLOT_SYNOPSIS, selectedTv.getOverview());
            values.put(COLUMN_USERRATING, selectedTv.getAverage());
            values.put(COLUMN_POSTER_PATH, selectedTv.getPhoto());
            values.put(COLUMN_BACKDROP_PATH, selectedTv.getBackdrop());
            values.put(COLUMN_CATEGORY, "2"); // 1 utk movie, 2 utk TV

            MaterialFavoriteButton materialFavoriteButton = findViewById(R.id.favorite_button);

            Cursor result = favoriteHelper.queryById(selectedTv.getId());
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
                                favoriteHelper.deleteById(selectedTv.getId());
                                Snackbar.make(buttonView, "Removed from Favorite",
                                        Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    }
            );

            showLoading(false);
        }
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
