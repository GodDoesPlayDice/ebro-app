package com.example.ebroapp.view.fragment.lowertoolbar

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.children
import androidx.core.view.forEachIndexed
import com.example.ebroapp.R
import com.example.ebroapp.databinding.FragmentLowerToolbarBinding
import com.example.ebroapp.utils.setOnSeekBarListener
import com.example.ebroapp.view.base.BaseFragment

class LowerToolbarFragment :
    BaseFragment<FragmentLowerToolbarBinding, LowerToolbarViewModel>(LowerToolbarViewModel::class.java) {

    private val dotActive = R.drawable.ic_dot_active
    private val dotInactive = R.drawable.ic_dot_inactive

    private val leftSeatMax: Int = 3
    private val rightSeatMax: Int = 3
    private val fanMax: Int = 5

    private var leftSeatState: Int = 0
    private var frontWindowState: Boolean = false
    private var seatPositionState: Boolean = false
    private var fanState: Int = 0
    private var rearWindowState: Boolean = false
    private var rightSeatState: Int = 0

    private var leftTemp: Int = 20
    private var rightTemp: Int = 20

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLowerToolbarBinding =
        FragmentLowerToolbarBinding::inflate

    override fun setupUI() {
        prepareUpperButtons()
        prepareTemperatureBars()
    }

    private fun prepareUpperButtons() {
        binding.llFrontWindow.setOnClickListener {
            frontWindowState = !frontWindowState
            onToggleButtonClick(it, frontWindowState)
        }
        binding.llSeatPosition.setOnClickListener {
            seatPositionState = !seatPositionState
            onToggleButtonClick(it, seatPositionState)
        }
        binding.llRearWindow.setOnClickListener {
            rearWindowState = !rearWindowState
            onToggleButtonClick(it, rearWindowState)
        }

        binding.llLeftSeat.setOnClickListener {
            leftSeatState++
            if (leftSeatState > leftSeatMax) leftSeatState = 0
            setActiveDots(it as ViewGroup, leftSeatState)
        }

        binding.llRightSeat.setOnClickListener {
            rightSeatState++
            if (rightSeatState > rightSeatMax) rightSeatState = 0
            setActiveDots(it as ViewGroup, rightSeatState)
        }

        binding.llFan.setOnClickListener {
            fanState++
            if (fanState > fanMax) fanState = 0
            setActiveDots(it as ViewGroup, fanState)
        }

        setActiveDots(binding.llLeftSeat, leftSeatState)
        setActiveDots(binding.llRightSeat, rightSeatState)
        setActiveDots(binding.llFan, fanState)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun prepareTemperatureBars() {
        binding.skLsTemperature.setOnSeekBarListener {
            this.leftTemp = it
            setTemperatureLabel(binding.tvLsTemperature, this.leftTemp)
        }
        binding.skRsTemperature.setOnSeekBarListener {
            this.rightTemp = it
            setTemperatureLabel(binding.tvRsTemperature, this.rightTemp)
        }

        binding.btnDecreaseLsTemperature.setOnClickListener {
            if (this.leftTemp > 15) {
                this.leftTemp--
                setTemperatureLabel(binding.tvLsTemperature, this.leftTemp)
                binding.skLsTemperature.progress = this.leftTemp - 15
            }
        }
        binding.btnIncreaseLsTemperature.setOnClickListener {
            if (this.leftTemp < 26) {
                this.leftTemp++
                setTemperatureLabel(binding.tvLsTemperature, this.leftTemp)
                binding.skLsTemperature.progress = this.leftTemp - 15
            }
        }
        binding.btnDecreaseRsTemperature.setOnClickListener {
            if (this.rightTemp > 15) {
                this.rightTemp--
                setTemperatureLabel(binding.tvRsTemperature, this.rightTemp)
                binding.skRsTemperature.progress = this.rightTemp - 15
            }
        }
        binding.btnIncreaseRsTemperature.setOnClickListener {
            if (this.rightTemp < 26) {
                this.rightTemp++
                setTemperatureLabel(binding.tvRsTemperature, this.rightTemp)
                binding.skRsTemperature.progress = this.rightTemp - 15
            }
        }
        setTemperatureLabel(binding.tvLsTemperature, this.leftTemp)
        setTemperatureLabel(binding.tvRsTemperature, this.rightTemp)
        binding.skLsTemperature.progress = this.leftTemp - 15
        binding.skRsTemperature.progress = this.rightTemp - 15
    }

    private fun onToggleButtonClick(view: View, newValue: Boolean): Boolean {
        if (view is ViewGroup) {
            getImageView(view).setColorFilter(
                requireContext().getColor(if (newValue) R.color.text_white else R.color.text_gray)
            )
            return true
        }
        return false
    }

    private fun getImageView(viewGroup: ViewGroup): ImageView {
        return viewGroup.children.first { it is ImageView } as ImageView
    }

    private fun setActiveDots(viewGroup: ViewGroup, value: Int): Boolean {
        val layout = viewGroup.children.first { it is LinearLayout } as LinearLayout
        layout.forEachIndexed { ind, view ->
            if (ind < value) {
                (view as ImageView).setImageResource(dotActive)
                view.scaleX = 4f
                view.scaleY = 4f
            } else {
                (view as ImageView).setImageResource(dotInactive)
                view.scaleX = 1f
                view.scaleY = 1f
            }
        }

        (viewGroup.children.first { it is ImageView } as ImageView)
            .setColorFilter(requireContext().getColor(if (value == 0) R.color.text_gray else R.color.text_white))
        return true
    }

    private fun setTemperatureLabel(tempView: TextView, value: Int) {
        when {
            value < 16 -> {
                tempView.text = requireContext().getString(R.string.lo)
            }
            value > 25 -> {
                tempView.text = requireContext().getString(R.string.hi)
            }
            else -> {
                tempView.text = requireContext().getString(R.string.temperature_label, value)
            }
        }
    }
}
