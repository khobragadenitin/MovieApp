package com.nitin.movieapp.movie_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.nitin.movieapp.presentation.MainActivity
import org.junit.Rule
import org.junit.Test

class MovieListFeature {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun validateProgressBarVisible(){
        composeRule.apply{
            onNodeWithTag("progress").assertIsDisplayed()
        }
    }

    @Test
    fun validateTopAppBarTitleIsVisibleCorrectly(){
        composeRule.apply{
            onNodeWithText("Movie List").assertIsDisplayed()
        }
    }

    @Test
    fun validateIsMovieListVisible(){
        composeRule.apply{
            Thread.sleep(2000)
            onNodeWithTag("movie_list").assertIsDisplayed()
        }
    }

    @Test
    fun validateNavigationFromMovieListToMovieDetails(){
        composeRule.apply {
            Thread.sleep(2000)
            onNodeWithTag("movie_list").onChildAt(0).performClick()
            onNodeWithText("Movie Details").assertIsDisplayed()
        }
    }
}