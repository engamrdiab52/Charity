package com.amrabdelhamiddiab.charity.presentation.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.amrabdelhamiddiab.charity.R
import com.amrabdelhamiddiab.charity.databinding.FragmentSettingsBinding
import com.amrabdelhamiddiab.charity.frameWork.CharityViewModelFactory.application
import com.amrabdelhamiddiab.charity.frameWork.LocaleHelper
import com.amrabdelhamiddiab.charity.frameWork.PreferenceManager
import com.google.android.material.switchmaterial.SwitchMaterial

class SettingsFragment : Fragment() {
    companion object{
        const val ONE_PER_DAY = 1440L
        const val ONE_PER_WEEK = 10080L
        const val TWO_PER_WEEK = 5040L
        const val TWO_PER_DAY = 720L
        const val THREE_PER_WEEK = 3360L
    }
    private lateinit var radioGroupLanguage: RadioGroup
    private lateinit var radioGroupRepeats: RadioGroup
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var switchNightMode: SwitchMaterial
    private val preferencesHelper = PreferenceManager(application.applicationContext)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        radioGroupLanguage = binding.radioGroupLanguage
        radioGroupRepeats = binding.radioGroupRepeats
        switchNightMode = binding.switchNightMode

        switchNightMode.setOnClickListener {
            if (switchNightMode.isChecked){
                preferencesHelper.setNighMode(true)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                preferencesHelper.setNighMode(false)
            }

        }

        when (preferencesHelper.getSavedLanguageChoice()) {
            "en" -> radioGroupLanguage.check(R.id.radio_button_english)
            "ar" -> radioGroupLanguage.check(R.id.radio_button_arabic)
        }
        when (preferencesHelper.getSavedRemindersChoice()){
            ONE_PER_DAY -> radioGroupRepeats.check(R.id.radio_button_once_day)
            ONE_PER_WEEK -> radioGroupRepeats.check(R.id.radio_button_once_week)
            TWO_PER_DAY -> radioGroupRepeats.check(R.id.radio_button_twice_day)
            TWO_PER_WEEK -> radioGroupRepeats.check(R.id.radio_button_twice_week)
            THREE_PER_WEEK -> radioGroupRepeats.check(R.id.radio_button_three_week)
        }
        switchNightMode.isChecked = preferencesHelper.getNightMode()
        radioGroupLanguage.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.radio_button_english -> {
                    LocaleHelper.setLocale(requireActivity(), "en")
                    requireActivity().recreate()
                }
                R.id.radio_button_arabic -> {
                    LocaleHelper.setLocale(requireActivity(), "ar")
                    requireActivity().recreate()
                }
            }
        }
        radioGroupRepeats.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.radio_button_once_week -> {
                    preferencesHelper.setSavedRemindersChoice(TWO_PER_WEEK)
                   // Toast.makeText(requireContext(), "radio_button_once_week", Toast.LENGTH_SHORT)
                   //     .show()
                }
                R.id.radio_button_once_day -> {
                    preferencesHelper.setSavedRemindersChoice(ONE_PER_DAY)
                }
                R.id.radio_button_twice_week -> {
                    preferencesHelper.setSavedRemindersChoice(TWO_PER_WEEK)
                }
                R.id.radio_button_twice_day -> {
                    preferencesHelper.setSavedRemindersChoice(TWO_PER_DAY)
                }
                R.id.radio_button_three_week -> {
                    preferencesHelper.setSavedRemindersChoice(THREE_PER_WEEK)
                }
            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }
}