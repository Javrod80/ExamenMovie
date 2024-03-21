package com.example.movieapp.activities.data

import com.google.gson.annotations.SerializedName

class MovieResponse(

    @SerializedName("Search") val movies: List<Movies>
) {
}

class Movies(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Released") val released : String,
    @SerializedName("Runtime") val runtime: String,
    @SerializedName("Genre") val genre: String,
    @SerializedName("Director") val director: String,
    @SerializedName("Plot") val plot: String,
    @SerializedName("Country") val country: String,
    @SerializedName("Awards") val awards : String,
    @SerializedName("Poster") val poster: String,
    @SerializedName("imdbRating") val rating : String,
    @SerializedName("imdbVotes") val votes : String,
    @SerializedName("imdbID") val id: String,













    ) {

}

