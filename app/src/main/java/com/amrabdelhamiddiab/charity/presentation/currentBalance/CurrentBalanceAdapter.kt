package com.amrabdelhamiddiab.charity.presentation.currentBalance

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amrabdelhamiddiab.charity.MainActivity.Companion.TAG
import com.amrabdelhamiddiab.charity.R
import com.amrabdelhamiddiab.charity.databinding.ActivityMainBinding.inflate
import com.amrabdelhamiddiab.charity.frameWork.TargetScreenData
import com.amrabdelhamiddiab.charity.presentation.home.HomeViewModel
import com.amrabdelhamiddiab.charity.presentation.home.ViewPagerAdapter
import com.amrabdelhamiddiab.charity.presentation.home.ViewPagerAdapter.Companion.HELP
import com.amrabdelhamiddiab.charity.presentation.home.ViewPagerAdapter.Companion.KIND
import com.amrabdelhamiddiab.charity.presentation.home.ViewPagerAdapter.Companion.MONEY
import com.amrabdelhamiddiab.charity.presentation.home.ViewPagerAdapter.Companion.PRAY
import com.amrabdelhamiddiab.charity.presentation.home.ViewPagerAdapter.Companion.SHARE
import com.amrabdelhamiddiab.charity.presentation.home.ViewPagerAdapter.Companion.SMILE
import kotlin.math.roundToInt

class CurrentBalanceAdapter(
    private val viewModel: CurrentBalanceViewModel,
    private val context: Context
) : RecyclerView.Adapter<CurrentBalanceAdapter.ViewHolderCurrentBalance>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCurrentBalance {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_current_balance, parent, false)
        return ViewHolderCurrentBalance(view)
    }

    override fun onBindViewHolder(holder: ViewHolderCurrentBalance, position: Int) {
    //    val itemValue = totalValues[position]
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 6
    }

    inner class ViewHolderCurrentBalance(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var totalValueFromViewModel = 0
        private val tvCurrentThing: TextView = itemView.findViewById(R.id.tv_current_thing)
        private val tvCurrentBalance: TextView = itemView.findViewById(R.id.tv_current_balance)
        private val imageViewTotal : ImageView = itemView.findViewById(R.id.image_view_total_balance)
        fun bind() {
            when (itemViewType) {
                MONEY -> {
                    Log.d(TAG, " BEFORE::: ${viewModel.moneyTotalValue}")
                    totalValueFromViewModel = viewModel.moneyTotalValue
                    if (totalValueFromViewModel == -1){
                        viewModel.setMoneyValueTotal(0)
                        totalValueFromViewModel = viewModel.moneyTotalValue
                        Log.d(TAG, "AFTER ::: ${viewModel.moneyTotalValue}")
                    }
                    "Money".also { tvCurrentThing.text = it }
                    tvCurrentBalance.text = totalValueFromViewModel.toString()
                    imageViewTotal.setImageResource(R.drawable.mony_profits)
                }
                HELP -> {
                    totalValueFromViewModel = viewModel.helpTotalValue
                    if (totalValueFromViewModel == -1){
                        viewModel.setHelpValueTotal(0)
                        totalValueFromViewModel = viewModel.helpTotalValue
                    }
                    "Help".also { tvCurrentThing.text = it }
                    tvCurrentBalance.text = totalValueFromViewModel.toString()
                    imageViewTotal.setImageResource(R.drawable.help_total)
                }
                KIND -> {
                    totalValueFromViewModel = viewModel.kindTotalValue
                    if (totalValueFromViewModel == -1){
                        viewModel.setKindValueTotal(0)
                        totalValueFromViewModel = viewModel.kindTotalValue
                    }
                    "Kind".also { tvCurrentThing.text = it }
                    tvCurrentBalance.text = totalValueFromViewModel.toString()
                    imageViewTotal.setImageResource(R.drawable.kind_total)
                }
                PRAY -> {
                    totalValueFromViewModel = viewModel.prayTotalValue
                    if (totalValueFromViewModel == -1){
                        viewModel.setPrayValueTotal(0)
                        totalValueFromViewModel = viewModel.prayTotalValue
                    }
                    "Pray".also { tvCurrentThing.text = it }
                    tvCurrentBalance.text = totalValueFromViewModel.toString()
                    imageViewTotal.setImageResource(R.drawable.pray_total)
                }
                SMILE -> {
                    totalValueFromViewModel = viewModel.smileTotalValue
                    if (totalValueFromViewModel == -1){
                        viewModel.setSmileValueTotal(0)
                        totalValueFromViewModel = viewModel.smileTotalValue
                    }
                    "Smile".also { tvCurrentThing.text = it }
                    tvCurrentBalance.text = totalValueFromViewModel.toString()
                    imageViewTotal.setImageResource(R.drawable.smile_total)
                }
                SHARE -> {
                    totalValueFromViewModel = viewModel.shareTotalValue
                    if (totalValueFromViewModel == -1){
                        viewModel.setShareValueTotal(0)
                        totalValueFromViewModel = viewModel.shareTotalValue
                    }
                    "Share".also { tvCurrentThing.text = it }
                    tvCurrentBalance.text = totalValueFromViewModel.toString()
                    imageViewTotal.setImageResource(R.drawable.share_total)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> MONEY
            1 -> HELP
            2 -> KIND
            3 -> PRAY
            4 -> SMILE
            5 -> SHARE
            else -> MONEY
        }

    }

}
