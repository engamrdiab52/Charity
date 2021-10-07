package com.amrabdelhamiddiab.charity.frameWork

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.amrabdelhamiddiab.charity.CharityApp

open class CharityViewModel(application: Application) : AndroidViewModel(application) {
    protected val application: CharityApp = this.getApplication()
}