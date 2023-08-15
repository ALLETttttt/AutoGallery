package com.example.carcatalogue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carcatalogue.ui.theme.CarCatalogueTheme
import com.example.carcatalogue.ui_components.MainScreen
import com.example.carcatalogue.utils.Routes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            CarCatalogueTheme {
                NavHost(
                    navController = navController,
                    startDestination = Routes.MAIN_SCREEN
                ) {
                    composable(Routes.MAIN_SCREEN) {
                        MainScreen(context = this@MainActivity) {

                        }
                    }
                }
            }
        }
    }
}