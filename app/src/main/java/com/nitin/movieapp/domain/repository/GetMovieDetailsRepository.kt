package com.nitin.movieapp.domain.repository

import com.nitin.movieapp.domain.model.details.MovieDetails

interface GetMovieDetailsRepository {

    suspend fun getMovieDetails(id:String) : MovieDetails

}