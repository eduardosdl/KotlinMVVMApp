package com.exemple.myapplication.extras.rest

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// serço que faz a request na web
interface RetrofitService {
    @GET("products")
    fun getAllProducts(): Call<ProductApiResponseDTO>

    companion object {
        private val retrofitService: RetrofitService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .addConverterFactory(GsonConverterFactory.create()).
                build()
            retrofit.create(RetrofitService::class.java)
        }

        fun getInstance(): RetrofitService {
            return retrofitService
        }
    }
}