package com.fiqartamin.moviecatalogue3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fiqartamin.moviecatalogue3.Model.Tv;

public class TvDetActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    TextView tvTitle, tvRelease, tvDesc, tvCategory, tvAverage;
    ImageView imgPhoto, imgThumb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_det);

        Tv selectedTv = getIntent().getParcelableExtra(EXTRA_MOVIE);

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
        }
    }
}
