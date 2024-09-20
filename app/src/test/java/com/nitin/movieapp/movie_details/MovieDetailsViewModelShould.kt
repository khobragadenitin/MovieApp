package com.nitin.movieapp.movie_details

import androidx.lifecycle.SavedStateHandle
import com.nitin.movieapp.MainCoroutineRule
import com.nitin.movieapp.core.common.Resource
import com.nitin.movieapp.domain.model.details.MovieDetails
import com.nitin.movieapp.domain.use_cases.GetMovieDetailsUseCase
import com.nitin.movieapp.presentation.viewmodels.MovieDetailsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailsViewModelShould {

    @get:Rule
    val mainCoroutineRule =  MainCoroutineRule()

    private val getMovieDetailsUseCase : GetMovieDetailsUseCase = mock()
    private lateinit var moviedetailsViewModel: MovieDetailsViewModel
    private val movieDetails : MovieDetails? = mock()
    private val movieId = "848326"


    @Before
    fun setup(){
        val savedStateHandle = SavedStateHandle()
        savedStateHandle.set("id",movieId)
        moviedetailsViewModel =  MovieDetailsViewModel(getMovieDetailsUseCase, savedStateHandle)
    }

    @Test
    fun validateUserWillSeeProgressBarInitially() = runTest {
        `when`(getMovieDetailsUseCase.invoke(movieId)).thenReturn(
            flow {
                emit(Resource.Loading())
            }
        )
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals(true,moviedetailsViewModel.movieDetailsStateHolder.value.isLoading)
    }

    @Test
    fun validateSuccessStateIsStoredInMovieDetails() = runTest {
        `when`(getMovieDetailsUseCase.invoke(movieId)).thenReturn(
            flow {
                emit(Resource.Success(movieDetails))
            }
        )
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals(movieDetails,moviedetailsViewModel.movieDetailsStateHolder.value.data)
    }

    @Test
    fun throwErrorInErrorCase() = runTest {
        `when`(getMovieDetailsUseCase.invoke(movieId)).thenReturn(
            flow {
                emit(Resource.Error("Something Went Wrong"))
            }
        )
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals("Something Went Wrong",moviedetailsViewModel.movieDetailsStateHolder.value.error)
    }

}