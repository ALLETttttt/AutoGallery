package com.example.carcatalogue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carcatalogue.ui.theme.CarCatalogueTheme
import com.example.carcatalogue.ui_components.InfoScreen
import com.example.carcatalogue.ui_components.MainScreen
import com.example.carcatalogue.utils.ListItem
import com.example.carcatalogue.utils.Routes
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            var item: ListItem? = null
            CarCatalogueTheme {
                NavHost(
                    navController = navController,
                    startDestination = Routes.MAIN_SCREEN
                ) {

                    composable(Routes.MAIN_SCREEN) {
                        MainScreen(context = this@MainActivity) {
                            item = it
                            navController.navigate(Routes.INFO_SCREEN)
                        }
                    }

                    composable(Routes.INFO_SCREEN) {
                        InfoScreen(item = item!!)
                    }
                }
            }
        }
    }
}