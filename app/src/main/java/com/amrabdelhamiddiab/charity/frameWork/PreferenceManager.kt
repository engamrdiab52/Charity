package com.amrabdelhamiddiab.charity.frameWork

import android.content.Context
import android.content.SharedPreferences
import com.amrabdelhamiddiab.core.data.IPreferenceHelper

class PreferenceManager constructor(context: Context) : IPreferenceHelper {
    companion object {
        const val MONEY_VALUE = "moneyValue"
        const val HELP_VALUE = "helpValue"
        const val KIND_VALUE = "kindValue"
        const val PRAY_VALUE = "prayValue"
        const val SMILE_VALUE = "smileValue"
        const val SHARE_VALUE = "shareValue"

        const val MONEY_VALUE_MAX = "moneyValueMax"
        const val HELP_VALUE_MAX = "helpValueMax"
        const val KIND_VALUE_MAX = "kindValueMax"
        const val PRAY_VALUE_MAX = "prayValueMax"
        const val SMILE_VALUE_MAX = "smileValueMax"
        const val SHARE_VALUE_MAX = "shareValueMax"

        const val MONEY_VALUE_TOTAL = "moneyValueTotal"
        const val HELP_VALUE_TOTAL= "helpValueTotal"
        const val KIND_VALUE_TOTAL = "kindValueTotal"
        const val PRAY_VALUE_TOTAL = "prayValueTotal"
        const val SMILE_VALUE_TOTAL = "smileValueTotal"
        const val SHARE_VALUE_TOTAL = "shareValueTotal"

        const val CURRENT_TIME = "currentTime"
    }

    private val PREFS_NAME = "CharityPreferences"
    private var preferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override fun setMoneyValue(moneyValue: Int) {
        preferences[MONEY_VALUE] = moneyValue
    }

    override fun getMoneyValue(): Int {
        return when (val value: Int? = preferences[MONEY_VALUE]) {
            -1 -> 0
            null -> 0
            else -> value
        }
    }

    override fun setHelpValue(moneyValue: Int) {
        preferences[HELP_VALUE] = moneyValue
    }

    override fun getHelpValue(): Int {
        return when (val value: Int? = preferences[HELP_VALUE]) {
            -1 -> 0
            null -> 0
            else -> value
        }
    }

    override fun setKindValue(moneyValue: Int) {
        preferences[KIND_VALUE] = moneyValue
    }

    override fun getKindValue(): Int {
        return when (val value: Int? = preferences[KIND_VALUE]) {
            -1 -> 0
            null -> 0
            else -> value
        }
    }

    override fun setPrayValue(moneyValue: Int) {
        preferences[PRAY_VALUE] = moneyValue
    }

    override fun getPrayValue(): Int {
        return when (val value: Int? = preferences[PRAY_VALUE]) {
            -1 -> 0
            null -> 0
            else -> value
        }
    }

    override fun setSmileValue(moneyValue: Int) {
        preferences[SMILE_VALUE] = moneyValue
    }

    override fun getSmileValue(): Int {
        return when (val value: Int? = preferences[SMILE_VALUE]) {
            -1 -> 0
            null -> 0
            else -> value
        }
    }

    override fun setShareValue(moneyValue: Int) {
        preferences[SHARE_VALUE] = moneyValue
    }

    override fun getShareValue(): Int {
        return when (val value: Int? = preferences[SHARE_VALUE]) {
            -1 -> 0
            null -> 0
            else -> value
        }
    }

    ///////////////////////////
    override fun setMoneyValueMax(moneyValueMax: Int) {
        preferences[MONEY_VALUE_MAX] = moneyValueMax
    }

    override fun getMoneyValueMax(): Int {
        return preferences[MONEY_VALUE_MAX] ?: 0
    }

    override fun setHelpValueMax(moneyValueMax: Int) {
        preferences[HELP_VALUE_MAX] = moneyValueMax
    }

    override fun getHelpValueMax(): Int {
        return preferences[HELP_VALUE_MAX] ?: 0
    }

