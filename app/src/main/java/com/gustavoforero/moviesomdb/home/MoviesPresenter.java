package com.gustavoforero.moviesomdb.home;

import com.gustavoforero.moviesomdb.domain.Movie;

import java.util.ArrayList;

class MoviesPresenter {

    private ArrayList<Movie> mMoviesList;

    MoviesPresenter() {
        mMoviesList = new ArrayList<>();

    }

    void updateMoviesList(ArrayList<Movie> list) {
        mMoviesList = list;

    }


    void onBindMovieViewAtPosition(int position, MovieItemView movieView) {
        Movie movie = mMoviesList.get(position);
        movieView.setYear(movie.getYear());
        movieView.loadPoster(movie.getPoster());
        movieView.setTitle(movie.getTitle());
        movieView.setType(movie.getType());
    }

    int getRepositoriesRowsCount() {
        return mMoviesList.size();
    }
}
