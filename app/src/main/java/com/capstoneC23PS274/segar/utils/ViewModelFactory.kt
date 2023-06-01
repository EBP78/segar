package com.capstoneC23PS274.segar.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstoneC23PS274.segar.data.SegarRepository

class ViewModelFactory (private val segarRepository: SegarRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}