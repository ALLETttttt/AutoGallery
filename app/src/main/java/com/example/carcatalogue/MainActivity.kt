package com.example.carcatalogue

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState

import com.example.carcatalogue.ui.theme.CarCatalogueTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scaffoldState = rememberScaffoldState()
            CarCatalogueTheme {
                Scaffold(
                    scaffoldState = scaffoldState,

                ) {

                }
            }
        }
    }
}

