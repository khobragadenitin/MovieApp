package com.nitin.movieapp.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nitin.movieapp.presentation.stateholders.MovieStateHolder
import com.nitin.movieapp.core.common.Resource
import com.nitin.movieapp.domain.use_cases.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieUseCase: GetMovieUseCase): ViewModel() {

    private val _movieListStateHolder = mutableStateOf(MovieStateHolder())
    val movieListStateHolder : State<MovieStateHolder> = _movieListStateHolder

    init {
        getMovieList()
    }

    private fun getMovieList() = viewModelScope.launch {

        movieUseCase().onEach {
                when(it){
                    is Resource.Loading -> {
                        _movieListStateHolder.value = MovieStateHolder(isLoading = true)
                    }
                    is Resource.Success -> {
                        _movieListStateHolder.value = MovieStateHolder(data = it.data)
                    }
                    is Resource.Error -> {
                        _movieListStateHolder.value = MovieStateHolder(error= it.message.toString())
                    }

                }
            }.launchIn(viewModelScope)

        }
    }