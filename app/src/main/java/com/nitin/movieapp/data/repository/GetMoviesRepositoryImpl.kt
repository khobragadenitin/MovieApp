package com.nitin.movieapp.data.repository

import com.nitin.movieapp.BuildConfig
import com.nitin.movieapp.data.network.ApiService
import com.nitin.movieapp.domain.model.list.Movie
import com.nitin.movieapp.domain.repository.GetMovieRepository
import com.nitin.movieapp.data.mappers.toDomain
import javax.inject.Inject

class GetMoviesRepositoryImpl @Inject constructor(private val apiService: ApiService) : GetMovieRepository {

    override suspend fun getMovies(): List<Movie> {
        return apiService.getMovieList(BuildConfig.API_KEY).results!!.toDomain()
    }
}