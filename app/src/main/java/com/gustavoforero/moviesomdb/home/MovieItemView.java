package com.gustavoforero.moviesomdb.home;

interface MovieItemView {

    void setTitle(String title);

    void setType(String type);

    void setYear(String year);

    void loadPoster(String url);
}
