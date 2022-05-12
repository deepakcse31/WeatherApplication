package com.example.weatherapplication.Ui.View.Fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.weatherapplication.R
import com.example.weatherapplication.Ui.View.Utils.Status
import com.example.weatherapplication.Ui.View.ViewModel.TamperatureViewModel
import com.example.weatherapplication.databinding.FragmentTamperatureBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.internal.Contexts
import hilt_aggregated_deps._com_example_weatherapplication_Ui_View_Fragment_SplashFragment_GeneratedInjector

@AndroidEntryPoint
class TamperatureFragment : Fragment() {
    private var binding : FragmentTamperatureBinding?=null
    private val tamperatureViewModel : TamperatureViewModel by  viewModels()

    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2


    private var fusedLocationClient: FusedLocationProviderClient? = null
    private var lastLocation: Location? = null
    private var latitudeLabel: String? = null
    private var longitudeLabel: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for context fragment
        binding= FragmentTamperatureBinding.inflate(layoutInflater)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        binding!!.swiperefresh.setOnRefreshListener {
            tamperatureViewModel.fetchweather((lastLocation)!!.latitude,(lastLocation)!!.longitude)
            binding!!.swiperefresh.setRefreshing(false);
        }
        binding?.todaytamperature?.setOnClickListener {
            val action =TamperatureFragmentDirections.actionTempereaturefragmentToSplashfragment("deepak")
            findNavController().navigate(action)
        }
        tamperatureViewModel.user.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding?.topProgress?.visibility = View.INVISIBLE
                    Log.e("Data--->", "Data--->" + it.data?.visibility)
                    Log.e("Tamperature--->", "Tamperature--->" + it.data?.weather?.get(0))
                    binding?.todaytamperature?.setText("Toaday Tamperature Of " + it.data?.name + " " + it.data?.main?.temp)
                    binding?.pressure?.setText("Pressure :- " + it?.data?.main?.pressure)
                    binding?.humidity?.setText("Humidity :- " + it?.data?.main?.humidity)
                    Log.e("weatherdata", "weatherdata" + it?.data?.weather?.get(0)?.description)
                    binding?.description?.setText("Weather Behaviour :-" + it?.data?.weather?.get(0)?.description)
                    Log.e("working", "workinh" + "working")
                }
                Status.ERROR -> {
                    binding?.topProgress?.visibility = View.VISIBLE
                    Log.e("Data", "data" + it.message)
                    Log.e("Eworking", "Eworkinh" + "Eworking")
                }
                Status.LOADING -> {
                    binding?.topProgress?.visibility = View.VISIBLE
                    Log.e("Data", "data" + it)
                    Log.e("Lworking", "Lworkinh" + "Lworking")

                }
            }
            //binding!!.todaytamperature.setText(it.toString())
        }
        return binding!!.root
    }
    override fun onStart() {
        super.onStart()
        if (!checkPermissions()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions()
            }
        }
        else {
            getLastLocation()
        }
    }
    private fun getLastLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient?.lastLocation!!.addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful && task.result != null) {
                try {

                    lastLocation = task.result
                    Log.e("Lcoation-->","Location-->"+lastLocation)
                }
                catch (e:Exception)
                {
                  Log.e("Exception","Exception"+e)
                }
                tamperatureViewModel.fetchweather((lastLocation)!!.latitude,(lastLocation)!!.longitude)
                //latitudeText!!.text = latitudeLabel + ": " + (lastLocation)!!.latitude
                // longitudeText!!.text = longitudeLabel + ": " + (lastLocation)!!.longitude
            }
            else {
                Log.w(TAG, "getLastLocation:exception", task.exception)
                //showMessage("No location detected. Make sure location is enabled on the device.")
            }
        }
    }

    private fun showSnackbar(
        mainTextStringId: String, actionStringId: String,
        listener: View.OnClickListener
    ) {
        Toast.makeText(context, mainTextStringId, Toast.LENGTH_LONG).show()
    }
    private fun checkPermissions(): Boolean {
        val permissionState = ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        return permissionState == PackageManager.PERMISSION_GRANTED
    }
    private fun startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
            REQUEST_PERMISSIONS_REQUEST_CODE
        )
    }
    private fun requestPermissions() {
        val shouldProvideRationale = ActivityCompat.shouldShowRequestPermissionRationale(
            requireActivity(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (shouldProvideRationale) {
            Log.i(TAG, "Displaying permission rationale to provide additional context.")
            showSnackbar("Location permission is needed for core functionality", "Okay",
                View.OnClickListener {
                    startLocationPermissionRequest()
                })
        }
        else {
            Log.i(TAG, "Requesting permission")
            startLocationPermissionRequest()
        }
    }
    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        Log.i(TAG, "onRequestPermissionResult")
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            when {
                grantResults.isEmpty() -> {
                    // If user interaction was interrupted, the permission request is cancelled and you
                    // receive empty arrays.
                    Log.i(TAG, "User interaction was cancelled.")
                }
                grantResults[0] == PackageManager.PERMISSION_GRANTED -> {
                    // Permission granted.
                    getLastLocation()
                }
                else -> {
                    showSnackbar("Permission was denied", "Settings",
                        View.OnClickListener {
                            // Build intent that displays the App settings screen.
                            val intent = Intent()
                            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            val uri = Uri.fromParts(
                                "package",
                                Build.DISPLAY, null
                            )
                            intent.data = uri
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }
    companion object {
        private val TAG = "LocationProvider"
        private val REQUEST_PERMISSIONS_REQUEST_CODE = 34
    }

}