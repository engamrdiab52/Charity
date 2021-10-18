package com.amrabdelhamiddiab.charity.presentation.newTarget

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import com.amrabdelhamiddiab.charity.MainActivity.Companion.TAG
import com.amrabdelhamiddiab.charity.R
import com.amrabdelhamiddiab.charity.databinding.FragmentNewTargetBinding
import com.amrabdelhamiddiab.charity.frameWork.CharityViewModelFactory
import com.amrabdelhamiddiab.charity.frameWork.hideKeyboard
import com.google.android.material.textfield.TextInputEditText
import java.util.*


class NewTargetFragment : Fragment() {
    private lateinit var binding: FragmentNewTargetBinding
    private val viewModel: NewTargetViewModel by lazy {
        ViewModelProvider(this, CharityViewModelFactory)[NewTargetViewModel::class.java]
    }
    private lateinit var money: TextInputEditText
    private lateinit var help: TextInputEditText
    private lateinit var kind: TextInputEditText
    private lateinit var pray: TextInputEditText
    private lateinit var smile: TextInputEditText
    private lateinit var share: TextInputEditText
    private var moneyString: String = ""
    private var helpString: String = ""
    private var kindString: String = ""
    private var prayString: String = ""
    private var smileString: String = ""
    private var shareString: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_target, container, false)
        money = binding.textInputEditTextMoney
        help = binding.textInputEditTextHelp
        kind = binding.textInputEditTextKind
        pray = binding.textInputEditTextPray
        smile = binding.textInputEditTextSmile
        share = binding.textInputEditTextShare

        money.setText("${viewModel.moneyValueMax}")
        help.setText("${viewModel.helpValueMax}")
        kind.setText("${viewModel.kindValueMax}")
        pray.setText("${viewModel.prayValueMax}")
        smile.setText("${viewModel.prayValueMax}")
        share.setText("${viewModel.shareValueMax}")

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_check, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_check -> {
                moneyString = money.text?.trim().toString()
                helpString = help.text?.trim().toString()
                kindString = kind.text?.trim().toString()
                prayString = pray.text?.trim().toString()
                smileString = smile.text?.trim().toString()
                shareString = share.text?.trim().toString()

                val moneyValueMax = if (moneyString.isEmpty()) {
                    0
                } else {
                    moneyString.toInt()
                }

                viewModel.setMoneyValue(moneyValueMax)

                val helpValueMax = if (helpString.isEmpty()) {
                    0
                } else {
                    helpString.toInt()
                }
                viewModel.setHelpValue(helpValueMax)

                val kindValueMax = if (kindString.isEmpty()) {
                    0
                } else {
                    kindString.toInt()
                }
                viewModel.setKindValue(kindValueMax)

                val prayValueMax = if (prayString.isEmpty()) {
                    0
                } else {
                    prayString.toInt()
                }
                viewModel.setPrayValue(prayValueMax)

                val smileValueMax = if (smileString.isEmpty()) {
                    0
                } else {
                    smileString.toInt()
                }
                viewModel.setSmileValue(smileValueMax)

                val shareValueMax = if (shareString.isEmpty()) {
                    0
                } else {
                    shareString.toInt()
                }
                viewModel.setShareValue(shareValueMax)
                val totalValue =
                    viewModel.moneyValueMax + viewModel.helpValueMax + viewModel.smileValueMax + viewModel.kindValueMax + viewModel.shareValueMax + viewModel.prayValueMax
                hideKeyboard()
                displayDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun displayDialog() {
        MaterialDialog(requireContext()).show {
            val singleChoice =
                listItemsSingleChoice(
                    R.array.project_array,
                    initialSelection = 0
                ) { _, index, text ->
                    Log.d(TAG, text.toString())
                    when (index) {
                        0 -> {
                            val date = Date()
                            val currentMoment = date.time
                            viewModel.saveTime(currentMoment)
                            viewModel.setCurrentMoneyValue(0)
                            viewModel.setCurrentHelpValue(0)
                            viewModel.setCurrentPrayValue(0)
                            viewModel.setCurrentKindValue(0)
                            viewModel.setCurrentSmileValue(0)
                            viewModel.setCurrentShareValue(0)
                            //**/*/*/*/*/*/*/*/**/*/*/*/*/*/*/*/*/*/*
                            findNavController().navigate(R.id.action_newTargetFragment_to_homeFragment)
                            Log.d(TAG, "SAVED TIME : " + viewModel.savedTime.toString())
                        }
                        1 -> {
                            findNavController().navigate(R.id.action_newTargetFragment_to_homeFragment)
                        }
                    }
                }
            positiveButton(R.string.text_ok)
            singleChoice.cancelOnTouchOutside
        }

    }

    companion object {
        private const val DAY: Long = 68400000
    }
}