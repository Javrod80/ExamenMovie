package com.example.movieapp.activities.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieServiceApi {


    @GET("?apikey=b9856625")
    suspend fun searchMovies (@Query("s") identifier: String) : Response<MovieResponse>


    @GET("?apikey=b9856625")
    suspend fun moviesId (@Query("i") identifier: String) : Response<Movies>










}