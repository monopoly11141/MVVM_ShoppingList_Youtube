package com.example.mvvm_shoppinglist_youtube.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_shoppinglist_youtube.R
import com.example.mvvm_shoppinglist_youtube.data.db.ShoppingDatabase
import com.example.mvvm_shoppinglist_youtube.data.db.entities.ShoppingItem
import com.example.mvvm_shoppinglist_youtube.data.repositories.ShoppingRepository
import com.example.mvvm_shoppinglist_youtube.databinding.ActivityShoppingBinding
import com.example.mvvm_shoppinglist_youtube.other.ShoppingItemAdapter

class ShoppingActivity : AppCompatActivity() {

    private lateinit var binding : ActivityShoppingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shopping)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        val viewModel : ShoppingViewModel by viewModels{ factory }

        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        binding.rvShoppingItem.layoutManager = LinearLayoutManager(this)
        binding.rvShoppingItem.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        binding.fabAdd.setOnClickListener {
            AddShoppingItemDialog(this, object : AddDialogListener{
                override fun onAddButtonListener(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }
}