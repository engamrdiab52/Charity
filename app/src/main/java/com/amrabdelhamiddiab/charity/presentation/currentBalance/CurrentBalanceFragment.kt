package com.amrabdelhamiddiab.charity.presentation.currentBalance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.amrabdelhamiddiab.charity.R
import com.amrabdelhamiddiab.charity.databinding.FragmentCurrentBalanceBinding
import com.amrabdelhamiddiab.charity.frameWork.CharityViewModelFactory
import com.amrabdelhamiddiab.charity.frameWork.HorizontalMarginItemDecoration
import com.amrabdelhamiddiab.charity.presentation.home.HomeViewModel

class CurrentBalanceFragment : Fragment() {
    private lateinit var binding: FragmentCurrentBalanceBinding
    private lateinit var viewPager2: ViewPager2

    private val viewModel: CurrentBalanceViewModel by lazy {
        ViewModelProvider(this, CharityViewModelFactory)[CurrentBalanceViewModel::class.java]
    }

    val adapter: CurrentBalanceAdapter by lazy {
        CurrentBalanceAdapter( viewModel, requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_current_balance, container, false)
        viewPager2 = binding.viewPagerCurrentBalance
        viewPager2.adapter = adapter
        val dotsIndicator = binding.springDotsIndicatorCurrentBalance
        dotsIndicator.setViewPager2(viewPager2)

      /*  viewPager2.clipToPadding = false
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
        viewPager2.addItemDecoration(itemDecoration)*/
        // Inflate the layout for this fragment
        return binding.root
    }

}