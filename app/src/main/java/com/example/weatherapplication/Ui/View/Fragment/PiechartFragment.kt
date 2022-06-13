package com.example.weatherapplication.Ui.View.Fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapplication.ViewModel.PiechartViewModel
import com.example.weatherapplication.databinding.PiechartFragmentBinding

class PiechartFragment : Fragment() {

    private lateinit var viewModel: PiechartViewModel
    var binding : PiechartFragmentBinding?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= PiechartFragmentBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PiechartViewModel::class.java)
    }

}