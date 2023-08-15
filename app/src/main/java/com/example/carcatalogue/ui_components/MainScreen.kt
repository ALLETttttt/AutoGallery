package com.example.carcatalogue.ui_components

import android.annotation.SuppressLint
import android.content.Context
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
import androidx.compose.ui.unit.dp
import com.example.carcatalogue.utils.DrawerEvents
import com.example.carcatalogue.utils.ListItem
import com.example.carcatalogue.utils.idArrayList
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(context: Context, onClick: (ListItem) -> Unit) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val mainList = remember {
        mutableStateOf(getListItemsByIndex(0, context))
    }
    val topBarTitle = remember {
        mutableStateOf("Porsche")
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerMenu { event ->
                when(event) {
                    is DrawerEvents.OnItemClick -> {
                        topBarTitle.value = event.title
                        mainList.value = getListItemsByIndex(event.index, context = context)
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

private fun getListItemsByIndex(index: Int, context: Context): List<ListItem> {
    val list = ArrayList<ListItem>()
    val arraylist = context.resources.getStringArray(idArrayList.listId[index])
    arraylist.forEach { item ->
        val itemArr = item.split("|")
        list.add(
            ListItem(itemArr[0], itemArr[1])
        )
    }
    return list
}