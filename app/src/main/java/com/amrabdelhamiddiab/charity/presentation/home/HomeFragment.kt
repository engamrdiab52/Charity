package com.amrabdelhamiddiab.charity.presentation.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.callbacks.onDismiss
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import com.amrabdelhamiddiab.charity.MainActivity.Companion.TAG
import com.amrabdelhamiddiab.charity.R
import com.amrabdelhamiddiab.charity.databinding.FragmentHomeBinding
import com.amrabdelhamiddiab.charity.frameWork.CharityViewModelFactory
import com.amrabdelhamiddiab.charity.frameWork.HorizontalMarginItemDecoration
import com.amrabdelhamiddiab.charity.frameWork.targetScreenDataList
import com.google.android.material.textfield.TextInputEditText
import java.util.*
import kotlin.math.abs

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewPager2: ViewPager2

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


    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, CharityViewModelFactory)[HomeViewModel::class.java]
    }
    private val adapter by lazy {
        ViewPagerAdapter(
            targetScreenDataList,
            viewModel,
            requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        viewPager2 = binding.viewPager
        viewPager2.adapter = adapter
        makeAnimationEffect()
        newOpen()

        return binding.root
    }

    private fun makeAnimationEffect() {
        val dotsIndicator = binding.springDotsIndicator
        dotsIndicator.setViewPager2(viewPager2)
        viewPager2.clipToPadding = false
        viewPager2.setPadding(0, 0, 0, 0)
        // You need to retain one page on each side
        // so that the next and previous items are visible
        viewPager2.offscreenPageLimit = 1
        // Add a PageTransformer that translates the next and previous items horizontally
        // towards the center of the screen, which makes them visible
        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx = resources
            .getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            // Next line scales the item's height. You can remove it if you don't want this effect
            page.scaleY = 1 - (0.20f * abs(position))
            // If you want a fading effect uncomment the next line:
            // page.alpha = 0.25f + (1 - abs(position))
        }
        viewPager2.setPageTransformer(pageTransformer)
        // The ItemDecoration gives the current (centered) item horizontal margin so that
        // it doesn't occupy the whole screen width. Without it the items overlap
        val itemDecoration = HorizontalMarginItemDecoration(
            requireContext(),
            R.dimen.viewpager_current_item_horizontal_margin
        )
        viewPager2.addItemDecoration(itemDecoration)
    }

    private fun newOpen() {
        if (viewModel.firstStartToApp()) {
            viewModel.setMoneyMaxValue(0)
            viewModel.setHelpMaxValue(0)
            viewModel.setPrayMaxValue(0)
            viewModel.setKindMaxValue(0)
            viewModel.setShareMaxValue(0)
            viewModel.setSmileMaxValue(0)
            //  findNavController().navigate(R.id.action_homeFragment_to_newTargetFragment)
            getNewTargetInputs()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_edit, menu)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_edit -> {
                getNewTargetInputs()
               // adapter.notifyDataSetChanged()
                //  findNavController().navigate(R.id.action_homeFragment_to_newTargetFragment)
                Log.d(TAG, "edit presses")
                true
            }
       /*     R.id.menu_add -> {
                viewModel.counterForFakeTime++
                if (viewModel.counterForFakeTime >= 29) {
                    viewModel.fakeTime -= (28 * DAY)
                    viewModel.counterForFakeTime = 0
                }
                viewModel.fakeTime += DAY
                Log.d(TAG, "${viewModel.fakeTime} +  ${viewModel.counterForFakeTime}")
                adapter.run { notifyDataSetChanged() }
                true
            }*/
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getNewTargetInputs() {
        val layoutMe = MaterialDialog(
            requireContext(),
            BottomSheet(LayoutMode.WRAP_CONTENT)
        ).customView(R.layout.fragment_new_target).cornerRadius(16.0f)
            .positiveButton(R.string.text_ok).onDismiss {
                getTextInputFieldsInputs()
            }
        initializeTextInputFields(layoutMe)
        layoutMe.show()
    }

    private fun initializeTextInputFields(materialDialog: MaterialDialog) {
        val custom = materialDialog.getCustomView()
        money = custom.findViewById(R.id.text_input_edit_text_money)
        help = custom.findViewById(R.id.text_input_edit_text_help)
        kind = custom.findViewById(R.id.text_input_edit_text_kind)
        pray = custom.findViewById(R.id.text_input_edit_text_pray)
        smile = custom.findViewById(R.id.text_input_edit_text_smile)
        share = custom.findViewById(R.id.text_input_edit_text_share)

        money.setText("${viewModel.moneyMaxValue}")
        help.setText("${viewModel.helpMaxValue}")
        kind.setText("${viewModel.kindMaxValue}")
        pray.setText("${viewModel.prayMaxValue}")
        smile.setText("${viewModel.smileMaxValue}")
        share.setText("${viewModel.shareMaxValue}")
    }

    private fun getTextInputFieldsInputs() {
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

        viewModel.setMoneyMaxValue(moneyValueMax)

        val helpValueMax = if (helpString.isEmpty()) {
            0
        } else {
            helpString.toInt()
        }
        viewModel.setHelpMaxValue(helpValueMax)

        val kindValueMax = if (kindString.isEmpty()) {
            0
        } else {
            kindString.toInt()
        }
        viewModel.setKindMaxValue(kindValueMax)

        val prayValueMax = if (prayString.isEmpty()) {
            0
        } else {
            prayString.toInt()
        }
        viewModel.setPrayMaxValue(prayValueMax)

        val smileValueMax = if (smileString.isEmpty()) {
            0
        } else {
            smileString.toInt()
        }
        viewModel.setSmileMaxValue(smileValueMax)

        val shareValueMax = if (shareString.isEmpty()) {
            0
        } else {
            shareString.toInt()
        }
        viewModel.setShareMaxValue(shareValueMax)
        //  hideKeyboard()
        displayDialog()

    }

    @SuppressLint("NotifyDataSetChanged")
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
                            //     findNavController().navigate(R.id.action_newTargetFragment_to_homeFragment)
                            Log.d(TAG, "SAVED TIME : " + viewModel.savedTime.toString())
                            adapter.run {
                                notifyDataSetChanged()
                            }
                        }
                        1 -> {
                            adapter.run {
                                notifyDataSetChanged()
                            }
                            //    displayToast("")
                            // findNavController().navigate(R.id.action_newTargetFragment_to_homeFragment)
                        }
                    }
                }
            positiveButton(R.string.text_ok)
            singleChoice.cancelOnTouchOutside
        }

    }
    companion object {
        const val DAY: Long = 68400000
    }

}