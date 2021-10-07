package com.amrabdelhamiddiab.charity.presentation.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.callbacks.onDismiss
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.amrabdelhamiddiab.charity.MainActivity.Companion.TAG
import com.amrabdelhamiddiab.charity.R
import com.amrabdelhamiddiab.charity.databinding.FragmentHomeBinding
import com.amrabdelhamiddiab.charity.frameWork.CharityViewModelFactory
import com.amrabdelhamiddiab.charity.presentation.newTarget.NewTargetFragment
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.textfield.TextInputEditText

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, CharityViewModelFactory)[HomeViewModel::class.java]
    }
    var fakeTime: Long = 0
    var counterForFakeTime: Int = 0

    private lateinit var moneyProgressIndicator: LinearProgressIndicator
    private lateinit var helpProgressIndicator: LinearProgressIndicator
    private lateinit var kindProgressIndicator: LinearProgressIndicator
    private lateinit var prayProgressIndicator: LinearProgressIndicator
    private lateinit var smileProgressIndicator: LinearProgressIndicator
    private lateinit var shareProgressIndicator: LinearProgressIndicator

    private lateinit var tvMoneyCurrentMaxValue: TextView
    private lateinit var tvHelpCurrentMaxValue: TextView
    private lateinit var tvKindCurrentMaxValue: TextView
    private lateinit var tvPrayCurrentMaxValue: TextView
    private lateinit var tvSmileCurrentMaxValue: TextView
    private lateinit var tvShareCurrentMaxValue: TextView

    private lateinit var tvMoney: TextView
    private lateinit var tvHelp: TextView
    private lateinit var tvKind: TextView
    private lateinit var tvPray: TextView
    private lateinit var tvSmile: TextView
    private lateinit var tvShare: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewModel = viewModel
        if (viewModel.startNewProject()) {
            findNavController().navigate(R.id.action_homeFragment_to_newTargetFragment)
        }
        initializeViews()

        "${viewModel.moneyCurrentValue}/${viewModel.moneyMaxValue}".also {
            tvMoneyCurrentMaxValue.text = it
        }
        "${viewModel.helpCurrentValue}/${viewModel.helpMaxValue}".also {
            tvHelpCurrentMaxValue.text = it
        }
        "${viewModel.kindCurrentValue}/${viewModel.kindMaxValue}".also {
            tvKindCurrentMaxValue.text = it
        }
        "${viewModel.prayCurrentValue}/${viewModel.prayMaxValue}".also {
            tvPrayCurrentMaxValue.text = it
        }
        "${viewModel.smileCurrentValue}/${viewModel.smileMaxValue}".also {
            tvSmileCurrentMaxValue.text = it
        }
        "${viewModel.shareCurrentValue}/${viewModel.shareMaxValue}".also {
            tvShareCurrentMaxValue.text = it
        }
