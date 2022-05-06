package com.example.weatherapplication.Ui.View.data.api

import com.example.weatherapplication.Ui.View.model.Weather
import com.example.weatherapplication.Ui.View.model.Weatherresponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private var apiService: ApiService) : ApiHelper {
    override suspend fun getUser(): Response<Weatherresponse> = apiService.getUsers()
    override suspend fun latlng(lat : Double,lng : Double): Response<Weatherresponse> = apiService.latlng(lat, lng)

}