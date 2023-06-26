package com.fernando.marsphotos.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fernando.marsphotos.network.MarsPhoto
import com.fernando.marsphotos.network.MarsUIState


@Composable
fun HomeScreen(view: MarsUIState) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when(view){
            is MarsUIState.Erro -> ErrorScreen(view.aviso)
            MarsUIState.Loading -> LoadingScreen()
            is MarsUIState.Sucess -> SucessScreen(view.photos)
        }
    }
}

@Composable
fun LoadingScreen() = Text(text = "Loading...")

@Composable
fun SucessScreen(photos: List<MarsPhoto>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ){
        items(items = photos, key = {photo -> photo.id}){
            MarsPhotoCard(photo = it)
        }        
    }
}

@Composable
fun ErrorScreen(mensagem: String) = Text(text = "Error To Loading\n$mensagem")
