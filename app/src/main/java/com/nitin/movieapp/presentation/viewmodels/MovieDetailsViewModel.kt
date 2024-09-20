package com.nitin.movieapp.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nitin.movieapp.core.common.Resource
import com.nitin.movieapp.domain.use_cases.GetMovieDetailsUseCase
import com.nitin.movieapp.presentation.stateholders.MovieDetailsStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val movieDetailsUseCase: GetMovieDetailsUseCase,savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _movieDetailsStateHolder = mutableStateOf(MovieDetailsStateHolder())
    val movieDetailsStateHolder : State<MovieDetailsStateHolder> = _movieDetailsStateHolder

    init {
        viewModelScope.launch {

            savedStateHandle.getStateFlow("id", "0").collectLatest {

                getMovieDetails(it)

            }
        }
    }

    private fun getMovieDetails(id: String) = viewModelScope.launch {

        movieDetailsUseCase(id).onEach {
            when(it){

                is Resource.Loading -> {
                    _movieDetailsStateHolder.value = MovieDetailsStateHolder(isLoading = true)
                }

                is Resource.Success -> {
                    _movieDetailsStateHolder.value = MovieDetailsStateHolder(data = it.data)
                }

                is Resource.Error -> {
                    _movieDetailsStateHolder.value = MovieDetailsStateHolder(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}