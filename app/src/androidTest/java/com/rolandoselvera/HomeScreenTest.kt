package com.rolandoselvera

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rolandoselvera.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testLazyColumnIsVisible() {
        // Wait until the LazyColumn is available:
        composeTestRule.waitUntil(timeoutMillis = 3000) {
            composeTestRule.onAllNodesWithTag("weatherList").fetchSemanticsNodes().isNotEmpty()
        }

        // Assert that the LazyColumn exists and is visible:
        composeTestRule.onNodeWithTag("weatherList")
            .assertExists("LazyColumn with tag 'weatherList' does not exist!")
            .assertIsDisplayed()

        println("Test Passed: LazyColumn is visible on the screen.")
    }

    @Test
    fun testNoResultsTextIsVisible() {
        // Wait until the text "No weather results found." is available:
        composeTestRule.waitUntil(timeoutMillis = 3000) {
            composeTestRule.onAllNodesWithText("No weather results found.", ignoreCase = true)
                .fetchSemanticsNodes().isNotEmpty()
        }

        // Assert that the text "No weather results found." exists and is visible:
        composeTestRule.onNodeWithText("No weather results found.", ignoreCase = true)
            .assertExists("Text 'No weather results found.' does not exist!")
            .assertIsDisplayed()

        println("Test Passed: 'No weather results found.' text is visible on the screen.")
    }
}