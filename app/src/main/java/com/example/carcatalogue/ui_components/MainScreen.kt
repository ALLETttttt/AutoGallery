package com.example.carcatalogue.ui_components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.carcatalogue.MainViewModule
import com.example.carcatalogue.R
import com.example.carcatalogue.utils.DrawerEvents
import com.example.carcatalogue.utils.ListItem
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    mainViewModule: MainViewModule = hiltViewModel(),
    onClick: (ListItem) -> Unit
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val mainList = mainViewModule.mainList
    val topBarTitle = remember {
        mutableStateOf("Porsche")
    }
    mainViewModule.getAllItemsByCategory(topBarTitle.value)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerMenu { event ->
                when(event) {
                    is DrawerEvents.OnItemClick -> {
                        topBarTitle.value = event.title
                        mainViewModule.getAllItemsByCategory(event.title)
                    }
                }
                coroutineScope.launch {
                    drawerState.close()
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                MainTopBar(title = topBarTitle.value, drawerState)
            },
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = "background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 55.dp)
            ) {
                items(mainList.value) {
                    MainListItem(item = it) { listItem ->
                        onClick(listItem)
                    }
                }
            }
        }
    }
}

