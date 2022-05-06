package com.example.weatherapplication.Ui.View.di

import android.content.Context
import com.example.weatherapplication.BuildConfig
import com.example.weatherapplication.Ui.View.Utils.NetworkHelper
import com.example.weatherapplication.Ui.View.data.api.ApiHelper
import com.example.weatherapplication.Ui.View.data.api.ApiHelperImpl
import com.example.weatherapplication.Ui.View.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
//
@Module
@InstallIn(SingletonComponent::class)//we are using it on application label
class ApplicationModule {


    @Provides
    fun provideBaseUrl() = "https://api.openweathermap.org/data/2.5/"

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext appContext: Context):NetworkHelper {
        return NetworkHelper(appContext)
    }

}