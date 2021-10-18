package com.amrabdelhamiddiab.charity.presentation.home

import android.app.Application
import android.view.View
import com.amrabdelhamiddiab.charity.frameWork.CharityViewModel
import com.amrabdelhamiddiab.charity.frameWork.PreferenceManager

class HomeViewModel(application: Application) : CharityViewModel(application) {
    private val preferencesHelper = PreferenceManager(application.applicationContext)
    var fakeTime: Long = 0
   // var counterForFakeTime: Int = 0
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
///////////////////////////////////////////////////////////////////////
val moneyTotalValue: Int get() = preferencesHelper.getMoneyValueTotal()
    val helpTotalValue: Int get() = preferencesHelper.getHelpValueTotal()
    val kindTotalValue: Int get() = preferencesHelper.getKindValueTotal()
    val prayTotalValue: Int get() = preferencesHelper.getPrayValueTotal()
    val smileTotalValue: Int get() = preferencesHelper.getSmileValueTotal()
    val shareTotalValue: Int get() = preferencesHelper.getShareValueTotal()

    val savedTime: Long get() = preferencesHelper.getPreviousSavedTime()
    ////////////////////////////
    fun setMoneyMaxValue(moneyValueMax: Int) {
        preferencesHelper.setMoneyValueMax(moneyValueMax)
    }

    fun setHelpMaxValue(helpValueMax: Int) {
        preferencesHelper.setHelpValueMax(helpValueMax)
    }

    fun setKindMaxValue(kindValueMax: Int) {
        preferencesHelper.setKindValueMax(kindValueMax)
    }

    fun setPrayMaxValue(prayValueMax: Int) {
        preferencesHelper.setPrayValueMax(prayValueMax)
    }

    fun setSmileMaxValue(smileValueMax: Int) {
        preferencesHelper.setSmileValueMax(smileValueMax)
    }

    fun setShareMaxValue(shareValueMax: Int) {
        preferencesHelper.setShareValueMax(shareValueMax)
    }


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

    fun saveTime(currentTime: Long) {
        preferencesHelper.setCurrentTime(currentTime)
    }

    fun setCurrentMoneyValue(moneyValue: Int) {
        preferencesHelper.setMoneyValue(moneyValue)
    }

    fun setCurrentHelpValue(helpValue: Int) {
        preferencesHelper.setHelpValue(helpValue)
    }

    fun setCurrentKindValue(kindValue: Int) {
        preferencesHelper.setKindValue(kindValue)
    }

    fun setCurrentPrayValue(prayValue: Int) {
        preferencesHelper.setPrayValue(prayValue)
    }

    fun setCurrentSmileValue(smileValue: Int) {
        preferencesHelper.setSmileValue(smileValue)
    }

    fun setCurrentShareValue(shareValue: Int) {
        preferencesHelper.setShareValue(shareValue)
    }


    fun firstStartToApp():Boolean {
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