package com.exemple.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.exemple.myapplication.databinding.ActivityMainBinding
import com.exemple.myapplication.extras.adapters.ProductsAdapter

/*
    View -> classe que monta o layout do design e gerencia os listners/eventos
*/

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private val adapter = ProductsAdapter{ product ->
        Toast.makeText(this, product.title, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.recyclerview.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        viewModel.productsList.observe(this) {products ->
            adapter.setProductsList(products)
        }

        viewModel.isLoading.observe(this) {isLoading ->
            if (isLoading) {
                binding.loading.visibility = View.VISIBLE
            } else {
                binding.loading.visibility = View.GONE
            }

        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllProducts()
    }
}