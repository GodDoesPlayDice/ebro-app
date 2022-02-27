package com.example.ebroapp.view.fragment.lowertoolbar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.children
import androidx.core.view.forEach
import androidx.core.view.forEachIndexed
import com.example.ebroapp.R
import com.example.ebroapp.databinding.FragmentLowerToolbarBinding
import com.example.ebroapp.view.base.BaseFragment
import com.example.ebroapp.databinding.FragmentLowerToolbarPlaceholderBinding

class LowerToolbarFragment : BaseFragment<FragmentLowerToolbarBinding>() {

    class CustomSeekListener(private val tempView: TextView) : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            tempView.text = (15 + progress).toString()
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


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLowerToolbarBinding =
        FragmentLowerToolbarBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareUpperButtons(view)
        prepareTemperatureBars(view)
    }

    private fun prepareUpperButtons(view: View) {
        view.findViewById<LinearLayout>(R.id.llFrontWindow)
            .setOnClickListener {
                frontWindowState = !frontWindowState
                onToggleButtonClick(it, frontWindowState)
            }
        view.findViewById<LinearLayout>(R.id.llSeatPosition)
            .setOnClickListener {
                seatPositionState = !seatPositionState
                onToggleButtonClick(it, seatPositionState)
            }
        view.findViewById<LinearLayout>(R.id.llRearWindow)
            .setOnClickListener {
                rearWindowState = !rearWindowState
                onToggleButtonClick(it, rearWindowState)
            }

        view.findViewById<LinearLayout>(R.id.llLeftSeat)
            .setOnClickListener {
                leftSeatState ++
                if (leftSeatState > leftSeatMax) leftSeatState = 0
                setActiveDots(it as ViewGroup, leftSeatState)
            }

        view.findViewById<LinearLayout>(R.id.llRightSeat)
            .setOnClickListener {
                rightSeatState ++
                if (rightSeatState > rightSeatMax) rightSeatState = 0
                setActiveDots(it as ViewGroup, rightSeatState)
            }

        view.findViewById<LinearLayout>(R.id.llFan)
            .setOnClickListener {
                fanState ++
                if (fanState > fanMax) fanState = 0
                setActiveDots(it as ViewGroup, fanState)
            }

        setActiveDots(view.findViewById<LinearLayout>(R.id.llFan), fanState)
    }

    private fun prepareTemperatureBars(view: View) {
        val leftTemp: TextView = view.findViewById(R.id.tv_ls_temperature)
        val rightTemp: TextView = view.findViewById(R.id.tv_rs_temperature)
        val leftSeek: SeekBar = view.findViewById(R.id.sk_ls_temperature)
        val rightSeek: SeekBar = view.findViewById(R.id.sk_rs_temperature)
        leftSeek.setOnSeekBarChangeListener(CustomSeekListener(leftTemp))
        rightSeek.setOnSeekBarChangeListener(CustomSeekListener(rightTemp))

        view.findViewById<ImageButton>(R.id.btn_decrease_ls_temperature).setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                var curValue = leftTemp.text.toString().toInt()
                if (curValue > 15) {
                    leftTemp.text = (curValue - 1).toString()
                    leftSeek.progress = curValue - 1 - 15
                }
            }
            true
        }
        view.findViewById<ImageButton>(R.id.btn_increase_ls_temperature).setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                var curValue = leftTemp.text.toString().toInt()
                if (curValue < 25) {
                    leftTemp.text = (curValue + 1).toString()
                    leftSeek.progress = curValue + 1 - 15
                }
            }
            true
        }
        view.findViewById<ImageButton>(R.id.btn_decrease_rs_temperature).setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                var curValue = rightTemp.text.toString().toInt()
                if (curValue > 15) {
                    rightTemp.text = (curValue - 1).toString()
                    rightSeek.progress = curValue - 1 - 15
                }
            }
            true
        }
        view.findViewById<ImageButton>(R.id.btn_increase_rs_temperature).setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                var curValue = rightTemp.text.toString().toInt()
                if (curValue < 25) {
                    rightTemp.text = (curValue + 1).toString()
                    rightSeek.progress = curValue + 1 - 15
                }
            }
            true
        }
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
}