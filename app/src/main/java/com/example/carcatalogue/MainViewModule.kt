package com.example.carcatalogue

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carcatalogue.db.MainDb
import com.example.carcatalogue.utils.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModule @Inject constructor(
    private val mainDb: MainDb
) : ViewModel() {
    val mainList = mutableStateOf(emptyList<ListItem>())
    private var job: Job? = null

    fun getAllItemsByCategory(cat: String) {
        job?.cancel()
        job = viewModelScope.launch {
            mainDb.dao.getListItemByCategory(cat).collect() {list ->
                mainList.value = list
            }
        }
    }

    fun getFavourites() {
        job?.cancel()
        job = viewModelScope.launch {
            mainDb.dao.getFavourites().collect() {list ->
                mainList.value = list
            }
        }
    }

    fun insertItem(item: ListItem) = viewModelScope.launch {
        mainDb.dao.insertItem(item)
    }

    fun deleteItem(item: ListItem) = viewModelScope.launch {
        mainDb.dao.deleteItem(item)
    }
}