package com.amrabdelhamiddiab.charity.frameWork

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.LocaleList
import java.util.*

/*
object LocaleHelper22 {


    val PREFS_NAME = "CharityPreferences"

    // the method is used to set the language at runtime
    fun setLocale(context: Context, language: String): Context {
        persist(context, language)
        // updating the language for devices above android nougat
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(context, language)
        } else
            updateResourcesLegacy(context, language)
        // for devices having lower version of android os
    }

    private fun persist(context: Context, language: String) {
        val preferences: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(SELECTED_LANGUAGE, language)
        editor.apply()
    }

    // the method is used update the language of application by creating
    // object of inbuilt Locale class and passing language argument to it
    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, language: String): Context {
        val localeToSwitchTo = Locale(language)
        Locale.setDefault(localeToSwitchTo)
        val configuration = context.resources.configuration
        configuration.setLocale(localeToSwitchTo)
        context.createConfigurationContext(configuration)
        return context
    }


    @SuppressLint("ObsoleteSdkInt")
    private fun updateResourcesLegacy(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = resources.configuration
        //  configuration.setLocale(locale)
        configuration.locale = locale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale)
        }
        resources.updateConfiguration(configuration, resources.displayMetrics)
        // context.createConfigurationContext(configuration)
        return context

    }
}*/
