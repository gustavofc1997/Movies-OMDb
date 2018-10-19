package com.gustavoforero.moviesomdb.net;

import com.gustavoforero.moviesomdb.net.responses.SearchResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface OMDbApi {


    String URL_BASE = "http://www.omdbapi.com/";
    String API_KEY = "5eec5adc";


    static String getSearchUrl(String search) {
        return URL_BASE + "?apikey=" + API_KEY + "&s=" + search;
    }

    @GET
    Observable<SearchResponse> searchMoviesByTitle(@Url String url);


}
