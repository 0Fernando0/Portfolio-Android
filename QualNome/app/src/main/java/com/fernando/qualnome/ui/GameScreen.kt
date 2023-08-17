package com.fernando.qualnome.ui

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fernando.qualnome.R
import com.fernando.qualnome.model.AppUIState

@Composable
fun GameScreen(appViewModel: AppViewModel = viewModel()) {
    val state: State<AppUIState> = appViewModel.uiState.collectAsState()
    Box(modifier = Modifier.fillMaxSize())
    {
        if (state.value.fimJogo){
            FinalScoreDialog(score = state.value.score,
                onPlayAgain = { appViewModel.iniciar_jogo() })
        }
        GameLayout(
            appViewModel = appViewModel,
            state = state,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun GameLayout(
    appViewModel: AppViewModel,
    state: State<AppUIState>,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Text(text = stringResource(id = R.string.app_name))
        CaixaDeEntrada(
            value = appViewModel.palpite,
            onValueChange = {appViewModel.insirir_nome(it)},
            tentativa = state.value.tentativa,
            palavraSelecionada = state.value.palavraSelecionada
        )

        Button(onClick = { appViewModel.checar_nome() },
            modifier = Modifier.fillMaxWidth(.85f)) { Text(text = stringResource(R.string.tentar))}

        OutlinedButton(onClick = { appViewModel.pular_nome() },
            modifier = Modifier.fillMaxWidth(.85f)) { Text(text = stringResource(R.string.pular))}

        Text(text = stringResource(R.string.score,state.value.score),
            color = Color.Black,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray)
                .padding(12.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaixaDeEntrada(
    value: String,
    onValueChange: (String) -> Unit,
    tentativa: Int,
    palavraSelecionada: String
) {
    Card{
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                stringResource(id = R.string.tentativas,tentativa),
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .align(Alignment.End)
                    .background(Color.Blue)
                    .padding(4.dp),
                color = Color.White)
            Text(text = palavraSelecionada, fontSize = 32.sp)
            Text(text = stringResource(id = R.string.descricao), fontSize = 12.sp)
            OutlinedTextField(
                value = value, onValueChange = onValueChange,
                label = { Text(text = stringResource(R.string.descricao_label)) },
                isError = false,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )

        }
    }
}

@Composable
private fun FinalScoreDialog(
    score: Int,
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        confirmButton = { 
                        TextButton(onClick = onPlayAgain) {
                            Text(text = stringResource(R.string.jogar_novamente))
                        }
        },
        title = { Text(text = stringResource(R.string.parabens))},
        text = { Text(text = stringResource(R.string.seu_score,score))},
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = { activity.finish() }) {
                Text(text = stringResource(R.string.sair_do_jogo))
            }
        }
    )
}