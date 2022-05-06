package com.example.weatherapplication.Ui.View.Network

sealed class NetworkStatus{
    object Available : NetworkStatus()
    object Unavailable : NetworkStatus()
}
