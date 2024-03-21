package com.example.movieapp.activities.data

import com.google.gson.annotations.SerializedName

class MovieResponse(

    @SerializedName("Search") val movies: List<Movies>
) {
}

class Movies(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("imdbID") val id: String,
    @SerializedName("Poster") val poster: String,
    @SerializedName("Director") val director: String,
    @SerializedName("Runtime") val runtime: String,
    @SerializedName("Genre") val genre: String,
    @SerializedName("Country") val country: String,
    @SerializedName("Plot") val plot: String,


    ) {

}

class Poster(
    @SerializedName("url") val url: String
) {

}