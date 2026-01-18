package com.example.movieapp.data

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val results: List<Movie>
)

data class Movie(
    val id: Int,
    val title: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("overview") val description: String,
    @SerializedName("vote_average") val rating: Double
)