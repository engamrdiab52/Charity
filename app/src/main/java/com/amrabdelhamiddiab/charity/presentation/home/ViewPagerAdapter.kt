package com.amrabdelhamiddiab.charity.presentation.home

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.callbacks.onDismiss
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.amrabdelhamiddiab.charity.MainActivity.Companion.TAG
import com.amrabdelhamiddiab.charity.R
import com.amrabdelhamiddiab.charity.frameWork.TargetScreenData
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.textfield.TextInputEditText
import java.util.*
import kotlin.math.roundToInt

class ViewPagerAdapter(
    private val targetList: List<TargetScreenData>,
    private val viewModel: HomeViewModel,
    private val context: Context,
    private val onClickListener: OnClickListener

) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view_pager, parent, false)
        return ViewPagerHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        val targetObject = targetList[position]
        holder.bind(targetObject.imageRes, viewModel, targetObject.stringRes)
    }

    override fun getItemCount(): Int {
        return targetList.size
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

    inner class ViewPagerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivSliderImage = itemView.findViewById<ImageView>(R.id.imageView)
        private val tvDescription = itemView.findViewById<TextView>(R.id.text_view_description)
        private var progressIndicator =
            itemView.findViewById<LinearProgressIndicator>(R.id.progress_bar)
        private val currentRatio = itemView.findViewById<TextView>(R.id.text_view_current_max_value)
        private val statusTv = itemView.findViewById<TextView>(R.id.tv_status)
        private val editGoalButton = itemView.findViewById<MaterialButton>(R.id.btn_edit_goal)
        private val imageViewCompleted = itemView.findViewById<ImageView>(R.id.image_view_completed)
        fun bind(image: Int, viewModel: HomeViewModel, itemString: Int) {
            when (itemViewType) {
                MONEY -> {
                    imageViewCompleted.visibility = viewModel.showMoneyDone()
                    //////////////////////////////////////
                    "${viewModel.moneyCurrentValue}/${viewModel.moneyMaxValue}".also {
                        currentRatio.text = it
                    }
                    tvDescription.setText(R.string.money_description)
                    ivSliderImage.setImageResource(R.drawable.money_image)
                    if (viewModel.moneyMaxValue == 0) {
                        progressIndicator.setProgressCompat(0, false)
                    } else {
                        progressIndicator.setProgressCompat(
                            ((viewModel.moneyCurrentValue / viewModel.moneyMaxValue.toFloat()) * 100).roundToInt(),
                            false
                        )
                    }

                    // progressIndicator.max = viewModel.moneyMaxValue
                    ///////////////////////////////////
                    estimateProgress(
                        viewModel.moneyCurrentValue, viewModel.moneyMaxValue, statusTv
                    )
                    ////////////////////////////////
                    editGoalButton.setOnClickListener {
                        displaySuccessDialog(
                            progressIndicator,
                            currentRatio,
                            viewModel.moneyMaxValue,
                            viewModel.moneyCurrentValue,
                            MONEY
                        )
                    }
                }
                HELP -> {
                    imageViewCompleted.visibility = viewModel.showHelpDone()
                    /////////////////////////
                    "${viewModel.helpCurrentValue}/${viewModel.helpMaxValue}".also {
                        currentRatio.text = it
                    }
                    tvDescription.setText(R.string.help_description)
                    ivSliderImage.setImageResource(R.drawable.help_image)
                    if (viewModel.helpMaxValue == 0) {
                        progressIndicator.setProgressCompat(0, false)
                    } else {
                        progressIndicator.setProgressCompat(
                            ((viewModel.helpCurrentValue / viewModel.helpMaxValue.toFloat()) * 100).roundToInt(),
                            false
                        )
                    }
                    //  progressIndicator.max = viewModel.helpMaxValue
                    ////////////////////////
                    estimateProgress(
                        viewModel.helpCurrentValue,
                        viewModel.helpMaxValue,
                        statusTv
                    )
                    ////////////////////////////////
                    editGoalButton.setOnClickListener {
                        displaySuccessDialog(
                            progressIndicator,
                            currentRatio,
                            viewModel.helpMaxValue,
                            viewModel.helpCurrentValue,
                            HELP
                        )
                    }
                }
                KIND -> {
                    imageViewCompleted.visibility = viewModel.showKindDone()
                    /////////////////////////
                    "${viewModel.kindCurrentValue}/${viewModel.kindMaxValue}".also {
                        currentRatio.text = it
                    }
                    tvDescription.setText(R.string.kind_description)
                    ivSliderImage.setImageResource(R.drawable.kind_image)
                    if (viewModel.kindMaxValue == 0) {
                        progressIndicator.setProgressCompat(0, false)
                    } else {
                        progressIndicator.setProgressCompat(
                            ((viewModel.kindCurrentValue / viewModel.kindMaxValue.toFloat()) * 100).roundToInt(),
                            false
                        )
                    }
                    // progressIndicator.max = viewModel.kindMaxValue
                    /////////////////////////
                    estimateProgress(
                        viewModel.kindCurrentValue,
                        viewModel.kindMaxValue,
                        statusTv
                    )
                    ////////////////////////////////
                    editGoalButton.setOnClickListener {

                        displaySuccessDialog(
                            progressIndicator,
                            currentRatio,
                            viewModel.kindMaxValue,
                            viewModel.kindCurrentValue,
                            KIND
                        )

                    }
                }
                PRAY -> {
                    imageViewCompleted.visibility = viewModel.showPrayDone()
                    /////////////////////////
                    "${viewModel.prayCurrentValue}/${viewModel.prayMaxValue}".also {
                        currentRatio.text = it
                    }
                    tvDescription.setText(R.string.pray_description)
                    ivSliderImage.setImageResource(R.drawable.pray_image)
                    if (viewModel.prayMaxValue == 0) {
                        progressIndicator.setProgressCompat(0, false)
                    } else {
                        progressIndicator.setProgressCompat(
                            ((viewModel.prayCurrentValue / viewModel.prayMaxValue.toFloat()) * 100).roundToInt(),
                            false
                        )
                    }
                    //  progressIndicator.max = viewModel.prayMaxValue
                    /////////////////////////
                    estimateProgress(
                        viewModel.prayCurrentValue,
                        viewModel.prayMaxValue,
                        statusTv
                    )
                    ////////////////////////////////
                    editGoalButton.setOnClickListener {
                        displaySuccessDialog(
                            progressIndicator,
                            currentRatio,
                            viewModel.prayMaxValue,
                            viewModel.prayCurrentValue,
                            PRAY
                        )
                    }
                }
                SMILE -> {
                    imageViewCompleted.visibility = viewModel.showSmileDone()
                    /////////////////////////
                    "${viewModel.smileCurrentValue}/${viewModel.smileMaxValue}".also {
                        currentRatio.text = it
                    }
                    tvDescription.setText(R.string.smile_description)
                    ivSliderImage.setImageResource(R.drawable.smile_image)
                    if (viewModel.smileMaxValue == 0) {
                        progressIndicator.setProgressCompat(0, false)
                    } else {
                        progressIndicator.setProgressCompat(
                            ((viewModel.smileCurrentValue / viewModel.smileMaxValue.toFloat()) * 100).roundToInt(),
                            false
                        )

                    }
                    // progressIndicator.max = viewModel.smileMaxValue
                    /////////////////////////
                    estimateProgress(
                        viewModel.smileCurrentValue,
                        viewModel.smileMaxValue,
                        statusTv
                    )
                    ////////////////////////////////
                    editGoalButton.setOnClickListener {
                        displaySuccessDialog(
                            progressIndicator,
                            currentRatio,
                            viewModel.smileMaxValue,
                            viewModel.smileCurrentValue,
                            SMILE
                        )
                    }
                }
                SHARE -> {
                    imageViewCompleted.visibility = viewModel.showShareDone()
                    /////////////////////////
                    "${viewModel.shareCurrentValue}/${viewModel.shareMaxValue}".also {
                        currentRatio.text = it
                    }
                    tvDescription.setText(R.string.share_description)
                    ivSliderImage.setImageResource(R.drawable.share_image)
                    if (viewModel.shareMaxValue == 0) {
                        progressIndicator.setProgressCompat(0, false)
                    } else {
                        progressIndicator.setProgressCompat(
                            ((viewModel.shareCurrentValue / viewModel.shareMaxValue.toFloat()) * 100).roundToInt(),
                            false
                        )

                    } // progressIndicator.max = viewModel.shareMaxValue
                    /////////////////////////
                    estimateProgress(
                        viewModel.shareCurrentValue,
                        viewModel.shareMaxValue,
                        statusTv
                    )
                    ////////////////////////////////
                    editGoalButton.setOnClickListener {
                        displaySuccessDialog(
                            progressIndicator,
                            currentRatio,
                            viewModel.shareMaxValue,
                            viewModel.shareCurrentValue,
                            SHARE
                        )
                    }
                }
            }
        }

        private fun estimateProgress(currentValue: Int, maximumValue: Int, statusView: TextView) {
            val value = ((currentValue / maximumValue.toDouble()) * 100)
            val week = estimateWeek()
            writeTextStatus(value, week, statusView)
        }

        private fun estimateWeek(): Int {
            var value = 0
            //(viewModel.savedTime + viewModel.fakeTime)
            when {
                (Date().time - viewModel.savedTime) <= (HomeFragment.DAY * 7) -> {
                    value = 1
                }
                (Date().time  - viewModel.savedTime) > (HomeFragment.DAY * 7) &&
                        ((viewModel.savedTime + viewModel.fakeTime) - viewModel.savedTime) <= (HomeFragment.DAY * 7 * 2) -> {
                    value = 2
                }
                (Date().time  - viewModel.savedTime) > (HomeFragment.DAY * 7 * 2) &&
                        ((viewModel.savedTime + viewModel.fakeTime) - viewModel.savedTime) <= (HomeFragment.DAY * 7 * 3) -> {
                    value = 3
                }
                (Date().time  - viewModel.savedTime) > (HomeFragment.DAY * 7 * 3) &&
                        ((viewModel.savedTime + viewModel.fakeTime) - viewModel.savedTime) <= ((HomeFragment.DAY * 7 * 4) + HomeFragment.DAY) -> {
                    value = 4
                }
            }
            Log.d(TAG, "n::::: $value")
            return value
        }

        private fun writeTextStatus(value: Double, n: Int, statusView: TextView) {
            if (n == 1) {
                "".also { statusView.text = it }
            } else {
                when {
                    value >= (25.0 * n) -> {
                        "awesome".also { statusView.text = it }
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
        }

        private fun displaySuccessDialog(
            progressIndicator: LinearProgressIndicator,
            textView: TextView,
            maxValue: Int,
            currentValue: Int,
            itemType: Int
        ) {
            var value = 0
            var valueString: String? = ""
            var valueFromViewModel = 0
            var maxValueFromViewModel = 0
            var totalValueFromViewModel = 0
            val layoutMe = MaterialDialog(
                context
            ).customView(R.layout.layout_bottom_sheet).cornerRadius(8f)
                .positiveButton(R.string.text_ok).onDismiss {
                    when (itemType) {
                        MONEY -> {
                            totalValueFromViewModel = viewModel.moneyTotalValue
                            if (totalValueFromViewModel == -1) {
                                viewModel.setMoneyValueTotal(0)
                            }
                            maxValueFromViewModel = viewModel.moneyMaxValue
                            if (!valueString.isNullOrBlank()) {
                                if ((value <= maxValueFromViewModel) && (maxValueFromViewModel != 0)) {
                                    val currentIncrease = value - viewModel.moneyCurrentValue
                                    val currentTotalValue =
                                        viewModel.moneyTotalValue + currentIncrease
                                    viewModel.setMoneyValueTotal(currentTotalValue)
                                    viewModel.setMoneyValue(value)

                                } else {
                                    Toast.makeText(
                                        context,
                                        "Error value",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                if (value == maxValueFromViewModel && value != 0) {
                                    imageViewCompleted.visibility = View.VISIBLE
                                } else {
                                    imageViewCompleted.visibility = View.GONE
                                }
                            }
                            valueFromViewModel = viewModel.moneyCurrentValue
                        }
                        HELP -> {
                            totalValueFromViewModel = viewModel.helpTotalValue
                            if (totalValueFromViewModel == -1) {
                                viewModel.setHelpValueTotal(0)
                            }
                            maxValueFromViewModel = viewModel.helpMaxValue
                            if (!valueString.isNullOrBlank()) {
                                if ((value <= maxValueFromViewModel) && (maxValueFromViewModel != 0)) {
                                    val currentIncrease = value - viewModel.helpCurrentValue
                                    val currentTotalValue =
                                        viewModel.helpTotalValue + currentIncrease
                                    viewModel.setHelpValueTotal(currentTotalValue)
                                    viewModel.setHelpValue(value)
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Error value",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                                if (value == maxValueFromViewModel && value != 0) {
                                    imageViewCompleted.visibility = View.VISIBLE
                                } else {
                                    imageViewCompleted.visibility = View.GONE
                                }
                            }
                            valueFromViewModel = viewModel.helpCurrentValue

                        }
                        KIND -> {
                            totalValueFromViewModel = viewModel.kindTotalValue
                            if (totalValueFromViewModel == -1) {
                                viewModel.setKindValueTotal(0)
                            }
                            maxValueFromViewModel = viewModel.kindMaxValue
                            if (!valueString.isNullOrBlank()) {
                                if ((value <= maxValueFromViewModel) && (maxValueFromViewModel != 0)) {
                                    val currentIncrease = value - viewModel.kindCurrentValue
                                    val currentTotalValue =
                                        viewModel.kindTotalValue + currentIncrease
                                    viewModel.setKindValueTotal(currentTotalValue)
                                    viewModel.setKindValue(value)
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Error value",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                                if (value == maxValueFromViewModel && value != 0) {
                                    imageViewCompleted.visibility = View.VISIBLE
                                } else {
                                    imageViewCompleted.visibility = View.GONE
                                }
                            }
                            valueFromViewModel = viewModel.kindCurrentValue
                        }
                        PRAY -> {
                            totalValueFromViewModel = viewModel.prayTotalValue
                            if (totalValueFromViewModel == -1) {
                                viewModel.setPrayValueTotal(0)
                            }
                            maxValueFromViewModel = viewModel.prayMaxValue
                            if (!valueString.isNullOrBlank()) {
                                if ((value <= maxValueFromViewModel) && (maxValueFromViewModel != 0)) {
                                    val currentIncrease = value - viewModel.prayCurrentValue
                                    val currentTotalValue =
                                        viewModel.prayTotalValue + currentIncrease
                                    viewModel.setPrayValueTotal(currentTotalValue)
                                    viewModel.setPrayValue(value)
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Error value",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                                if (value == maxValueFromViewModel && value != 0) {
                                    imageViewCompleted.visibility = View.VISIBLE
                                } else {
                                    imageViewCompleted.visibility = View.GONE
                                }
                            }
                            valueFromViewModel = viewModel.prayCurrentValue

                        }
                        SMILE -> {
                            totalValueFromViewModel = viewModel.smileTotalValue
                            if (totalValueFromViewModel == -1) {
                                viewModel.setSmileValueTotal(0)
                            }
                            maxValueFromViewModel = viewModel.smileMaxValue
                            if (!valueString.isNullOrBlank()) {
                                if ((value <= maxValueFromViewModel) && (maxValueFromViewModel != 0)) {
                                    val currentIncrease = value - viewModel.smileCurrentValue
                                    val currentTotalValue =
                                        viewModel.smileTotalValue + currentIncrease
                                    viewModel.setSmileValueTotal(currentTotalValue)
                                    viewModel.setSmileValue(value)
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Error value",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                                if (value == maxValueFromViewModel && value != 0) {
                                    imageViewCompleted.visibility = View.VISIBLE
                                } else {
                                    imageViewCompleted.visibility = View.GONE
                                }
                            }
                            valueFromViewModel = viewModel.smileCurrentValue
                        }
                        SHARE -> {
                            totalValueFromViewModel = viewModel.shareTotalValue
                            if (totalValueFromViewModel == -1) {
                                viewModel.setShareValueTotal(0)
                            }
                            maxValueFromViewModel = viewModel.shareMaxValue
                            if (!valueString.isNullOrBlank()) {
                                if ((value <= maxValueFromViewModel) && (maxValueFromViewModel != 0)) {
                                    val currentIncrease = value - viewModel.shareCurrentValue
                                    val currentTotalValue =
                                        viewModel.shareTotalValue + currentIncrease
                                    viewModel.setShareValueTotal(currentTotalValue)
                                    viewModel.setShareValue(value)
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Error value",
                                        Toast.LENGTH_LONG
                                    )
                                        .show()
                                }
                                if (value == maxValueFromViewModel && value != 0) {
                                    imageViewCompleted.visibility = View.VISIBLE
                                } else {
                                    imageViewCompleted.visibility = View.GONE
                                }
                            }
                            valueFromViewModel = viewModel.shareCurrentValue

                        }
                    }
                    if (maxValueFromViewModel != 0) {
                        progressIndicator.setProgressCompat(
                            ((valueFromViewModel / maxValueFromViewModel.toFloat()) * 100)
                                .roundToInt(), false
                        )
                    }

                    "$valueFromViewModel/$maxValueFromViewModel".also { textView.text = it }
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

    }

    class OnClickListener(val clickListener: (imageResource: Int) -> Unit) {
        fun onClick(imageResource: Int) = clickListener(imageResource)
    }


    companion object {
        const val MONEY: Int = 0
        const val HELP: Int = 1
        const val KIND: Int = 2
        const val PRAY: Int = 3
        const val SMILE: Int = 4
        const val SHARE: Int = 5
    }
}