package com.example.less14imagesinternetzapyt

data class GetResponse (var temperature: String? = null, var wind: String? = null, var description: String? = null, var forecast: ArrayList<Forecast> = arrayListOf())

data class Forecast(var day: String, var temperature: String, var wind: String)