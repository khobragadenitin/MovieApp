package com.nitin.movieapp.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nitin.movieapp.presentation.screens.MovieListScreen
import com.nitin.movieapp.presentation.screens.MovieDetailsScreen

@Composable
fun MovieNavigation(navHostController: NavHostController) {

    NavHost(navController = navHostController,
        startDestination = MovieNavigationItem.MovieList.route ){

        composable(MovieNavigationItem.MovieList.route){
            MovieListScreen(navHostController)
        }

        composable(MovieNavigationItem.MovieDetails.route+"/{id}"){
            val id = it.arguments?.getString("id")
            Log.d("MovieDetailsScreen",""+id)
            MovieDetailsScreen()
        }
    }
}