package com.example.weatherapplication.data.api

import com.example.weatherapplication.model.Weatherresponse
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("weather?lat=12.9716&lon=77.5946&appid=ff997cdc6de804f30a977a1a9f7c5870")
    suspend fun getUsers(): Response<Weatherresponse>

    @GET("weather?appid=ff997cdc6de804f30a977a1a9f7c5870")
    suspend fun latlng(@Query("lat") lat : Double,@Query("lng") lng :Double) : Response<Weatherresponse>

    @POST("weather?lat=12.9716&lon=77.5946&appid=ff997cdc6de804f30a977a1a9f7c5870")
    suspend fun datafind(@Body jsonObject: JSONObject)
}