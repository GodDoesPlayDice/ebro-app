package com.example.ebroapp.view.fragment.settings

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.SeekBar
import android.widget.TextView
import android.widget.ToggleButton
import com.example.ebroapp.R
import com.example.ebroapp.databinding.FragmentSettingsBinding
import com.example.ebroapp.view.base.BaseFragment

class SettingsFragment :
    BaseFragment<FragmentSettingsBinding, SettingsViewModel>(SettingsViewModel::class.java) {

    private val lightsButtons = mutableListOf<ToggleButton>()
    private lateinit var thumbView: View

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSettingsBinding =
        FragmentSettingsBinding::inflate

    override fun setupUI() {
        prepareLightsButtons()
        prepareSeekBar()
    }

    private fun prepareLightsButtons() {
        lightsButtons.add(binding.tbLightsOff)
        lightsButtons.add(binding.tbLightsParking)
        lightsButtons.add(binding.tbLightsOn)
        lightsButtons.add(binding.tbLightsAuto)

        val listener: CompoundButton.OnCheckedChangeListener =
            object : CompoundButton.OnCheckedChangeListener {
                override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
                    if (isChecked) {
                        lightsButtons.filter { it != buttonView && it.isChecked }.forEach {
                            it.setOnCheckedChangeListener(null)
                            it.isChecked = false
                            it.setOnCheckedChangeListener(this)
                        }
                    } else {
                        buttonView.isChecked = true
                    }
                }

            }

        lightsButtons.forEach { button ->
            button.setOnCheckedChangeListener(listener)
        }
    }

    private fun prepareSeekBar() {
        thumbView = LayoutInflater.from(this.context)
            .inflate(R.layout.settings_seekbar_thumb, null, false)
        binding.sbDisplayBrightness.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                seekBar.thumb = getThumb(progress)
                seekBar.thumbOffset = 60
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        binding.sbDisplayBrightness.thumb = getThumb(0)
        binding.sbDisplayBrightness.thumbOffset = 60
    }

    private fun getThumb(progress: Int): Drawable {
        (thumbView.findViewById(R.id.tvProgress) as TextView).text =
            requireContext().getString(R.string.volume_percentage, progress + PERCENTAGE_STEP)
        thumbView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        val bitmap = Bitmap.createBitmap(
            thumbView.measuredWidth,
            thumbView.measuredHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        thumbView.layout(0, 0, thumbView.measuredWidth, thumbView.measuredHeight)
        thumbView.draw(canvas)
        return BitmapDrawable(resources, bitmap)
    }

    companion object {
        private const val PERCENTAGE_STEP = 10
    }
}
