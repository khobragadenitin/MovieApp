package com.nitin.movieapp.movie_details

import com.nitin.movieapp.domain.model.details.MovieDetails
import com.nitin.movieapp.domain.repository.GetMovieDetailsRepository
import com.nitin.movieapp.domain.use_cases.GetMovieDetailsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class GetMovieDetailsUseCaseShould {


    private lateinit var getMovieDetailsRepository: GetMovieDetailsRepository
    private lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase
    private val movieDetails  = mock<MovieDetails>()
    private val movieId = "848326"


    @Before
    fun setUp(){
        getMovieDetailsRepository = mock()
        getMovieDetailsUseCase = GetMovieDetailsUseCase(getMovieDetailsRepository)

    }

    @Test
    fun returnMovieFromUseCaseInSuccess() = runTest{
        `when`(getMovieDetailsRepository.getMovieDetails(movieId)).thenReturn(
            movieDetails
        )
        getMovieDetailsUseCase.invoke(movieId).onEach {
            Assert.assertEquals(movieDetails,it.data)
        }
    }

    @Test
    fun returnErrorFromUseCaseInSuccess() = runTest{
        `when`(getMovieDetailsRepository.getMovieDetails(movieId)).thenThrow(
            RuntimeException("Something Went Wrong")
        )
        getMovieDetailsUseCase.invoke(movieId).onEach {
            Assert.assertEquals("Something Went Wrong",it.message)
        }
    }
}