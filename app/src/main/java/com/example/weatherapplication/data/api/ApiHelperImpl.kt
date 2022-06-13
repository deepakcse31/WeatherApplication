package com.example.weatherapplication.data.api

import com.example.weatherapplication.model.Weatherresponse
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private var apiService: ApiService) : ApiHelper {
    override suspend fun getUser(): Response<Weatherresponse> = apiService.getUsers()
    override suspend fun latlng(lat : Double,lng : Double): Response<Weatherresponse> = apiService.latlng(lat, lng)
    override suspend fun datafind(jsonObject: JSONObject) {

    }

}