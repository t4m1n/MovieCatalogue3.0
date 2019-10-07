package com.fiqartamin.moviecatalogue3;

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
import com.fiqartamin.moviecatalogue3.Model.Movie;
import com.fiqartamin.moviecatalogue3.data.FavoriteHelper;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;

import static com.fiqartamin.moviecatalogue3.data.FavoriteContract.FavoriteEntry.COLUMN_MOVIEID;
import static com.fiqartamin.moviecatalogue3.data.FavoriteContract.FavoriteEntry.COLUMN_PLOT_SYNOPSIS;
import static com.fiqartamin.moviecatalogue3.data.FavoriteContract.FavoriteEntry.COLUMN_POSTER_PATH;
import static com.fiqartamin.moviecatalogue3.data.FavoriteContract.FavoriteEntry.COLUMN_RELEASE;
import static com.fiqartamin.moviecatalogue3.data.FavoriteContract.FavoriteEntry.COLUMN_TITLE;
import static com.fiqartamin.moviecatalogue3.data.FavoriteContract.FavoriteEntry.COLUMN_USERRATING;

public class MovieDetActivity extends AppCompatActivity {
    private TextView tvTitle, tvRelease, tvDesc, tvCategory, tvAverage;
    private ImageView imgPhoto, imgThumb;
    private ProgressBar progressBar;

    private FavoriteHelper favoriteHelper;

    public static final String EXTRA_MOVIE = "extra_movie";
    public static final int REQUEST_ADD = 100;
    public static final int RESULT_ADD = 101;
    public static final int REQUEST_UPDATE = 200;
    public static final int RESULT_UPDATE = 201;
    public static final int RESULT_DELETE = 301;
    private final int ALERT_DIALOG_CLOSE = 10;
    private final int ALERT_DIALOG_DELETE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_det);

        Movie selectedMovie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        favoriteHelper = FavoriteHelper.getInstance(getApplicationContext());
        progressBar = findViewById(R.id.progressBar);

        showLoading(true);

        if (selectedMovie != null) {
            tvTitle = findViewById(R.id.movie_det_title);
            tvTitle.setText(selectedMovie.getTitle());

            tvRelease = findViewById(R.id.movie_det_release);
            tvRelease.setText(selectedMovie.getRelease());

            tvDesc = findViewById(R.id.movie_det_desc);
            tvDesc.setText(selectedMovie.getOverview());

            tvCategory = findViewById(R.id.movie_det_cat);
            tvCategory.setText(R.string.tv_show);

            tvAverage = findViewById(R.id.movie_det_average);
            tvAverage.setText(Double.toString(selectedMovie.getAverage()));

            imgPhoto = findViewById(R.id.movie_det_img);
            String photo1 = "https://image.tmdb.org/t/p/w500" + selectedMovie.getPhoto();
            Glide.with(this)
                    .load(photo1)
                    .into(imgPhoto);

            imgThumb = findViewById(R.id.movie_det_thumb);
            String photo2 = "https://image.tmdb.org/t/p/w500" + selectedMovie.getBackdrop();
            Glide.with(this)
                    .load(photo2)
                    .into(imgThumb);

            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(selectedMovie.getTitle());
            }

            ContentValues values = new ContentValues();
            values.put(COLUMN_MOVIEID, selectedMovie.getId());
            values.put(COLUMN_TITLE, selectedMovie.getTitle());
            values.put(COLUMN_RELEASE, selectedMovie.getRelease());
            values.put(COLUMN_PLOT_SYNOPSIS, selectedMovie.getOverview());
            values.put(COLUMN_USERRATING, selectedMovie.getAverage());
            values.put(COLUMN_POSTER_PATH, selectedMovie.getPhoto());

            MaterialFavoriteButton materialFavoriteButton = findViewById(R.id.favorite_button);

            Cursor result = favoriteHelper.queryById(selectedMovie.getId());
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
                                favoriteHelper.deleteById(selectedMovie.getId());
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
