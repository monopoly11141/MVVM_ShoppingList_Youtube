package com.example.mvvm_shoppinglist_youtube.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.mvvm_shoppinglist_youtube.data.db.entities.ShoppingItem
import kotlinx.coroutines.flow.StateFlow

@Dao
interface ShoppingDao {

    @Upsert
    suspend fun upsert(item : ShoppingItem)

    @Delete
    suspend fun delete(item : ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems() : LiveData<List<ShoppingItem>>


}