package com.nitin.movieapp.data.mappers

import com.nitin.movieapp.data.model.details.MovieDetailsDTO
import com.nitin.movieapp.data.model.list.MovieDTO
import com.nitin.movieapp.domain.model.details.MovieDetails
import com.nitin.movieapp.domain.model.list.Movie

fun List<MovieDTO>.toDomain() : List<Movie>{
    return map{
        Movie(
            id = it.id?:0,
            posterPath = it.poster_path?:"",
            title = it.title?:""
        )
    }
}

fun MovieDetailsDTO.toDomain() = MovieDetails(
    originalTitle = originalTitle?:"",
    overview = overview?:"",
    posterPath = poster_path?:"",
    tagline = tagline?:"",
    title = title?:""
)

