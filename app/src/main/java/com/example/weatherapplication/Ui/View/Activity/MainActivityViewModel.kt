package com.example.weatherapplication.Ui.View.Activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.Ui.View.Network.NetworkConnectionLiveData
import com.example.weatherapplication.Ui.View.Utils.NetworkHelper
import com.example.weatherapplication.Ui.View.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

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