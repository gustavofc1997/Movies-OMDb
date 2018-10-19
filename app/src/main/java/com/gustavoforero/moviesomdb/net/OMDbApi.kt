package com.gustavoforero.moviesomdb.net

import com.gustavoforero.moviesomdb.net.responses.SearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface OMDbApi {

    companion object {

        val BASE_URL = "http://www.omdbapi.com"
        val API_KEY = "5eec5adc"

        fun getSearchUrl(search: String):String{
            return "$BASE_URL/?apikey=$API_KEY&s=$search"
        }
    }




    @GET
    fun searchMovieByTitle(@Url url: String): Observable<SearchResponse>
}
