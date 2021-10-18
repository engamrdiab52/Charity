package com.amrabdelhamiddiab.core.data

interface IPreferenceHelper {
    fun setMoneyValue(moneyValue: Int)
    fun getMoneyValue(): Int

    fun setHelpValue(moneyValue: Int)
    fun getHelpValue(): Int

    fun setKindValue(moneyValue: Int)
    fun getKindValue(): Int

    fun setPrayValue(moneyValue: Int)
    fun getPrayValue(): Int

    fun setSmileValue(moneyValue: Int)
    fun getSmileValue(): Int

    fun setShareValue(moneyValue: Int)
    fun getShareValue(): Int

    ////////////////////////////////////////
    fun setMoneyValueMax(moneyValueMax: Int)
    fun getMoneyValueMax(): Int

    fun setHelpValueMax(moneyValueMax: Int)
    fun getHelpValueMax(): Int

    fun setKindValueMax(moneyValueMax: Int)
    fun getKindValueMax(): Int

    fun setPrayValueMax(moneyValueMax: Int)
    fun getPrayValueMax(): Int

    fun setSmileValueMax(moneyValueMax: Int)
    fun getSmileValueMax(): Int

    fun setShareValueMax(moneyValueMax: Int)
    fun getShareValueMax(): Int

    fun setMoneyValueTotal(moneyValueTotal: Int)
    fun getMoneyValueTotal(): Int

    fun setHelpValueTotal(helpValueTotal: Int)
    fun getHelpValueTotal(): Int

    fun setKindValueTotal(kindValueTotal: Int)
    fun getKindValueTotal(): Int

    fun setPrayValueTotal(prayValueTotal: Int)
    fun getPrayValueTotal(): Int

    fun setSmileValueTotal(smileValueTotal: Int)
    fun getSmileValueTotal(): Int

    fun setShareValueTotal(moneyValueTotal: Int)
    fun getShareValueTotal(): Int

    fun setCurrentTime(moneyValueMax: Long)
    fun getPreviousSavedTime(): Long

    fun getSavedLanguageChoice(): String

    fun setSavedRemindersChoice(repeatsCount:Long)
    fun getSavedRemindersChoice(): Long

    fun setNighMode(nighModeOn: Boolean)
    fun getNightMode(): Boolean

    fun clearPrefs()
}