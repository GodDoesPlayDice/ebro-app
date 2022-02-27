package com.example.ebroapp.view.fragment.lowertoolbar

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.children
import androidx.core.view.forEachIndexed
import com.example.ebroapp.R
import com.example.ebroapp.databinding.FragmentLowerToolbarBinding
import com.example.ebroapp.view.base.BaseFragment

class LowerToolbarFragment : BaseFragment<FragmentLowerToolbarBinding>() {

    class CustomSeekListener(private val tempView: TextView, private val addOp: (Int) -> Unit) : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//            tempView.text = (15 + progress).toString() + "\\u00B0"
            addOp(15 + progress)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
        }
    }

    private val dotActive = R.drawable.ic_dot_active
    private val dotInactive = R.drawable.ic_dot_inactive

    private val leftSeatMax: Int = 3
    private val rightSeatMax: Int = 3
    private val fanMax: Int = 5

    private var leftSeatState: Int = 0
    private var frontWindowState: Boolean = true
    private var seatPositionState: Boolean = true
    private var fanState: Int = 2
    private var rearWindowState: Boolean = true
    private var rightSeatState: Int = 0

    private var leftTemp: Int = 19
    private var rightTemp: Int = 24


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLowerToolbarBinding =
        FragmentLowerToolbarBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareUpperButtons(view)
        prepareTemperatureBars(view)
    }

    private fun prepareUpperButtons(view: View) {
        binding.llFrontWindow
            .setOnClickListener {
                frontWindowState = !frontWindowState
                onToggleButtonClick(it, frontWindowState)
            }
        binding.llSeatPosition
            .setOnClickListener {
                seatPositionState = !seatPositionState
                onToggleButtonClick(it, seatPositionState)
            }
        binding.llRearWindow
            .setOnClickListener {
                rearWindowState = !rearWindowState
                onToggleButtonClick(it, rearWindowState)
            }

        binding.llLeftSeat
            .setOnClickListener {
                leftSeatState ++
                if (leftSeatState > leftSeatMax) leftSeatState = 0
                setActiveDots(it as ViewGroup, leftSeatState)
            }

        binding.llRightSeat
            .setOnClickListener {
                rightSeatState ++
                if (rightSeatState > rightSeatMax) rightSeatState = 0
                setActiveDots(it as ViewGroup, rightSeatState)
            }

        binding.llFan
            .setOnClickListener {
                fanState ++
                if (fanState > fanMax) fanState = 0
                setActiveDots(it as ViewGroup, fanState)
            }

        setActiveDots(binding.llFan, fanState)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun prepareTemperatureBars(view: View) {
        val leftTv: TextView = view.findViewById(R.id.tvLsTemperature)
        val rightTv: TextView = view.findViewById(R.id.tvRsTemperature)
        val leftSeek: SeekBar = view.findViewById(R.id.skLsTemperature)
        val rightSeek: SeekBar = view.findViewById(R.id.skRsTemperature)
        leftSeek.setOnSeekBarChangeListener(CustomSeekListener(leftTv) {
            this.leftTemp = it
            setTemeratureLabel(leftTv, this.leftTemp)
        })
        rightSeek.setOnSeekBarChangeListener(CustomSeekListener(rightTv) {
            this.rightTemp = it
            setTemeratureLabel(rightTv, this.rightTemp)
        })

        binding.btnDecreaseLsTemperature.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                if (this.leftTemp > 15) {
                    this.leftTemp --
                    setTemeratureLabel(leftTv, this.leftTemp)
                    leftSeek.progress = this.leftTemp - 15
                }
            }
            true
        }
        binding.btnIncreaseLsTemperature.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                if (this.leftTemp < 25) {
                    this.leftTemp ++
                    setTemeratureLabel(leftTv, this.leftTemp)
                    leftSeek.progress = this.leftTemp - 15
                }
            }
            true
        }
        binding.btnDecreaseRsTemperature.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                if (this.rightTemp > 15) {
                    this.rightTemp --
                    setTemeratureLabel(rightTv, this.rightTemp)
                    rightSeek.progress = this.rightTemp - 15
                }
            }
            true
        }
        binding.btnIncreaseRsTemperature.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                if (this.rightTemp < 25) {
                    this.rightTemp ++
                    setTemeratureLabel(rightTv, this.rightTemp)
                    rightSeek.progress = this.rightTemp - 15
                }
            }
            true
        }
        setTemeratureLabel(leftTv, this.leftTemp)
        setTemeratureLabel(rightTv, this.rightTemp)
        leftSeek.progress = this.leftTemp - 15
        rightSeek.progress = this.rightTemp - 15
    }

    private fun onToggleButtonClick(v: View, newValue: Boolean): Boolean {
        if (v is ViewGroup) {
            getImageView(v).let {
                it.setColorFilter(resources.getColor(if (newValue) R.color.text_white else R.color.text_gray));
            }
            return true
        }
        return false
    }

    private fun getImageView(v: ViewGroup): ImageView {
        return v.children.first { it is ImageView } as ImageView
    }

    private fun setActiveDots(v: ViewGroup, value: Int): Boolean {
        var layout = v.children.first {it is LinearLayout } as LinearLayout
        layout.forEachIndexed { ind, v ->
            if (ind < value) {
                (v as ImageView).setImageResource(dotActive)
                v.scaleX = 4f
                v.scaleY = 4f
            } else {
                (v as ImageView).setImageResource(dotInactive)
                v.scaleX = 1f
                v.scaleY = 1f
            }

        }
        return true
    }

    private fun setTemeratureLabel(tempView: TextView, value: Int) {
        tempView.text = "$value\u00B0"
    }
}