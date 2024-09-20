package com.nitin.movieapp.data.network

import com.nitin.movieapp.data.model.list.MovieListResponseDTO
import com.nitin.movieapp.data.model.details.MovieDetailsDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/popular")
    suspend fun getMovieList(
        @Query("api_key") apiKey:String
    ): MovieListResponseDTO

    @GET("3/movie/{id}")
    suspend fun getMovieDetails(@Path("id") id : String,
                                @Query("api_key") apiKey: String) : MovieDetailsDTO
}