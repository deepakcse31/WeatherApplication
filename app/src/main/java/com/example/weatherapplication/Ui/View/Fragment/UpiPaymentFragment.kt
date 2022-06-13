package com.example.weatherapplication.Ui.View.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.FragmentUpiPaymentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpiPaymentFragment : Fragment() {
    var binding : FragmentUpiPaymentBinding?=null
   // var easyupiPayment : EasyUpiPayment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentUpiPaymentBinding.inflate(layoutInflater)

        return binding?.root
    }
    fun Context.loadJSONFromAssets(fileName: String): String {
        return applicationContext.assets.open(fileName).bufferedReader().use { reader ->
            reader.readText()
        }
    }
}