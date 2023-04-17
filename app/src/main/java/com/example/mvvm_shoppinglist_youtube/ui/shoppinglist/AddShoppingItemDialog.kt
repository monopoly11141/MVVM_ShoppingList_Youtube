package com.example.mvvm_shoppinglist_youtube.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.mvvm_shoppinglist_youtube.R
import com.example.mvvm_shoppinglist_youtube.data.db.entities.ShoppingItem

class AddShoppingItemDialog(context : Context, var addDialogListener: AddDialogListener) : AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)

        findViewById<Button>(R.id.btnAdd)?.setOnClickListener {
            val name = findViewById<EditText>(R.id.etName)?.text.toString()
            val amount = findViewById<EditText>(R.id.etAmount)?.text.toString()

            if(name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(context, "Please enter all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val item = ShoppingItem(name, amount.toInt())
            addDialogListener.onAddButtonListener(item)
            dismiss()
        }

        findViewById<Button>(R.id.btnCancel)?.setOnClickListener {
            cancel()
        }
    }

}