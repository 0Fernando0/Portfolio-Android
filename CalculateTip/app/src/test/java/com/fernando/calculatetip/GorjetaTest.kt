package com.fernando.calculatetip

import com.fernando.calculatetip.ui.gorjeta
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.NumberFormat

class GorjetaTest {

    @Test
    fun calcula_20_porcento_noRound(){
        val amount = 10.00
        val tipPercent = 20.00
        val expeckTip = NumberFormat.getCurrencyInstance().format(2.0)
        val actualTip = gorjeta(amount.toString(),tipPercent.toString(),false)
        assertEquals(expeckTip,actualTip)
    }

}