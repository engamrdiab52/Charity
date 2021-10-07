package com.amrabdelhamiddiab.charity

import android.app.Application
import com.amrabdelhamiddiab.charity.frameWork.CharityViewModel
import com.amrabdelhamiddiab.charity.frameWork.CharityViewModelFactory

class CharityApp : Application() {
    override fun onCreate() {
        super.onCreate()
        CharityViewModelFactory.inject(this)
    }
}