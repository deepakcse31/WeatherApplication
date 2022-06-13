package com.example.weatherapplication.Ui.View.Activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapplication.Network.NetworkConnectionLiveData
import com.example.weatherapplication.Utils.NetworkHelper
import com.example.weatherapplication.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper,
    private val networkStatusHelper: NetworkConnectionLiveData
) : ViewModel() {

    var networkresponse: LiveData<Boolean> = networkHelper._networkresponse

    init {

    }
}