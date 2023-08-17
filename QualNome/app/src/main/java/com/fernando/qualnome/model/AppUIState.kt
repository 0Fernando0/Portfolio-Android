package com.fernando.qualnome.model


// classe para representar os estados do app
data class AppUIState(
    val palavraSelecionada: String = "",
    val tentativa: Int = 1,
    val score: Int = 0,
    val fimJogo: Boolean = false
)
