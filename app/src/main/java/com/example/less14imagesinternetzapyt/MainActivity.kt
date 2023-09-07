package com.example.less14imagesinternetzapyt


import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity: Activity() {

    private lateinit var textViewTemperature: TextView
    private lateinit var textViewWind: TextView
    private lateinit var textViewDescription: TextView
    private lateinit var requestButton: Button
    private lateinit var forecastTextViewTemperature: TextView
    private lateinit var forecastTextViewWind: TextView
    private lateinit var forecastTwoTextViewTemperature: TextView
    private lateinit var forecastTwoTextViewWind: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        textViewTemperature = findViewById(R.id.temperatureView)
        textViewWind = findViewById(R.id.windView)
        textViewDescription = findViewById(R.id.descriptionView)

        forecastTextViewTemperature = findViewById(R.id.forecastOneTemperatureView)
        forecastTextViewWind = findViewById(R.id.forecastWindView)

        forecastTwoTextViewTemperature = findViewById(R.id.forecastTwoTemperatureView)
        forecastTwoTextViewWind = findViewById(R.id.forecastWindViewTwo)

        requestButton = findViewById(R.id.button)
        requestButton.setOnClickListener {
            val client = ApiClient.client.create(ApiEndpoint::class.java)
            client.getCity().enqueue(object : retrofit2.Callback<GetResponse> {
                override fun onResponse(call: retrofit2.Call<GetResponse>, response: retrofit2.Response<GetResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()

                        val temperature = data?.temperature
                        textViewTemperature.text = temperature

                        val wind = data?.wind
                        textViewWind.text = wind

                        val description = data?.description
                        textViewDescription.text = description

                        if (data != null) {
                            val forecastList = data.forecast
                            if (forecastList.isNotEmpty()) {
                                val firstForecast = forecastList[0]
                                val forecastTemperature = firstForecast.temperature
                                val forecastWind = firstForecast.wind
                                forecastTextViewWind.text = forecastWind
                                forecastTextViewTemperature.text = forecastTemperature
                            }
                        }

                        if (data != null) {
                            val forecastList = data.forecast
                            if (forecastList.isNotEmpty()) {
                                val firstForecast = forecastList[1]
                                val forecastTemperature = firstForecast.temperature
                                val forecastWind = firstForecast.wind
                                forecastTwoTextViewWind.text = forecastWind
                                forecastTwoTextViewTemperature.text = forecastTemperature
                            }
                        }


                    }
                }

                override fun onFailure(call: retrofit2.Call<GetResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "ERRORE!", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}