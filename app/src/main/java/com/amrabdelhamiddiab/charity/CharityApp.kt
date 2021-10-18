package com.amrabdelhamiddiab.charity

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.amrabdelhamiddiab.charity.frameWork.CharityViewModelFactory
import com.amrabdelhamiddiab.charity.frameWork.LocaleHelper.onAttach
import com.amrabdelhamiddiab.core.domain.Constants.CHANNEL_ID_PERIOD_WORK

class CharityApp : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base?.let { onAttach(it, "en") })
    }

    override fun onCreate() {
        super.onCreate()
        CharityViewModelFactory.inject(this)
        createNotificationChannel()
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
        /*    val channelInstant =
                NotificationChannel(CHANNEL_ID_ONE_TIME_WORK, "One Time Work Request", importance)
            channelInstant.description = "One Time Work"*/

            val channelPeriodic = NotificationChannel(CHANNEL_ID_PERIOD_WORK, "Period Work Request", importance)
            channelPeriodic.description = "Periodic Work"

            val notificationManager = applicationContext.getSystemService(
                NotificationManager::class.java
            )
            notificationManager!!.createNotificationChannel(channelPeriodic)
          //  notificationManager.createNotificationChannel(channelInstant)
        }
    }
}