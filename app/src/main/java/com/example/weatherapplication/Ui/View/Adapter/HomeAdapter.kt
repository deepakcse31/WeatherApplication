package com.example.weatherapplication.Ui.View.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.databinding.CarddesignBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    var name =ArrayList<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.HomeViewHolder {
        val v=CarddesignBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        name.add("Deepak")
        name.add("Teja")
        name.add("Deepak")
        name.add("Teja")
        name.add("Deepak")
        name.add("Teja")
        name.add("Deepak")
        name.add("Teja")
        name.add("Deepak")
        name.add("Teja")

        for ( i in 0..100)
        {
            v.textdata.setText("Deepak Kumar "+name.get(i))
            Log.e("All Data","All Data"+name.get(i))
        }
        return HomeViewHolder(v)
    }

    override fun onBindViewHolder(holder: HomeAdapter.HomeViewHolder, position: Int) {
        with(holder){
            with(name[position])
            {
                binding.textdata.setText(name.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return name.size
    }
    inner class HomeViewHolder(val binding : CarddesignBinding) : RecyclerView.ViewHolder(binding.root){

    }
}