package com.fernando.marsphotos.ui


import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.fernando.marsphotos.R
import com.fernando.marsphotos.network.MarsPhoto


@Composable
fun MarsPhotoCard(
    photo: MarsPhoto
) {
    Card(
        modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        .aspectRatio(1f)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(photo.img_src)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            error = painterResource(id = R.drawable.broken_image),
            placeholder = painterResource(id = R.drawable.circulo)
        )
    }
}