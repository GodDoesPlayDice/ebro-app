package com.example.ebroapp.utils

import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.example.ebroapp.R

fun FragmentTransaction.setAnimation() =
    this.setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)

fun SeekBar.setOnSeekBarListener(addOp: (Int) -> Unit) {
    this.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            addOp(15 + progress)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
        }
    }
    )
}