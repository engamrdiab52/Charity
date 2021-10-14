package com.amrabdelhamiddiab.charity.presentation.home

import android.app.Application
import android.view.View
import androidx.lifecycle.ViewModel
import com.amrabdelhamiddiab.charity.frameWork.CharityViewModel
import com.amrabdelhamiddiab.charity.frameWork.PreferenceManager

class HomeViewModel(application: Application) : CharityViewModel(application) {
    private val preferencesHelper = PreferenceManager(application.applicationContext)
    var fakeTime: Long = 0
    var counterForFakeTime: Int = 0
    //////////////////////////////////
    val moneyCurrentValue: Int get() = preferencesHelper.getMoneyValue()
    val helpCurrentValue: Int get() = preferencesHelper.getHelpValue()
    val kindCurrentValue: Int get() = preferencesHelper.getKindValue()
    val prayCurrentValue: Int get() = preferencesHelper.getPrayValue()
    val smileCurrentValue: Int get() = preferencesHelper.getSmileValue()
    val shareCurrentValue: Int get() = preferencesHelper.getShareValue()

    /////////////////////////////////
    val moneyMaxValue: Int get() = preferencesHelper.getMoneyValueMax()
    val helpMaxValue: Int get() = preferencesHelper.getHelpValueMax()
    val kindMaxValue: Int get() = preferencesHelper.getKindValueMax()
    val prayMaxValue: Int get() = preferencesHelper.getPrayValueMax()
    val smileMaxValue: Int get() = preferencesHelper.getSmileValueMax()
    val shareMaxValue: Int get() = preferencesHelper.getShareValueMax()

    val savedTime: Long get() = preferencesHelper.getPreviousSavedTime()
    ////////////////////////////
    fun setMoneyValue(moneyValue: Int) {
        preferencesHelper.setMoneyValue(moneyValue)
    }

    fun setHelpValue(helpValue: Int) {
        preferencesHelper.setHelpValue(helpValue)
    }

    fun setKindValue(kindValue: Int) {
        preferencesHelper.setKindValue(kindValue)
    }

    fun setPrayValue(prayValue: Int) {
        preferencesHelper.setPrayValue(prayValue)
    }

    fun setSmileValue(smileValue: Int) {
        preferencesHelper.setSmileValue(smileValue)
    }

    fun setShareValue(shareValue: Int) {
        preferencesHelper.setShareValue(shareValue)
    }


    fun saveTime(currentTime: Long) {
        preferencesHelper.setCurrentTime(currentTime)
    }

    fun startNewProject():Boolean {
        return moneyMaxValue == -1 &&
                helpMaxValue == -1 &&
                kindMaxValue == -1 &&
                prayMaxValue == -1 &&
                smileMaxValue == -1 &&
                shareMaxValue == -1
    }
    fun showMoneyDone():Int{
      return if (moneyMaxValue == moneyCurrentValue && moneyCurrentValue != 0) {
           View.VISIBLE
       } else {
           View.GONE
       }
    }
    fun showHelpDone():Int{
        return if (helpMaxValue == helpCurrentValue && helpCurrentValue != 0) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
    fun showKindDone():Int{
        return if (kindMaxValue == kindCurrentValue && kindCurrentValue != 0) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
    fun showPrayDone():Int{
        return if (prayMaxValue == prayCurrentValue && prayCurrentValue != 0) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
    fun showSmileDone():Int{
        return if (smileMaxValue == smileCurrentValue && smileCurrentValue != 0) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
    fun showShareDone():Int{
        return if (shareMaxValue == shareCurrentValue && shareCurrentValue != 0) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

}