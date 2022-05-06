package com.example.weatherapplication.Ui.View.data.api

import com.example.weatherapplication.Ui.View.model.Weather
import com.example.weatherapplication.Ui.View.model.Weatherresponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getUser() : Response<Weatherresponse>
    suspend fun latlng(lat : Double,lng : Double): Response<Weatherresponse>
}