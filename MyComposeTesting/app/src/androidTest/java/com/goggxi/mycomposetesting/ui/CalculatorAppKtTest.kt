package com.goggxi.mycomposetesting.ui

import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.goggxi.mycomposetesting.R
import com.goggxi.mycomposetesting.ui.theme.MyComposeTestingTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorAppKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()
//    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MyComposeTestingTheme {
                CalculatorApp()
            }
        }
    }

    @Test
    fun calculate_area_of_rectangle_correct() {
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.enter_length))
            .performTextInput("3")
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.enter_width))
            .performTextInput("2")
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.count))
            .performClick()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.count), useUnmergedTree = true)
            .assertHasNoClickAction()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.result, 6.0))
            .assertExists()
//        composeTestRule.onNodeWithText("Masukkan panjang").performTextInput("3")
//        composeTestRule.onNodeWithText("Masukkan lebar").performTextInput("2")
//        composeTestRule.onNodeWithText("Hitung!").performClick()
//        composeTestRule.onNodeWithText("Hasil: 6.0").assertExists()
    }

    @Test
    fun wrong_input_not_calculated() {
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.enter_length))
            .performTextInput("..3")
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.enter_width))
            .performTextInput("4")
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.count))
            .performClick()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.result, 0.0))
            .assertExists()
    }
}