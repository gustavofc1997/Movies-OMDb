package com.gustavoforero.moviesomdb.net.responses

import com.gustavoforero.moviesomdb.domain.Movie

import java.util.ArrayList

class SearchResponse {
     var Response: Boolean = false
     var Error: String? = null
     var Search: ArrayList<Movie>? = null
}
