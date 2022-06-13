package com.example.weatherapplication.data.repository

import com.example.weatherapplication.data.api.ApiHelper
import org.json.JSONObject
import retrofit2.http.Body
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getUsers() =  apiHelper.getUser()
    suspend fun latlng(lat : Double,lng : Double) =  apiHelper.getUser()
    suspend fun datafind(@Body jsonObject: JSONObject){

    }
}