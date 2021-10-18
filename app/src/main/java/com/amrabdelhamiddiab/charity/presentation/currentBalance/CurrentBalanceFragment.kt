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

        // Inflate the layout for this fragment
        return binding.root
    }

}