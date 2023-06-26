package com.fernando.marsphotos.network

sealed interface MarsUIState{
    data class Sucess(val photos: List<MarsPhoto>): MarsUIState
    data class Erro(val aviso: String): MarsUIState
    object Loading: MarsUIState
}