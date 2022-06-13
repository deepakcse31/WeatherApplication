package com.example.weatherapplication.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.Utils.NetworkHelper
import com.example.weatherapplication.Utils.Resource
import com.example.weatherapplication.data.repository.MainRepository
import com.example.weatherapplication.model.Weatherresponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

//constructor injection
@HiltViewModel

//Scoped function-> let,run,with,also,apply
class TamperatureViewModel @Inject  constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {
    private val _users = MutableLiveData<Resource<Weatherresponse>>()

     val user : LiveData<Resource<Weatherresponse>> get() = _users

    init {
       // logindata()
    }

     fun fetchweather(lat : Double, lng :Double) {
         viewModelScope.launch {
             try {
                 coroutineScope {
                     _users.postValue(Resource.loading(null))
                     if (networkHelper.isNetworkConnected()) {
                         //parallel APi Call in android
                         Log.e("Networkconnected", "NetworkConnected" + "Networkconnected")
                         val responsedata =async {mainRepository.latlng(lat, lng).let {
                             if (it.isSuccessful) {
                                 Log.e("body->", "body->" + Resource.success(it.body()))
                                 _users.postValue(Resource.success(it.body()))
                             } else {
                                 _users.postValue(Resource.error(it.errorBody().toString(), null))
                             }
                         }
                         }
                         val responseone =async { mainRepository.getUsers() }
                         val responsedata1=responsedata.await()
                         val responsedataá=responseone.await()

                     } else {
                         Log.e("Networkdisconnected", "NetworkdisConnected" + "Networkcdisonnected")
                         _users.postValue(Resource.error("No Internet Connection", null))

                     }

                 }
             } catch (e: Exception) {

             }

         }




         fun fetchweaterresponse() {
             viewModelScope.launch {
                 try {
                     coroutineScope {
                         _users.postValue(Resource.loading(null))
                         if (networkHelper.isNetworkConnected()) {
                             Log.e("Networkconnected", "NetworkConnected" + "Networkconnected")
                             mainRepository.getUsers().let {

                                 //Log.e("DataResponse-->","DataResponse-->"+it.body())
                                 // Log.e("ResourceValue-->","ResourceValue-->"+Resource)

                                 if (it.isSuccessful) {
                                     Log.e("body->", "body->" + Resource.success(it.body()))
                                     _users.postValue(Resource.success(it.body()))
                                 } else {
                                     _users.postValue(
                                         Resource.error(
                                             it.errorBody().toString(),
                                             null
                                         )
                                     )
                                 }
                             }
                         } else {
                             Log.e(
                                 "Networkdisconnected",
                                 "NetworkdisConnected" + "Networkcdisonnected"
                             )
                             _users.postValue(Resource.error("No Internet Connection", null))

                         }

                     }
                 } catch (e: Exception) {

                 }

             }
         }
     }
    fun logindata(){
        viewModelScope.launch {
            var payerReg = JSONObject()
            payerReg.put("driver_number", "deepak")
            payerReg.put("verification_code","1234")
            mainRepository.datafind(payerReg).let {
                Log.e("Data--->","Data--->"+it)
            }
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
   // }