    override fun setKindValueMax(moneyValueMax: Int) {
        preferences[KIND_VALUE_MAX] = moneyValueMax
    }

    override fun getKindValueMax(): Int {
        return preferences[KIND_VALUE_MAX] ?: 0
    }

    override fun setPrayValueMax(moneyValueMax: Int) {
        preferences[PRAY_VALUE_MAX] = moneyValueMax
    }

    override fun getPrayValueMax(): Int {
        return preferences[PRAY_VALUE_MAX] ?: 0
    }

    override fun setSmileValueMax(moneyValueMax: Int) {
        preferences[SMILE_VALUE_MAX] = moneyValueMax
    }

    override fun getSmileValueMax(): Int {
        return preferences[SMILE_VALUE_MAX] ?: 0
    }

    override fun setShareValueMax(moneyValueMax: Int) {
        preferences[SHARE_VALUE_MAX] = moneyValueMax
    }

    override fun getShareValueMax(): Int {
        return preferences[SHARE_VALUE_MAX] ?: 0
    }

    override fun setMoneyValueTotal(moneyValueTotal: Int) {
        preferences[MONEY_VALUE_TOTAL] = moneyValueTotal
    }

    override fun getMoneyValueTotal(): Int {
        return preferences[MONEY_VALUE_TOTAL] ?: 0
    }

    override fun setHelpValueTotal(helpValueTotal: Int) {
        preferences[HELP_VALUE_TOTAL] = helpValueTotal
    }

    override fun getHelpValueTotal(): Int {
        return preferences[HELP_VALUE_TOTAL] ?: 0
    }

    override fun setKindValueTotal(kindValueTotal: Int) {
        preferences[KIND_VALUE_TOTAL] = kindValueTotal
    }

    override fun getKindValueTotal(): Int {
        return preferences[KIND_VALUE_TOTAL] ?: 0
    }

    override fun setPrayValueTotal(prayValueTotal: Int) {
        preferences[PRAY_VALUE_TOTAL] = prayValueTotal
    }

    override fun getPrayValueTotal(): Int {
        return preferences[PRAY_VALUE_TOTAL] ?: 0
    }

    override fun setSmileValueTotal(smileValueTotal: Int) {
        preferences[SMILE_VALUE_TOTAL] = smileValueTotal
    }

    override fun getSmileValueTotal(): Int {
        return preferences[SMILE_VALUE_TOTAL] ?: 0
    }

    override fun setCurrentTime(moneyValueMax: Long) {
        preferences[CURRENT_TIME] = moneyValueMax
    }

    override fun setShareValueTotal(moneyValueTotal: Int) {
        preferences[SHARE_VALUE_TOTAL] = moneyValueTotal
    }

    override fun getShareValueTotal(): Int {
        return preferences[SHARE_VALUE_TOTAL] ?: 0
    }

    override fun getPreviousSavedTime(): Long {
        return preferences[CURRENT_TIME] ?: 0L
    }

    override fun getSavedLanguageChoice(): String {
        return preferences[LocaleHelper.SELECTED_LANGUAGE] ?: ""
    }

    override fun clearPrefs() {
        preferences.edit().clear().apply()
    }


    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    private operator fun SharedPreferences.set(key: String, value: Any?) {
        when (value) {
            is String? -> edit { it.putString(key, value) }
            is Int -> edit { it.putInt(key, value) }
            is Boolean -> edit { it.putBoolean(key, value) }
            is Float -> edit { it.putFloat(key, value) }
            is Long -> edit { it.putLong(key, value) }
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    /**
     * finds value on given key.
     * [T] is the type of value
     * @param defaultValue optional default value - will take null for strings, false for bool and -1 for numeric values if [defaultValue] is not specified
     */
    private inline operator fun <reified T : Any> SharedPreferences.get(
        key: String,
        defaultValue: T? = null
    ): T? {
        return when (T::class) {
            String::class -> getString(key, defaultValue as? String) as T?
            Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
            Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
            Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
            Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }
}