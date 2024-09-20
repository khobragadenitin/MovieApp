package com.nitin.movieapp.movie_list

import com.nitin.movieapp.BuildConfig
import com.nitin.movieapp.MainCoroutineRule
import com.nitin.movieapp.data.model.list.MovieListResponseDTO
import com.nitin.movieapp.data.network.ApiService
import com.nitin.movieapp.data.repository.GetMoviesRepositoryImpl
import com.nitin.movieapp.domain.repository.GetMovieRepository
import com.nitin.movieapp.data.mappers.toDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class GetMovieRepositoryShould {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()
    private lateinit var getMovieRepository: GetMovieRepository
    private lateinit var apiService: ApiService
    private val movieList  = mock <MovieListResponseDTO>()

    @Before
    fun setUp(){
        apiService = mock()
        getMovieRepository = GetMoviesRepositoryImpl(apiService)
    }

    @Test
    fun returnSuccessWhenGetDataFromBackend() = runTest{
        `when`(apiService.getMovieList(BuildConfig.API_KEY)).thenReturn(
            movieList
        )
        val result = getMovieRepository.getMovies()
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals(movieList.results!!.toDomain(),result)
    }
}