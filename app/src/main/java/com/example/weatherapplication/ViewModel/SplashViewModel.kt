package com.example.weatherapplication.ViewModel

import androidx.lifecycle.ViewModel
import com.example.weatherapplication.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel@Inject constructor(
    private val mainRepository: MainRepository,
)  : ViewModel() {

}