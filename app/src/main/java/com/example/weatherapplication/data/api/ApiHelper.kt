package com.example.weatherapplication.data.api

import com.example.weatherapplication.model.Weatherresponse
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body

interface ApiHelper {
    suspend fun getUser() : Response<Weatherresponse>
    suspend fun latlng(lat : Double,lng : Double): Response<Weatherresponse>
    suspend fun datafind(@Body jsonObject: JSONObject)
}