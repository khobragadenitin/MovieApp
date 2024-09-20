package com.nitin.movieapp.data.repository

import com.nitin.movieapp.BuildConfig
import com.nitin.movieapp.data.network.ApiService
import com.nitin.movieapp.domain.model.details.MovieDetails
import com.nitin.movieapp.domain.repository.GetMovieDetailsRepository
import com.nitin.movieapp.data.mappers.toDomain
import javax.inject.Inject

class GetMovieDetailsRepositoryImpl @Inject constructor(private val apiService : ApiService) : GetMovieDetailsRepository {

    override suspend fun getMovieDetails(id: String): MovieDetails {
        return apiService.getMovieDetails(id = id, apiKey = BuildConfig.API_KEY).toDomain()
    }
}