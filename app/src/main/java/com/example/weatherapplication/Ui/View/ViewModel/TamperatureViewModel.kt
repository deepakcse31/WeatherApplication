package com.example.weatherapplication.Ui.View.ViewModel

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
import android.widget.Toast
import androidx.constraintlayout.motion.widget.Debug.getLocation
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.Ui.View.Utils.NetworkHelper
import com.example.weatherapplication.Ui.View.Utils.Resource
import com.example.weatherapplication.Ui.View.data.repository.MainRepository
import com.example.weatherapplication.Ui.View.model.Weatherresponse
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

//constructor injection
@HiltViewModel
class TamperatureViewModel @Inject  constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {
    private val _users = MutableLiveData<Resource<Weatherresponse>>()

     val user : LiveData<Resource<Weatherresponse>> get() = _users

    init {


        //fetchweather()
    }
     fun fetchweather(lat : Double, lng :Double) {
        viewModelScope.launch {
            try {
               coroutineScope {
                   _users.postValue(Resource.loading(null))
                   if (networkHelper.isNetworkConnected())
                   {
                       Log.e("Networkconnected","NetworkConnected"+"Networkconnected")
                       mainRepository.latlng(lat,lng).let {

                           //Log.e("DataResponse-->","DataResponse-->"+it.body())
                           // Log.e("ResourceValue-->","ResourceValue-->"+Resource)

                           if (it.isSuccessful) {
                               Log.e("body->","body->"+Resource.success(it.body()))
                               _users.postValue(Resource.success(it.body()))
                           }else{
                               _users.postValue(Resource.error(it.errorBody().toString(),null))
                           }
                       }
                   }
                   else
                   {
                       Log.e("Networkdisconnected","NetworkdisConnected"+"Networkcdisonnected")
                       _users.postValue(Resource.error("No Internet Connection",null))

                   }

               }
            }
            catch (e : Exception)
            {

            }

        }

            /*   mainRepository.latlng(12.9716,77.5946).let {

            Log.e("itdata","itdata"+it.body())

            if (it.isSuccessful) {
                        Log.e("body->","body->"+Resource.success(it.body()))
                        _users.postValue(Resource.success(it.body()))
                    }else{
                        _users.postValue(Resource.error(it.errorBody().toString(),null))
                    }



        }

      */


//            _users.postValue(Resource.loading(null))
//            if (networkHelper.isNetworkConnected())
//            {
//                Log.e("Networkconnected","NetworkConnected"+"Networkconnected")
//                mainRepository.getUsers().let {
//
//                    //Log.e("DataResponse-->","DataResponse-->"+it.body())
//                   // Log.e("ResourceValue-->","ResourceValue-->"+Resource)
//
//                    if (it.isSuccessful) {
//                        Log.e("body->","body->"+Resource.success(it.body()))
//                        _users.postValue(Resource.success(it.body()))
//                    }else{
//                        _users.postValue(Resource.error(it.errorBody().toString(),null))
//                    }
//                }
//            }
//            else
//            {
//                Log.e("Networkdisconnected","NetworkdisConnected"+"Networkcdisonnected")
//                _users.postValue(Resource.error("No Internet Connection",null))
//
//            }
//
//        }
        }
    }
