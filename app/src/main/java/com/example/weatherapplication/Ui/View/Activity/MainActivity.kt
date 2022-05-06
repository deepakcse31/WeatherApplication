package com.example.weatherapplication.Ui.View.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import com.example.weatherapplication.R
import com.example.weatherapplication.Ui.View.Network.NetworkConnectionLiveData
import com.example.weatherapplication.Ui.View.Network.NetworkStatus
import com.example.weatherapplication.Ui.View.Utils.NetworkHelper
import com.example.weatherapplication.Ui.View.data.repository.MainRepository
import com.example.weatherapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint//communicate with dragger(if we are binding fragment with @AndroidEntryPoint and activity must be bind with android entrypoint
// )
class MainActivity : AppCompatActivity() {
    //make a field for dragger

    //this is field injection
//    @Inject
//    lateinit var networkHelper : NetworkHelper
    private var binding : ActivityMainBinding?=null
    private val mainActivityViewModel : MainActivityViewModel by viewModels()
    @Inject
    lateinit var networkConnectionLiveData: NetworkConnectionLiveData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        installSplashScreen()
        setContentView(binding!!.root)


        networkConnectionLiveData.observe(this,{
            Log.e("network123->", "network123->" + it)

        })

        mainActivityViewModel.networkresponse.observe(this) {
            Log.e("Connecteddata", "Connecteddata" + it)
        }

    }
}