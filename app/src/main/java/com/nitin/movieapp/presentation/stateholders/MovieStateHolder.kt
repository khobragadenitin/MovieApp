package com.nitin.movieapp.presentation.stateholders

import com.nitin.movieapp.domain.model.list.Movie

data class MovieStateHolder (
    val isLoading:Boolean = false,
    val data : List<Movie>? = null,
    val error : String = ""
)