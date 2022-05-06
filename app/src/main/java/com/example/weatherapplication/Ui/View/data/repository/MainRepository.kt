package com.example.weatherapplication.Ui.View.data.repository

import com.example.weatherapplication.Ui.View.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getUsers() =  apiHelper.getUser()
    suspend fun latlng(lat : Double,lng : Double) =  apiHelper.getUser()

}