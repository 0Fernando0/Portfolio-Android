package com.fernando.calculatetip

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.fernando.calculatetip.ui.Home
import com.fernando.calculatetip.ui.theme.CalculateTipTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class GorjetaUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calcula_20_porcento(){
        composeTestRule.setContent {
            CalculateTipTheme {
                Home()
            }
        }

        composeTestRule
            .onNodeWithText("Valor")
            .performTextInput("10")

        composeTestRule
            .onNodeWithText("(%)")
            .performTextInput("10")

        val expect = NumberFormat
            .getCurrencyInstance()
            .format(1.0)

        composeTestRule
            .onNodeWithText("Gorjeta: $expect")
            .assertExists("teste compativel")

    }

}