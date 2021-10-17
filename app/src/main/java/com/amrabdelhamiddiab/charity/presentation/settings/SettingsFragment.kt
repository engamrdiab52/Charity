package com.amrabdelhamiddiab.charity.presentation.settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.databinding.DataBindingUtil
import com.amrabdelhamiddiab.charity.R
import com.amrabdelhamiddiab.charity.databinding.FragmentSettingsBinding
import com.amrabdelhamiddiab.charity.frameWork.CharityViewModelFactory.application
import com.amrabdelhamiddiab.charity.frameWork.LocaleHelper
import com.amrabdelhamiddiab.charity.frameWork.PreferenceManager
import java.util.*

class SettingsFragment : Fragment() {
    private lateinit var radioGroup: RadioGroup
    private lateinit var binding: FragmentSettingsBinding
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
        radioGroup = binding.radioGroupLanguage
        when (preferencesHelper.getSavedLanguageChoice()){
            "en" -> radioGroup.check(R.id.radio_button_english)
            "ar" -> radioGroup.check(R.id.radio_button_arabic)
        }
        radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.radio_button_english -> {
                    LocaleHelper.setLocale(requireContext(), "en")
                }
                R.id.radio_button_arabic -> {
                    LocaleHelper.setLocale(requireContext(), "ar")
                }
            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }
}