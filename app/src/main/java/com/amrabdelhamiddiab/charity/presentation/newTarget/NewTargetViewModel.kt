package com.amrabdelhamiddiab.charity.presentation.newTarget

import android.app.Application
import com.amrabdelhamiddiab.charity.frameWork.CharityViewModel
import com.amrabdelhamiddiab.charity.frameWork.PreferenceManager

class NewTargetViewModel(application: Application) : CharityViewModel(application) {
    private val preferencesHelper = PreferenceManager(application.applicationContext)
    val moneyCurrentValue: Int get() = preferencesHelper.getMoneyValue()
    val helpCurrentValue: Int get() = preferencesHelper.getHelpValue()
    val kindCurrentValue: Int get() = preferencesHelper.getKindValue()
    val prayCurrentValue: Int get() = preferencesHelper.getPrayValue()
    val smileCurrentValue: Int get() = preferencesHelper.getSmileValue()
    val shareCurrentValue: Int get() = preferencesHelper.getShareValue()

    val moneyValueMax: Int get() = preferencesHelper.getMoneyValueMax()
    val helpValueMax: Int get() = preferencesHelper.getHelpValueMax()
    val kindValueMax: Int get() = preferencesHelper.getKindValueMax()
    val prayValueMax: Int get() = preferencesHelper.getPrayValueMax()
    val smileValueMax: Int get() = preferencesHelper.getSmileValueMax()
    val shareValueMax: Int get() = preferencesHelper.getShareValueMax()

    val savedTime: Long get() = preferencesHelper.getPreviousSavedTime()

    fun saveTime(currentTime: Long){
        preferencesHelper.setCurrentTime(currentTime)
    }

    fun setMoneyValue(moneyValueMax: Int) {
        preferencesHelper.setMoneyValueMax(moneyValueMax)
    }

    fun setHelpValue(helpValueMax: Int) {
        preferencesHelper.setHelpValueMax(helpValueMax)
    }

    fun setKindValue(kindValueMax: Int) {
        preferencesHelper.setKindValueMax(kindValueMax)
    }

    fun setPrayValue(prayValueMax: Int) {
        preferencesHelper.setPrayValueMax(prayValueMax)
    }

    fun setSmileValue(smileValueMax: Int) {
        preferencesHelper.setSmileValueMax(smileValueMax)
    }

    fun setShareValue(shareValueMax: Int) {
        preferencesHelper.setShareValueMax(shareValueMax)
    }

}