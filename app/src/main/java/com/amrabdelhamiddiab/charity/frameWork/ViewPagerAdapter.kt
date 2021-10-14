package com.amrabdelhamiddiab.charity.frameWork

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
import com.amrabdelhamiddiab.charity.presentation.home.HomeFragment
import com.amrabdelhamiddiab.charity.presentation.home.HomeViewModel
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.textfield.TextInputEditText

class ViewPagerAdapter(
    private val targetList: List<TargetScreenData>,
    private val viewModel: HomeViewModel,
    private val context: Context,
    private val onClickListener: ViewPagerAdapter.OnClickListener

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

  inner  class ViewPagerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivSliderImage = itemView.findViewById<ImageView>(R.id.imageView)
        private val tvDescription = itemView.findViewById<TextView>(R.id.text_view_description)
        private val progressIndicator =
            itemView.findViewById<LinearProgressIndicator>(R.id.progress_bar)
        private val currentRatio = itemView.findViewById<TextView>(R.id.text_view_current_max_value)
        private val statusTv = itemView.findViewById<TextView>(R.id.tv_status)
        private val editGoalButton = itemView.findViewById<Button>(R.id.btn_edit_goal)
      private val imageViewCompleted = itemView.findViewById<ImageView>(R.id.image_view_completed)
        fun bind(image: Int, viewModel: HomeViewModel, itemString: Int) {
            when (image) {
                R.drawable.money_image -> {
                    //////////////////////////////////////
                    "${viewModel.moneyCurrentValue}/${viewModel.moneyMaxValue}".also {
                        currentRatio.text = it
                    }
                    tvDescription.setText(R.string.money_description)
                    ivSliderImage.setImageResource(R.drawable.money_image)
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
                            image
                        )
                    }
                }
                R.drawable.help_image -> {
                    /////////////////////////
                    "${viewModel.helpCurrentValue}/${viewModel.helpMaxValue}".also {
                        currentRatio.text = it
                    }
                    tvDescription.setText(R.string.help_description)
                    ivSliderImage.setImageResource(R.drawable.help_image)
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
                            image
                        )
                    }
                }
                R.drawable.kind_image -> {
                    /////////////////////////
                    "${viewModel.kindCurrentValue}/${viewModel.kindMaxValue}".also {
                        currentRatio.text = it
                    }
                    tvDescription.setText(R.string.kind_description)
                    ivSliderImage.setImageResource(R.drawable.kind_image)
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
                            image
                        )
                    }
                }
                R.drawable.pray_image -> {
                    /////////////////////////
                    "${viewModel.prayCurrentValue}/${viewModel.prayMaxValue}".also {
                        currentRatio.text = it
                    }
                    tvDescription.setText(R.string.pray_description)
                    ivSliderImage.setImageResource(R.drawable.pray_image)
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
                            image
                        )
                    }
                }
                R.drawable.smile_image -> {
                    /////////////////////////
                    "${viewModel.smileCurrentValue}/${viewModel.smileMaxValue}".also {
                        currentRatio.text = it
                    }
                    tvDescription.setText(R.string.smile_description)
                    ivSliderImage.setImageResource(R.drawable.smile_image)
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
                            image
                        )
                    }
                }
                R.drawable.share_image -> {
                    /////////////////////////
                    "${viewModel.shareCurrentValue}/${viewModel.shareMaxValue}".also {
                        currentRatio.text = it
                    }
                    tvDescription.setText(R.string.share_description)
                    ivSliderImage.setImageResource(R.drawable.share_image)
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
                            image
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
            when {
                ((viewModel.savedTime + viewModel.fakeTime) - viewModel.savedTime) <= (HomeFragment.DAY * 7) -> {
                    value = 1
                }
                ((viewModel.savedTime + viewModel.fakeTime) - viewModel.savedTime) > (HomeFragment.DAY * 7) &&
                        ((viewModel.savedTime + viewModel.fakeTime) - viewModel.savedTime) <= (HomeFragment.DAY * 7 * 2) -> {
                    value = 2
                }
                ((viewModel.savedTime + viewModel.fakeTime) - viewModel.savedTime) > (HomeFragment.DAY * 7 * 2) &&
                        ((viewModel.savedTime + viewModel.fakeTime) - viewModel.savedTime) <= (HomeFragment.DAY * 7 * 3) -> {
                    value = 3
                }
                ((viewModel.savedTime + viewModel.fakeTime) - viewModel.savedTime) > (HomeFragment.DAY * 7 * 3) &&
                        ((viewModel.savedTime + viewModel.fakeTime) - viewModel.savedTime) <= (HomeFragment.DAY * 7 * 4) -> {
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

        private fun displaySuccessDialog(
            progressIndicator: LinearProgressIndicator,
            textView: TextView,
            maxValue: Int,
            currentValue: Int,
            imageResource: Int
        ) {
            var value = 0
            var valueString: String? = ""
            var valueFromViewModel = 0
            var maxValueFromViewModel = 0
            val layoutMe = MaterialDialog(
                context
            ).customView(R.layout.layout_bottom_sheet).cornerRadius(8f)
                .positiveButton(R.string.text_ok).onDismiss {
                    when (imageResource) {
                        R.drawable.money_image -> {
                            maxValueFromViewModel = viewModel.moneyMaxValue
                            if (!valueString.isNullOrBlank()) {
                                if (value <= maxValueFromViewModel) {
                                    viewModel.setMoneyValue(value)
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
                            valueFromViewModel = viewModel.moneyCurrentValue

                            Log.d(
                                TAG,
                                valueFromViewModel.toString() + maxValueFromViewModel.toString()
                            )
                        }
                        R.drawable.help_image -> {
                            maxValueFromViewModel = viewModel.helpMaxValue
                            if (!valueString.isNullOrBlank()) {
                                if (value <= maxValueFromViewModel) {
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
                        R.drawable.kind_image -> {
                            maxValueFromViewModel = viewModel.kindMaxValue
                            if (!valueString.isNullOrBlank()) {
                                if (value <= maxValueFromViewModel) {
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
                        R.drawable.pray_image -> {
                            maxValueFromViewModel = viewModel.prayMaxValue
                            if (!valueString.isNullOrBlank()) {
                                if (value <= maxValueFromViewModel) {
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
                        R.drawable.smile_image -> {
                            maxValueFromViewModel = viewModel.smileMaxValue
                            if (!valueString.isNullOrBlank()) {
                                if (value <= maxValueFromViewModel) {
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
                        R.drawable.share_image -> {
                            maxValueFromViewModel = viewModel.shareMaxValue
                            if (!valueString.isNullOrBlank()) {
                                if (value <= maxValueFromViewModel) {
                                    viewModel.setShareValue(value)
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
                            valueFromViewModel = viewModel.shareCurrentValue

                        }
                    }
                    progressIndicator.progress = valueFromViewModel
                    "$valueFromViewModel/$maxValueFromViewModel".also { textView.text = it }
                  //  displayToast(valueFromViewModel.toString())
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
}