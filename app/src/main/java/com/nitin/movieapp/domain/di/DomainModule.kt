package com.nitin.movieapp.domain.di

import com.nitin.movieapp.data.network.ApiService
import com.nitin.movieapp.data.repository.GetMovieDetailsRepositoryImpl
import com.nitin.movieapp.data.repository.GetMoviesRepositoryImpl
import com.nitin.movieapp.domain.repository.GetMovieDetailsRepository
import com.nitin.movieapp.domain.repository.GetMovieRepository
import com.nitin.movieapp.domain.use_cases.GetMovieDetailsUseCase
import com.nitin.movieapp.domain.use_cases.GetMovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun providesGetMoviesRepository(apiService: ApiService) : GetMovieRepository{
        return GetMoviesRepositoryImpl(apiService)
    }

    @Provides
    fun providesGetMovieDetailsRepository(apiService: ApiService):GetMovieDetailsRepository{
        return GetMovieDetailsRepositoryImpl(apiService)
    }

    @Provides
    fun providesGetMovieUseCase(getMovieRepository: GetMovieRepository) : GetMovieUseCase{
        return GetMovieUseCase(getMovieRepository)
    }

    @Provides
    fun providesGetMovieDetailsUseCase (getMovieDetailsRepository: GetMovieDetailsRepository) : GetMovieDetailsUseCase{
        return GetMovieDetailsUseCase(getMovieDetailsRepository)
    }
}