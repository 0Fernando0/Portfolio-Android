package com.fernando.mydogs.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.fernando.mydogs.R
import com.fernando.mydogs.data.Dog


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDogsApp(lista: List<Dog>) {
    Scaffold(
        topBar = { MyDogsTopAppBar()}
    ) {

        LazyColumn(contentPadding = it) {
            items(lista) {
                CardDog(dog = it)
            }
        }
        
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDogsTopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
               Image(painter = painterResource(id = R.drawable.ic_woof_logo),
                   contentDescription = null,
                   modifier = Modifier
                       .size(dimensionResource(id = R.dimen.image_size))
                       .padding(dimensionResource(id = R.dimen.padding_small))
               )
               Text(text = stringResource(id = R.string.app_name),
                   style = MaterialTheme.typography.displayLarge)
            }
        }
    )
}

@Composable
fun CardDog(dog: Dog) {
    var icon by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (icon) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer,
    )

    Card(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_small)),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
                .background(color = color)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_mini)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_mini))
            ) {
                DogImage(dogImage = dog.dogImage, dogName = dog.dogName)
                DogDescribe(dogName = dog.dogName, age = dog.age)
                Spacer(modifier = Modifier.weight(1f))
                DogIcon(icon = icon) { icon = !icon }
            }
            if (icon) {
                DogHobby(dog.hobby)
            }
        }
    }
}


@Composable
fun DogImage(@DrawableRes dogImage: Int,@StringRes dogName: Int) {
    Image(
        painter = painterResource(id = dogImage),
        contentDescription = stringResource(id = dogName),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(dimensionResource(id = R.dimen.image_size))
            .clip(CircleShape)
    )
}
@Composable
fun DogDescribe(@StringRes dogName: Int,age: Int) {
    Column {
        Text(text = stringResource(id = dogName),
            style = MaterialTheme.typography.displayMedium)
        Text(text = stringResource(id = R.string.years_old,age),
            style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun DogIcon(icon: Boolean,funcao: () -> Unit) {
    IconButton(onClick = funcao) {
        Icon(imageVector = if (!icon) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
            contentDescription = null)
    }
}

@Composable
fun DogHobby(
    @StringRes dogHobby: Int
) {
    Column(
        modifier = Modifier
            .padding(
                start = dimensionResource(id = R.dimen.padding_medium),
                top = dimensionResource(id = R.dimen.padding_small),
                end = dimensionResource(id = R.dimen.padding_medium),
                bottom = dimensionResource(id = R.dimen.padding_medium)
            )
    ) {
        Text(text = stringResource(id = R.string.about),
            style = MaterialTheme.typography.labelSmall)
        Text(text = stringResource(id = dogHobby),
            style = MaterialTheme.typography.bodySmall)
    }
}