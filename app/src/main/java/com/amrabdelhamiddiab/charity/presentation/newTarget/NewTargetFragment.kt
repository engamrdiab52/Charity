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

        if (viewModel.moneyValueMax != -1) {
            money.setText(viewModel.moneyValueMax.toString())
        } else {
            money.setText("0")
        }
        if (viewModel.moneyValueMax != -1) {
            help.setText(viewModel.helpValueMax.toString())
        } else {
            help.setText("0")
        }

        if (viewModel.moneyValueMax != -1) {
            kind.setText(viewModel.kindValueMax.toString())
        } else {
            kind.setText("0")
        }

        if (viewModel.moneyValueMax != -1) {
            pray.setText(viewModel.prayValueMax.toString())
        } else {
            pray.setText("0")
        }

        if (viewModel.moneyValueMax != -1) {
            smile.setText(viewModel.smileValueMax.toString())
        } else {
            smile.setText("0")
        }
        if (viewModel.moneyValueMax != -1) {
            share.setText(viewModel.shareValueMax.toString())
        } else {
            share.setText("0")
        }


        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_check, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_check -> {
                /*  val days = 10000000000 / 68400000.0f
                  val value =  days.roundToInt() +1*/
                moneyString = money.text?.trim().toString()
                helpString = help.text?.trim().toString()
                kindString = kind.text?.trim().toString()
                prayString = pray.text?.trim().toString()
                smileString = smile.text?.trim().toString()
                shareString = share.text?.trim().toString()

                val moneyValueMax = if (moneyString.isEmpty()) {
                    0
                } else {
                    if (moneyString.toInt() < viewModel.moneyCurrentValue) {
                        Toast.makeText(
                            requireContext(),
                            "your new target value less than your saved score",
                            Toast.LENGTH_LONG
                        ).show()
                        viewModel.moneyValueMax
                    } else {
                        moneyString.toInt()
                    }
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
                findNavController().navigate(R.id.action_newTargetFragment_to_homeFragment)
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
                            Log.d(TAG, "SAVED TIME : " + viewModel.savedTime.toString())
                        }
                        1 -> {
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