package com.example.weatherapplication.Ui.View.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapplication.Utils.MyLifecycleObserver
import com.example.weatherapplication.databinding.FragmentCameraBinding

class CameraFragment : Fragment() {
    private var binding : FragmentCameraBinding?=null
    lateinit var observer : MyLifecycleObserver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observer = MyLifecycleObserver(requireActivity().activityResultRegistry)
        lifecycle.addObserver(observer)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentCameraBinding.inflate(layoutInflater)
        binding?.tvblankfragment?.setOnClickListener {
            observer.selectImage()
        }

        return binding?.root
    }
}