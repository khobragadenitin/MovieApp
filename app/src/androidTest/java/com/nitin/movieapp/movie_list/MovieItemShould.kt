package com.nitin.movieapp.movie_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.nitin.movieapp.domain.model.list.Movie
import com.nitin.movieapp.presentation.components.MovieItem
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieItemShould {

    @get:Rule
    val composerule = createComposeRule()
    private lateinit var movie : Movie

    @Before
    fun setUp() {
        movie = Movie(id = 753342,"/jE5o7y9K6pZtWNNMEw3IdpHuncR.jpg","Napoleon")
        composerule.setContent {
            MovieItem(movie = movie,{})
        }
    }

    @Test
    fun beDispalyed(){
        composerule.onNodeWithTag("movieItem").assertIsDisplayed()
    }

    @Test
    fun containCorrectTitle(){
        composerule.onNodeWithText("Napoleon")
    }
}