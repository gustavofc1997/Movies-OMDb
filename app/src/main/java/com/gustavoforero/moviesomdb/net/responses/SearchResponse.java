package com.gustavoforero.moviesomdb.net.responses;

import com.gustavoforero.moviesomdb.domain.Movie;

import java.util.ArrayList;

public class SearchResponse {
    private boolean Response;
    private String Error;
    private ArrayList<Movie> Search;

    public boolean isSuccess() {
        return Response;
    }

    public String getError() {
        return Error;
    }

    public ArrayList<Movie> getSearch() {
        return Search;
    }
}
