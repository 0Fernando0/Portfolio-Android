package com.fernando.marsphotos.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernando.marsphotos.network.ApiService
import com.fernando.marsphotos.network.MarsUIState
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MarsViewModel: ViewModel() {

    var state: MarsUIState by mutableStateOf(MarsUIState.Loading)
        private set

    init {
        runBlocking {
            getPhotos()
        }
    }

    suspend fun getPhotos(){

        viewModelScope.launch {

            state = try {
                val connection = ApiService.retrofitService.getPhotos()
                MarsUIState.Sucess(connection)
            } catch (error: Exception) {
                MarsUIState.Erro(error.toString())
            }

        }

    }

}