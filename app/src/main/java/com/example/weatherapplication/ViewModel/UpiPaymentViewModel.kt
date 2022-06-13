package com.example.weatherapplication.ViewModel

import androidx.lifecycle.ViewModel
import com.example.weatherapplication.Utils.NetworkHelper
import com.example.weatherapplication.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpiPaymentViewModel@Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
)  : ViewModel() {

}