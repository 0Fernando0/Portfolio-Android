package com.fernando.qualnome.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fernando.qualnome.data.AUMENTAR_SCORE
import com.fernando.qualnome.data.ListaDeNomes
import com.fernando.qualnome.model.AppUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel: ViewModel() {
    private val _uiState: MutableStateFlow<AppUIState> = MutableStateFlow(AppUIState())
    val uiState: StateFlow<AppUIState>
        get() = _uiState.asStateFlow()

    var palpite: String by mutableStateOf("")
        private set

    private lateinit var nome: String

    private val nomesUsados: MutableSet<String> = mutableSetOf()
    fun insirir_nome(text: String){ palpite = text }


    private fun embaralhar_nome(palavra: String): String{
        val embaralhar = palavra.toCharArray()
        embaralhar.shuffle()
        while (String(embaralhar) == palavra){
            embaralhar.shuffle()
        }
        return String(embaralhar)
    }
    fun iniciar_jogo(){
        nomesUsados.clear()
        nome = ListaDeNomes.listaDeNomes.random()
        nomesUsados.add(this.nome)
        _uiState.value = AppUIState(palavraSelecionada = embaralhar_nome(nome))
    }

    fun pular_nome(){
        if (uiState.value.tentativa == 10){
            _uiState.update {
                it.copy(
                    fimJogo = true
                )
            }
            return
        }

        if (nomesUsados == ListaDeNomes.listaDeNomes){ nomesUsados.clear() }

        while (nomesUsados.contains(this.nome)){
            this.nome = ListaDeNomes.listaDeNomes.random()
        }
        _uiState.update {
            it.copy(
                palavraSelecionada = embaralhar_nome(this.nome),
                tentativa = it.tentativa.inc()
            )
        }
        this.palpite = ""
        nomesUsados.add(nome)

    }

    fun checar_nome(){

        if (uiState.value.tentativa == 10 ){
            if (this.palpite.lowercase().trim() == this.nome) _uiState.update { it.copy(score = it.score + AUMENTAR_SCORE)}
            _uiState.update { it.copy(fimJogo = true) }
            return
        }

        if (this.palpite.lowercase().trim() == this.nome){
            _uiState.update {
                it.copy(
                    score = it.score + AUMENTAR_SCORE
                )
            }
            this.palpite = ""
            pular_nome()
        }else{
            _uiState.update {
                it.copy(
                    tentativa = it.tentativa.inc()
                )
            }
        }
    }


    init {
        iniciar_jogo()
    }
}