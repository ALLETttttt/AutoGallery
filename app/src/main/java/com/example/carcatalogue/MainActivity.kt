package com.example.carcatalogue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.carcatalogue.ui.theme.CarCatalogueTheme
import com.example.carcatalogue.ui_components.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarCatalogueTheme {
                MainScreen(context = this)
            }
        }
    }
}