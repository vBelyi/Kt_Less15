package com.example.less14

//інтернет запит без використання для цього сторонніх бібліотек
import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Inet4Address
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import kotlin.concurrent.thread


class MainActivity: Activity() {

    lateinit var textContext: TextView
    lateinit var requestButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        textContext = findViewById(R.id.textView)
        requestButton = findViewById(R.id.button)
        requestButton.setOnClickListener { request("https://example.com") }
    }

    private fun request(webAddress: String){
        thread {
            val handler = Handler(Looper.getMainLooper())
            val urlConnection: HttpsURLConnection? = null
            try{
                val url = URL(webAddress)
                val urlConnection = url.openConnection() as HttpsURLConnection
                val code = urlConnection.responseCode
                if (code == 200) {
                    //патерн wrapper - обгортка - приймаэмо дані у вигляді за домогою InputStreamReader
                    //перепаковуэмо дані і показуємо за домогою BufferedReader
                    val inputStreamReader = InputStreamReader(urlConnection.inputStream)
                    val bufferedReader = BufferedReader(inputStreamReader)
                    var line = ""
                    while (bufferedReader.readLine() != null) {
                        line += bufferedReader.readLine()
                    }
                handler.post { textContext.text = line }
                }
            } catch (e: Exception) {


            } finally {
                urlConnection?.disconnect()
            }
        }

    }

}