package com.example.carcatalogue.utils

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "main")
data class ListItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val imageName: String,
    val url: String,
    val category: String,
    val ifFav: Boolean
)
