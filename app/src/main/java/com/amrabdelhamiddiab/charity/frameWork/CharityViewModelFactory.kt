package com.amrabdelhamiddiab.charity.frameWork

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object CharityViewModelFactory : ViewModelProvider.Factory {
    lateinit var application: Application

    fun inject(application: Application) {
        CharityViewModelFactory.application = application

    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (CharityViewModel::class.java.isAssignableFrom(modelClass)) {
            return modelClass.getConstructor(Application::class.java).newInstance(application)
        }else{
            throw IllegalStateException("viewModel must extend BagsViewModel")
        }
    }
}