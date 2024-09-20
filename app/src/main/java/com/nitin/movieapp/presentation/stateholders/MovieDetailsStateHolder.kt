package com.nitin.movieapp.presentation.stateholders

import com.nitin.movieapp.domain.model.details.MovieDetails


data class MovieDetailsStateHolder(
    val isLoading:Boolean = false,
    val data : MovieDetails? = null,
    val error:String = ""
)
