package com.capstoneC23PS274.segar.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstoneC23PS274.segar.data.SegarRepository
import com.capstoneC23PS274.segar.ui.screen.camera.CameraViewmodel
import com.capstoneC23PS274.segar.ui.screen.dictdetail.DictDetailViewmodel
import com.capstoneC23PS274.segar.ui.screen.dictionary.DictionaryViewmodel
import com.capstoneC23PS274.segar.ui.screen.history.HistoryViewmodel
import com.capstoneC23PS274.segar.ui.screen.login.LoginViewmodel
import com.capstoneC23PS274.segar.ui.screen.profile.ProfileViewmodel
import com.capstoneC23PS274.segar.ui.screen.register.RegisterViewmodel
import com.capstoneC23PS274.segar.ui.screen.result.ResultViewmodel
import com.capstoneC23PS274.segar.ui.screen.splash.SplashViewmodel

class ViewModelFactory (private val segarRepository: SegarRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewmodel::class.java)){
            return LoginViewmodel(segarRepository) as T
        } else if (modelClass.isAssignableFrom(RegisterViewmodel::class.java)){
            return RegisterViewmodel(segarRepository) as T
        } else if (modelClass.isAssignableFrom(DictionaryViewmodel::class.java)){
            return DictionaryViewmodel(segarRepository) as T
        } else if (modelClass.isAssignableFrom(DictDetailViewmodel::class.java)){
            return DictDetailViewmodel(segarRepository) as T
        } else if (modelClass.isAssignableFrom(CameraViewmodel::class.java)){
            return CameraViewmodel(segarRepository) as T
        } else if (modelClass.isAssignableFrom(HistoryViewmodel::class.java)){
            return HistoryViewmodel(segarRepository) as T
        } else if (modelClass.isAssignableFrom(ProfileViewmodel::class.java)){
            return ProfileViewmodel(segarRepository) as T
        } else if (modelClass.isAssignableFrom(SplashViewmodel::class.java)){
            return SplashViewmodel(segarRepository) as T
        } else if (modelClass.isAssignableFrom(ResultViewmodel::class.java)){
            return ResultViewmodel(segarRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}