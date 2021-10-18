package com.amrabdelhamiddiab.charity.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.callbacks.onDismiss
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.amrabdelhamiddiab.charity.MainActivity.Companion.TAG
import com.amrabdelhamiddiab.charity.R
import com.amrabdelhamiddiab.charity.databinding.FragmentHomeBinding
import com.amrabdelhamiddiab.charity.frameWork.CharityViewModelFactory
import com.amrabdelhamiddiab.charity.frameWork.HorizontalMarginItemDecoration
import com.amrabdelhamiddiab.charity.frameWork.targetScreenDataList
import com.google.android.material.textfield.TextInputEditText

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewPager2: ViewPager2
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, CharityViewModelFactory)[HomeViewModel::class.java]
    }
    val adapter by lazy {
        ViewPagerAdapter(
            targetScreenDataList,
            viewModel,
            requireContext(),
            ViewPagerAdapter.OnClickListener {
                Toast.makeText(requireContext(), "done", Toast.LENGTH_SHORT).show()
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        if (viewModel.startNewProject()) {
            viewModel.setMoneyMaxValue(0)
            viewModel.setHelpMaxValue(0)
            viewModel.setPrayMaxValue(0)
            viewModel.setKindMaxValue(0)
            viewModel.setShareMaxValue(0)
            viewModel.setSmileMaxValue(0)
            findNavController().navigate(R.id.action_homeFragment_to_newTargetFragment)

        }
        viewPager2 = binding.viewPager
        viewPager2.adapter = adapter
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
            page.scaleY = 1 - (0.20f * Math.abs(position))
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
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_edit, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_edit -> {
                val layoutMe = MaterialDialog(
                    requireContext(),
                    BottomSheet(LayoutMode.WRAP_CONTENT)
                ).customView(R.layout.fragment_new_target).cornerRadius(16.0f)
                    .positiveButton(R.string.text_ok).onDismiss {

                    }
                val custom = layoutMe.getCustomView()
               // custom.findViewById<TextInputEditText>(R.id.text_input_edit_text_money).
                layoutMe.show()

                //  findNavController().navigate(R.id.action_homeFragment_to_newTargetFragment)
                Log.d(TAG, "edit presses")
                true
            }
            R.id.menu_add -> {
                viewModel.counterForFakeTime++
                if (viewModel.counterForFakeTime >= 29) {
                    viewModel.fakeTime -= (28 * DAY)
                    viewModel.counterForFakeTime = 0
                }
                viewModel.fakeTime += DAY
                Log.d(TAG, "${viewModel.fakeTime} +  ${viewModel.counterForFakeTime}")
                adapter.run { notifyDataSetChanged() }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun displayToast(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val DAY: Long = 68400000
    }

}