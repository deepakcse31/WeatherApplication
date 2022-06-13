package com.example.weatherapplication.Utils

import android.content.Context
import android.view.View
import android.widget.Toast

object Toastdata {
    fun toastdat(context: Context,data : String){
        Toast.makeText(context,data,Toast.LENGTH_LONG).show()
    }

    fun View.show()
    {
        this.visibility=View.VISIBLE;
    }
    fun Toast.toast(context: Context,msg : String){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show()
    }
}