package com.nitin.movieapp.movie_details

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.nitin.movieapp.presentation.MainActivity
import org.junit.Rule
import org.junit.Test

class MovieDetailsFeature {


    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun validateMovieDetailsImageVisible(){
        composeRule.apply {
            Thread.sleep(2000)
            onNodeWithTag("movie_list").onChildAt(0).performClick()
            onNodeWithTag("movieDetailsImage").assertIsDisplayed()
        }
    }

    @Test
    fun validateMovieDetailsTitleVisible(){
        composeRule.apply {
            Thread.sleep(4000)
            onNodeWithTag("movie_list").onChildAt(0).performClick()
            onNodeWithTag("movieDetailsTitle").assertIsDisplayed()
        }
    }

    @Test
    fun validateMovieDetailsTagLineVisible(){
        composeRule.apply {
            Thread.sleep(2000)
            onNodeWithTag("movie_list").onChildAt(0).performClick()
            onNodeWithTag("movieDetailsTagLine").assertIsDisplayed()
        }
    }
    @Test
    fun validateMovieDetailsOverViewVisible(){
        composeRule.apply {
            Thread.sleep(2000)
            onNodeWithTag("movie_list").onChildAt(0).performClick()
            onNodeWithTag("movieDetailsOverView").assertIsDisplayed()
        }
    }
}