package com.nitin.movieapp.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.nitin.movieapp.R
import com.nitin.movieapp.presentation.viewmodels.MovieDetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieDetailsScreen(movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()) {

    Scaffold(topBar = { TopAppBar(title = { Text(text = "Movie Details")} ) }) {
        innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)){

            val result = movieDetailsViewModel.movieDetailsStateHolder.value

            if (result.isLoading){
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    CircularProgressIndicator(modifier = Modifier.testTag("progress"))
                }
            }

            if (result.error.isNotBlank()){
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text(text = result.error)
                }
            }

            result.data?.let {

                Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                    AsyncImage(model = "https://image.tmdb.org/t/p/w500/${it.posterPath}",
                        placeholder = painterResource(id = R.drawable.error),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                            .testTag("movieDetailsImage")
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(text = it.originalTitle, style = MaterialTheme.typography.headlineMedium, modifier = Modifier.testTag("movieDetailsTitle"))

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(text = it.tagline, style = MaterialTheme.typography.titleMedium, modifier = Modifier.wrapContentHeight().testTag("movieDetailsTagLine"))

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = it.overview, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.testTag("movieDetailsOverView"))

                }
            }
        }
    }
}