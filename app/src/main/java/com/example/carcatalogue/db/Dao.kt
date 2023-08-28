package com.example.carcatalogue.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.carcatalogue.utils.ListItem
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ListItem)

    @Delete
    suspend fun deleteItem(item: ListItem)

    @Query("SELECT * FROM main WHERE category LIKE:cat")
    fun getListItemByCategory(cat: String): Flow<List<ListItem>>

    @Query("SELECT * FROM main WHERE isFav = 1")
    fun getFavourites(): Flow<List<ListItem>>

}