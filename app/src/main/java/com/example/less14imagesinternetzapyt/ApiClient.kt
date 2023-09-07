package com.example.less14imagesinternetzapyt

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//патерн синглтон
object ApiClient {
    val client = Retrofit.Builder()
        .baseUrl("https://goweather.herokuapp.com")
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}