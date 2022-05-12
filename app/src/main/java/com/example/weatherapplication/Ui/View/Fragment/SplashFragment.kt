package com.example.weatherapplication.Ui.View.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {
    private var binding : FragmentSplashBinding?=null
    val tagname : String = "SplashFragment"
      val args : SplashFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSplashBinding.inflate(layoutInflater)
        Log.e("Data","Data"+args.name)
        binding!!.textdata.setOnClickListener {
            findNavController().navigate(R.id.action_splashfragment_to_tempereaturefragment)
        }
        Log.e(tagname,tagname)
        return binding?.root
    }

}