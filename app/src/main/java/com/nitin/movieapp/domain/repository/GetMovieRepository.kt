package com.nitin.movieapp.domain.repository

import com.nitin.movieapp.domain.model.list.Movie

interface GetMovieRepository {

    suspend fun getMovies() : List<Movie>
}