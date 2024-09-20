package com.nitin.movieapp.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nitin.movieapp.presentation.navigation.MovieNavigationItem
import com.nitin.movieapp.presentation.components.MovieItem
import com.nitin.movieapp.presentation.viewmodels.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieListScreen(navHostController: NavHostController, viewModel: MovieViewModel = hiltViewModel()) {

    Scaffold(topBar = { TopAppBar(title = { Text(text = "Movie List")} ) }) {

        innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)){
            val result = viewModel.movieListStateHolder.value

            if (result.isLoading){
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center){
                    CircularProgressIndicator(modifier = Modifier.testTag("progress"))
                }
            }

            if (result.error.isNotBlank()){
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center){
                    Text(text = result.error)
                }
            }


            if (result.data!=null) {
                LazyColumn(modifier = Modifier.testTag("movie_list")) {
                    items(result.data) {
                        MovieItem(it) {
                            navHostController.navigate(MovieNavigationItem.MovieDetails.route + "/$it")
                        }
                    }
                }
            }
        }

    }
}


 
