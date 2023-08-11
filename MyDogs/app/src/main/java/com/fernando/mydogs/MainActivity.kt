package com.fernando.mydogs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.fernando.mydogs.data.ListDogs
import com.fernando.mydogs.ui.MyDogsApp
import com.fernando.mydogs.ui.theme.MyDogsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val dogs = ListDogs.dogs
            MyDogsTheme {
                MyDogsApp(lista = dogs)
            }
        }
    }
}
