package com.fernando.marsphotos


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.fernando.marsphotos.ui.HomeScreen
import com.fernando.marsphotos.ui.MarsViewModel
import com.fernando.marsphotos.ui.theme.MarsPhotosTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val view = MarsViewModel()
            MarsPhotosTheme {
                HomeScreen(view = view.state)
            }
        }
    }
}
