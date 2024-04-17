package com.exemple.myapplication.extras.respositories

import android.util.Log
import com.exemple.myapplication.Product
import com.exemple.myapplication.extras.rest.ProductApiResponseDTO
import com.exemple.myapplication.extras.rest.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// repository para expor a busca de produtos
class ProductRepository {
    private val retrofitService = RetrofitService.getInstance()

    fun getAllProducts(success: (List<Product>) -> Unit, failure: () -> Unit) {
        val request = retrofitService.getAllProducts()
        request.enqueue(object : Callback<ProductApiResponseDTO> {
            // method on response is success
            override fun onResponse(
                call: Call<ProductApiResponseDTO>,
                response: Response<ProductApiResponseDTO>
            ) {
                val productsResponse = response.body()
                if (productsResponse != null) {
                    success(productsResponse.products)
                } else {
                    failure()
                }
            }

            // method on response is failure
            override fun onFailure(call: Call<ProductApiResponseDTO>, t: Throwable) {
                failure()
            }
        })
    }
}