package com.fernando.calculatetip.ui

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fernando.calculatetip.R
import com.fernando.calculatetip.custom.CustomTextField
import java.text.NumberFormat
import kotlin.math.round


@Composable
fun Home() {
    var value by remember { mutableStateOf("") }
    var porcentagem by remember { mutableStateOf("") }
    var round by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(15.dp)
                .verticalScroll(ScrollState(0)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = stringResource(R.string.calculadora_de_gorjeta),
                fontSize = 22.sp
            )
            CustomTextField(
                value = value,
                onValueChange = {value = it},
                label = R.string.valor,
                imeAction = ImeAction.Next
            )
            CustomTextField(
                value = porcentagem,
                onValueChange = {porcentagem = it},
                label = R.string.porcentagem,
                imeAction = ImeAction.Done
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(R.string.arredondar))
                Switch(checked = round, onCheckedChange = {round = it})
            }
            Text(
                text = stringResource(R.string.gorjeta, gorjeta(value,porcentagem,round)),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// visivel para fins de teste
@VisibleForTesting
internal fun gorjeta(valor: String, porcentagem: String, round: Boolean): String{

    val valor_double: Double = valor.toDoubleOrNull() ?: 0.0
    val porc: Double = porcentagem.toDoubleOrNull() ?: 0.0
    val total: Double = valor_double/100 * porc

    return if (round){
        NumberFormat.getCurrencyInstance().format(round(total))
    }
    else{
        NumberFormat.getCurrencyInstance().format(total)
    }

}