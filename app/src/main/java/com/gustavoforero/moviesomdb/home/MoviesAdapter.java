package com.gustavoforero.moviesomdb.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gustavoforero.moviesomdb.R;
import com.gustavoforero.moviesomdb.domain.Movie;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private MoviesPresenter mPresenter;

    MoviesAdapter() {
        this.mPresenter = new MoviesPresenter();
    }

    void updateItemList(ArrayList<Movie> list) {
        mPresenter.updateMoviesList(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MovieViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_movie, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        mPresenter.onBindMovieViewAtPosition(i, movieViewHolder);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getRepositoriesRowsCount();
    }
}
