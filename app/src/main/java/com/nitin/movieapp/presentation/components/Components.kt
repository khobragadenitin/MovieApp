package com.nitin.movieapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nitin.movieapp.R
import com.nitin.movieapp.domain.model.list.Movie

@Composable
fun MovieItem(movie: Movie, onClick:(String)->Unit) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        Card (
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier
                .padding(15.dp)
                .testTag("movieItem")
                .wrapContentHeight()
                .fillMaxWidth(0.8f)
                .clickable {
                    onClick.invoke(movie.id.toString())
                }
        ){
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

                AsyncImage(model = "https://image.tmdb.org/t/p/w500/${movie.posterPath}",
                    placeholder = painterResource(id = R.drawable.error),
                    contentDescription =null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .testTag("movieImage")
                    , contentScale = ContentScale.None)

                Text(text = movie.title, modifier = Modifier
                    .padding(8.dp)
                    .testTag("movieTitle"), style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}