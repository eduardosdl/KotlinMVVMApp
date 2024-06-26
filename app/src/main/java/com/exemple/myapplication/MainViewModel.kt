package com.exemple.myapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exemple.myapplication.extras.respositories.ProductRepository

/*
    ViewModel -> classe que faz a comunicação (omeio de campo) entre a View e a Model
*/

class MainViewModel: ViewModel() {
    private val repository = ProductRepository()
    private val _productsList = MutableLiveData<List<Product>>()
    val productsList: LiveData<List<Product>>
        get() = _productsList
    val isLoading = MutableLiveData<Boolean>()

    fun getAllProducts() {
        isLoading.value = true

        repository.getAllProducts({ productsList ->
            _productsList.value = productsList
            isLoading.value = false
        }, {
            Log.d("my-app", "Deu ruim")
            isLoading.value = false
        })
    }

    // factory para que seja possivel instanciar a view model passando parametros
//    class MainViewModelFactory (private val repository: ProductRepository) : ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//          return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
//              MainViewModel(this.repository) as T
//          } else {
//              throw IllegalArgumentException("ViewModel Not Found")
//          }
//      }
//  }
}
