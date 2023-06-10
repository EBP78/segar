package com.capstoneC23PS274.segar.network

import android.content.Context
import android.net.ConnectivityManager

class NetworkObserver (private val context: Context) {
    val networkManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}