//##############################################
        binding.viewMoney.setOnClickListener {
            displaySuccessDialog(
                moneyProgressIndicator,
                tvMoneyCurrentMaxValue,
                viewModel.moneyMaxValue,
                viewModel.moneyCurrentValue,
                tvMoney
            )
        }
        binding.viewHelp.setOnClickListener {
            displaySuccessDialog(
                helpProgressIndicator,
                tvHelpCurrentMaxValue,
                viewModel.helpMaxValue,
                viewModel.helpCurrentValue,
                tvHelp
            )
        }
        binding.viewKind.setOnClickListener {
            displaySuccessDialog(
                kindProgressIndicator,
                tvKindCurrentMaxValue,
                viewModel.kindMaxValue,
                viewModel.kindCurrentValue,
                tvKind
            )
        }
        binding.viewPray.setOnClickListener {
            displaySuccessDialog(
                prayProgressIndicator,
                tvPrayCurrentMaxValue,
                viewModel.prayMaxValue,
                viewModel.prayCurrentValue,
                tvPray
            )
        }
        binding.viewSmile.setOnClickListener {
            displaySuccessDialog(
                smileProgressIndicator,
                tvSmileCurrentMaxValue,
                viewModel.smileMaxValue,
                viewModel.smileCurrentValue,
                tvSmile
            )
        }
        binding.viewShare.setOnClickListener {
            displaySuccessDialog(
                shareProgressIndicator,
                tvShareCurrentMaxValue,
                viewModel.shareMaxValue,
                viewModel.shareCurrentValue,
                tvShare
            )
        }
        /////////////////////////////////////////////////////////////////////
        estimateProgress(viewModel.moneyCurrentValue, viewModel.moneyMaxValue, binding.statusMoney)
        estimateProgress(viewModel.helpCurrentValue, viewModel.helpMaxValue, binding.statusHelp)
        estimateProgress(viewModel.kindCurrentValue, viewModel.kindMaxValue, binding.statusKind)
        estimateProgress(viewModel.prayCurrentValue, viewModel.prayMaxValue, binding.statusPray)
        estimateProgress(viewModel.smileCurrentValue, viewModel.smileMaxValue, binding.statusSmile)
        estimateProgress(viewModel.shareCurrentValue, viewModel.shareMaxValue, binding.statusShare)
        /////////////////////////////////////////////////////////////////
        return binding.root
    }

    private fun estimateProgress(currentValue: Int, maximumValue: Int, statusView: TextView) {
        val value = ((currentValue / maximumValue.toDouble()) * 100)
        val week = estimateWeek()
        writeTextStatus(value, week, statusView)
    }

    private fun estimateWeek(): Int {
        var value = 0
        when {
            ((viewModel.savedTime + fakeTime) - viewModel.savedTime) <= (DAY * 7) -> {
                value = 1
            }
            ((viewModel.savedTime + fakeTime) - viewModel.savedTime) > (DAY * 7) &&
                    ((viewModel.savedTime + fakeTime) - viewModel.savedTime) <= (DAY * 7 * 2) -> {
                value = 2
            }
            ((viewModel.savedTime + fakeTime) - viewModel.savedTime) > (DAY * 7 * 2) &&
                    ((viewModel.savedTime + fakeTime) - viewModel.savedTime) <= (DAY * 7 * 3) -> {
                value = 3
            }
            ((viewModel.savedTime + fakeTime) - viewModel.savedTime) > (DAY * 7 * 3) &&
                    ((viewModel.savedTime + fakeTime) - viewModel.savedTime) <= (DAY * 7 * 4) -> {
                value = 4
            }
        }
        return value
    }

    private fun writeTextStatus(value: Double, n: Int, statusView: TextView) {
        if (n == 1) {
            "".also { statusView.text = it }
        } else {
            when {
                value > (25.0 * n) -> {
                    "awesome".also { statusView.text = it }
                }
                value == (25.0 * n) -> {
                    "Perfect".also { statusView.text = it }
                }
                value in (21.0 * n)..(24.99 * n) -> {
                    "Excellent".also { statusView.text = it }
                }
                value in (17.5 * n)..(20.99 * n) -> {
                    "Very Good".also { statusView.text = it }
                }
                value in (12.5 * n)..(17.49 * n) -> {
                    "Good".also { statusView.text = it }
                }
                value in (7.5 * n)..(12.49 * n) -> {
                    "you are late".also { statusView.text = it }
                }
                value in (2.5 * n)..(7.49 * n) -> {
                    "You almost lose your goal".also { statusView.text = it }
                }
                value in 0.0..(2.49 * n) -> {
                    "You can edit the target".also { statusView.text = it }
                }
            }
        }
        //   Log.d(TAG, "writeTextStatus    $value   $n")

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_edit, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_edit -> {
                findNavController().navigate(R.id.action_homeFragment_to_newTargetFragment)
                Log.d(TAG, "edit presses")
                true
            }
            R.id.menu_add -> {
                counterForFakeTime++
                if (counterForFakeTime >= 29) {
                    fakeTime -= (28 * DAY)
                    counterForFakeTime = 0
                }
                fakeTime += DAY
                estimateProgress(
                    viewModel.moneyCurrentValue,
                    viewModel.moneyMaxValue,
                    binding.statusMoney
                )
                estimateProgress(
                    viewModel.helpCurrentValue,
                    viewModel.helpMaxValue,
                    binding.statusHelp
                )
                estimateProgress(
                    viewModel.kindCurrentValue,
                    viewModel.kindMaxValue,
                    binding.statusKind
                )
                estimateProgress(
                    viewModel.prayCurrentValue,
                    viewModel.prayMaxValue,
                    binding.statusPray
                )
                estimateProgress(
                    viewModel.smileCurrentValue,
                    viewModel.smileMaxValue,
                    binding.statusSmile
                )
                estimateProgress(
                    viewModel.shareCurrentValue,
                    viewModel.shareMaxValue,
                    binding.statusShare
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun displaySuccessDialog(
        progressIndicator: LinearProgressIndicator,
        textView: TextView,
        maxValue: Int,
        currentValue: Int,
        textCallerView: TextView
    ) {
        var value = 0
        var valueString: String? = ""
        var valueFromViewModel = 0
        var maxValueFromViewModel = 0
        val layoutMe = MaterialDialog(
            requireContext()
        ).customView(R.layout.layout_bottom_sheet).cornerRadius(8f)
            .positiveButton(R.string.text_ok).onDismiss {
                when (textCallerView) {
                    tvMoney -> {
                        maxValueFromViewModel = viewModel.moneyMaxValue
                        if (!valueString.isNullOrBlank()) {
                            if (value <= maxValueFromViewModel) {
                                viewModel.setMoneyValue(value)
                            } else {
                                Toast.makeText(requireContext(), "Error value", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            if (value == maxValueFromViewModel && value != 0) {
                                binding.imageViewMoney.visibility = View.VISIBLE
                            } else {
                                binding.imageViewMoney.visibility = View.GONE
                            }
                        }
                        valueFromViewModel = viewModel.moneyCurrentValue

                        Log.d(TAG, valueFromViewModel.toString() + maxValueFromViewModel.toString())
                    }
                    tvHelp -> {
                        maxValueFromViewModel = viewModel.helpMaxValue
                        if (!valueString.isNullOrBlank()) {
                            if (value <= maxValueFromViewModel) {
                                viewModel.setHelpValue(value)
                            } else {
                                Toast.makeText(requireContext(), "Error value", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            if (value == maxValueFromViewModel && value != 0) {
                                binding.imageViewHelp.visibility = View.VISIBLE
                            } else {
                                binding.imageViewHelp.visibility = View.GONE
                            }
                        }
                        valueFromViewModel = viewModel.helpCurrentValue

                    }
                    tvKind -> {
                        maxValueFromViewModel = viewModel.kindMaxValue
                        if (!valueString.isNullOrBlank()) {
                            if (value <= maxValueFromViewModel) {
                                viewModel.setKindValue(value)
                            } else {
                                Toast.makeText(requireContext(), "Error value", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            if (value == maxValueFromViewModel && value != 0) {
                                binding.imageViewKind.visibility = View.VISIBLE
                            } else {
                                binding.imageViewKind.visibility = View.GONE
                            }
                        }
                        valueFromViewModel = viewModel.kindCurrentValue
                    }
                    tvPray -> {
                        maxValueFromViewModel = viewModel.prayMaxValue
                        if (!valueString.isNullOrBlank()) {
                            if (value <= maxValueFromViewModel) {
                                viewModel.setPrayValue(value)
                            } else {
                                Toast.makeText(requireContext(), "Error value", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            if (value == maxValueFromViewModel && value != 0) {
                                binding.imageViewPray.visibility = View.VISIBLE
                            } else {
                                binding.imageViewPray.visibility = View.GONE
                            }
                        }
                        valueFromViewModel = viewModel.prayCurrentValue

                    }
                    tvSmile -> {
                        maxValueFromViewModel = viewModel.smileMaxValue
                        if (!valueString.isNullOrBlank()) {
                            if (value <= maxValueFromViewModel) {
                                viewModel.setSmileValue(value)
                            } else {
                                Toast.makeText(requireContext(), "Error value", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            if (value == maxValueFromViewModel && value != 0) {
                                binding.imageViewSmile.visibility = View.VISIBLE
                            } else {
                                binding.imageViewSmile.visibility = View.GONE
                            }
                        }
                        valueFromViewModel = viewModel.smileCurrentValue
                    }
                    tvShare -> {
                        maxValueFromViewModel = viewModel.shareMaxValue
                        if (!valueString.isNullOrBlank()) {
                            if (value <= maxValueFromViewModel) {
                                viewModel.setShareValue(value)
                            } else {
                                Toast.makeText(requireContext(), "Error value", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            if (value == maxValueFromViewModel && value != 0) {
                                binding.imageViewShare.visibility = View.VISIBLE
                            } else {
                                binding.imageViewShare.visibility = View.GONE
                            }
                        }
                        valueFromViewModel = viewModel.shareCurrentValue

                    }
                }
                progressIndicator.progress = valueFromViewModel
                "$valueFromViewModel/$maxValueFromViewModel".also { textView.text = it }
                displayToast(valueFromViewModel.toString())
            }

        val custom = layoutMe.getCustomView()
        custom.findViewById<TextInputEditText>(R.id.edit_text_new_value)
            .addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
                override fun afterTextChanged(p0: Editable?) {
                    valueString = p0.toString().trim()
                    value = if (!p0.isNullOrEmpty()) {
                        p0.toString().trim().toInt()
                    } else {
                        Log.d(TAG, "-1")
                        -1
                    }
                }
            })
        custom.findViewById<TextView>(R.id.text_view_current_value).text =
            currentValue.toString()
        custom.findViewById<TextView>(R.id.text_view_max_value).text = maxValue.toString()
        layoutMe.show()
    }

    private fun displayToast(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val DAY: Long = 68400000
    }

    private fun initializeViews() {
        moneyProgressIndicator = binding.progressBarMoney
        helpProgressIndicator = binding.progressBarHelp
        kindProgressIndicator = binding.progressBarKind
        prayProgressIndicator = binding.progressBarPray
        smileProgressIndicator = binding.progressBarSmile
        shareProgressIndicator = binding.progressBarShare

        tvMoneyCurrentMaxValue = binding.textViewCurrentMaxValueMoney
        tvHelpCurrentMaxValue = binding.textViewCurrentMaxValueHelp
        tvKindCurrentMaxValue = binding.textViewCurrentMaxValueKind
        tvPrayCurrentMaxValue = binding.textViewCurrentMaxValuePray
        tvSmileCurrentMaxValue = binding.textViewCurrentMaxValueSmile
        tvShareCurrentMaxValue = binding.textViewCurrentMaxValueShare

        tvMoney = binding.textViewMoney2
        tvHelp = binding.textViewHelp
        tvKind = binding.textViewKind
        tvPray = binding.textViewPray
        tvSmile = binding.textViewSmile
        tvShare = binding.textViewShare

        moneyProgressIndicator.max = viewModel.moneyMaxValue
        helpProgressIndicator.max = viewModel.helpMaxValue
        kindProgressIndicator.max = viewModel.kindMaxValue
        prayProgressIndicator.max = viewModel.prayMaxValue
        smileProgressIndicator.max = viewModel.smileMaxValue
        shareProgressIndicator.max = viewModel.shareMaxValue

        moneyProgressIndicator.progress = viewModel.moneyCurrentValue
        helpProgressIndicator.progress = viewModel.helpCurrentValue
        kindProgressIndicator.progress = viewModel.kindCurrentValue
        prayProgressIndicator.progress = viewModel.prayCurrentValue
        smileProgressIndicator.progress = viewModel.smileCurrentValue
        shareProgressIndicator.progress = viewModel.shareCurrentValue
    }
}