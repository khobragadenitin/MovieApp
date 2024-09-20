package com.nitin.movieapp.domain.use_cases

import com.nitin.movieapp.core.common.Resource
import com.nitin.movieapp.domain.model.details.MovieDetails
import com.nitin.movieapp.domain.repository.GetMovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val getMovieDetailsRepository: GetMovieDetailsRepository) {

    operator fun invoke(id:String) : Flow<Resource<MovieDetails>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = getMovieDetailsRepository.getMovieDetails(id = id)))
        } catch (e : Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

}