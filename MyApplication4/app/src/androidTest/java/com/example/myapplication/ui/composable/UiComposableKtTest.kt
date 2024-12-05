
package com.example.myapplication.ui.composable


import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import org.junit.Test

internal class UiComposableKtTest {


    @OptIn(ExperimentalTestApi::class)
    @Test
    fun titleForEachPage() = runComposeUiTest{
        // Arrange
        val header = "Header"
        val subHeader = "SubHeader"

        // Act
        setContent {
            TitleForEachPage(
                header = header,
                subHeader = subHeader
            )
        }

        // Assert
        onNodeWithText(header).assertIsDisplayed()
        onNodeWithText(subHeader).assertIsDisplayed()
    }

//    @org.junit.jupiter.api.Test
//    fun progressCircle() {
//    }
//
//    @org.junit.jupiter.api.Test
//    fun customRow() {
//    }
//
//    @org.junit.jupiter.api.Test
//    fun clickableBox() {
//    }
}
