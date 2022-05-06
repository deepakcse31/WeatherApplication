package com.example.weatherapplication.Ui.View.Fragment

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.weatherapplication.R
import com.example.weatherapplication.Ui.View.Utils.Status
import com.example.weatherapplication.Ui.View.ViewModel.TamperatureViewModel
import com.example.weatherapplication.databinding.FragmentTamperatureBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.internal.Contexts

@AndroidEntryPoint
class TamperatureFragment : Fragment() {
    private var binding : FragmentTamperatureBinding?=null
    private val tamperatureViewModel : TamperatureViewModel by  viewModels()

    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentTamperatureBinding.inflate(layoutInflater)

        tamperatureViewModel.user.observe(viewLifecycleOwner,{
            when(it.status)
            {
                Status.SUCCESS->{
                    binding?.topProgress?.visibility=View.INVISIBLE
                    Log.e("Data--->","Data--->"+it.data?.visibility)
                    Log.e("Tamperature--->","Tamperature--->"+it.data?.weather?.get(0))
                    binding?.todaytamperature?.setText("Toaday Tamperature Of "+it.data?.name+" "+it.data?.main?.temp)
                    binding?.pressure?.setText("Pressure :- "+it?.data?.main?.pressure)
                    binding?.humidity?.setText("Humidity :- "+it?.data?.main?.humidity)
                    Log.e("weatherdata","weatherdata"+it?.data?.weather?.get(0)?.description)
                   binding?.description?.setText("Weather Behaviour :-"+it?.data?.weather?.get(0)?.description)
                    Log.e("working","workinh"+"working")
                }
                Status.ERROR->{
                    binding?.topProgress?.visibility=View.VISIBLE
                    Log.e("Data","data"+it.message)
                    Log.e("Eworking","Eworkinh"+"Eworking")
                }
                Status.LOADING->{
                    binding?.topProgress?.visibility=View.VISIBLE
                    Log.e("Data","data"+it)
                    Log.e("Lworking","Lworkinh"+"Lworking")

                }
            }
            //binding!!.todaytamperature.setText(it.toString())
        })
        return binding!!.root
    }


}