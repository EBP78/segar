package com.capstoneC23PS274.segar.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstoneC23PS274.segar.data.SegarRepository
import com.capstoneC23PS274.segar.ui.screen.dictionary.DictionaryViewmodel
import com.capstoneC23PS274.segar.ui.screen.login.LoginViewmodel

class ViewModelFactory (private val segarRepository: SegarRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewmodel::class.java)){
            return LoginViewmodel(segarRepository) as T
        } else if (modelClass.isAssignableFrom(DictionaryViewmodel::class.java)){
            return DictionaryViewmodel(segarRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}