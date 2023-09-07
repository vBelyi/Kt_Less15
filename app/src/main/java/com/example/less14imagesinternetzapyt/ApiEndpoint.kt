package com.example.less14imagesinternetzapyt

import retrofit2.Call
import retrofit2.http.GET


interface ApiEndpoint {

    @GET("/weather/London")
    fun getCity():Call<GetResponse>

}