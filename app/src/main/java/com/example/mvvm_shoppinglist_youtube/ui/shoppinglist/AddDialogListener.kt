package com.example.mvvm_shoppinglist_youtube.ui.shoppinglist

import com.example.mvvm_shoppinglist_youtube.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonListener(item: ShoppingItem)
}