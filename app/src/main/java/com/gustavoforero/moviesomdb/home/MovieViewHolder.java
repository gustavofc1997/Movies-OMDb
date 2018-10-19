package com.gustavoforero.moviesomdb.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gustavoforero.moviesomdb.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieViewHolder extends RecyclerView.ViewHolder implements MovieItemView {


    @BindView(R.id.lab_movie_title)
    TextView mLabMovieTitle;
    @BindView(R.id.lab_movie_year)
    TextView mLabMovieYear;
    @BindView(R.id.lab_type)
    TextView mLabMovieType;
    @BindView(R.id.img_poster)
    ImageView mImgPoster;


    MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    @Override
    public void setTitle(String title) {
        mLabMovieTitle.setText(title);

    }

    @Override
    public void setType(String type) {
        mLabMovieType.setText(type);
    }

    @Override
    public void setYear(String year) {
        mLabMovieYear.setText(year);

    }

    @Override
    public void loadPoster(String url) {
        Picasso.get().load(url).into(mImgPoster);
    }
}
