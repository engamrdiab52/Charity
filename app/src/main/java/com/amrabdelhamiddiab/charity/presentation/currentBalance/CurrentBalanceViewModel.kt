package com.amrabdelhamiddiab.charity.presentation.currentBalance

import android.app.Application
import com.amrabdelhamiddiab.charity.frameWork.CharityViewModel
import com.amrabdelhamiddiab.charity.frameWork.PreferenceManager

class CurrentBalanceViewModel(application: Application) : CharityViewModel(application) {
    private val preferencesHelper = PreferenceManager(application.applicationContext)

    val moneyTotalValue: Int get() = preferencesHelper.getMoneyValueTotal()
    val helpTotalValue: Int get() = preferencesHelper.getHelpValueTotal()
    val kindTotalValue: Int get() = preferencesHelper.getKindValueTotal()
    val prayTotalValue: Int get() = preferencesHelper.getPrayValueTotal()
    val smileTotalValue: Int get() = preferencesHelper.getSmileValueTotal()
    val shareTotalValue: Int get() = preferencesHelper.getShareValueTotal()

    fun setMoneyValueTotal(moneyValueTotal: Int) {
        preferencesHelper.setMoneyValueTotal(moneyValueTotal)
    }

    fun setHelpValueTotal(helpValueTotal: Int) {
        preferencesHelper.setHelpValueTotal(helpValueTotal)
    }

    fun setKindValueTotal(kindValueTotal: Int) {
        preferencesHelper.setKindValueTotal(kindValueTotal)
    }

    fun setPrayValueTotal(prayValueTotal: Int) {
        preferencesHelper.setPrayValueTotal(prayValueTotal)
    }

    fun setSmileValueTotal(smileValueTotal: Int) {
        preferencesHelper.setSmileValueTotal(smileValueTotal)
    }

    fun setShareValueTotal(shareValueTotal: Int) {
        preferencesHelper.setShareValueTotal(shareValueTotal)
    }
}