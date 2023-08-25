package com.example.carcatalogue.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.carcatalogue.utils.ListItem

@Database(
    entities = [ListItem::class],
    version = 1
)
abstract class MainDb: RoomDatabase() {
}