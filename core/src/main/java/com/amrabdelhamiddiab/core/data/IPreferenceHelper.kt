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

    fun setCurrentTime(moneyValueMax: Long)
    fun getPreviousSavedTime(): Long

    fun clearPrefs()
}