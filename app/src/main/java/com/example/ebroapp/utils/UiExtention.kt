package com.example.ebroapp.utils

import androidx.fragment.app.FragmentTransaction
import com.example.ebroapp.R

fun FragmentTransaction.setAnimation() =
    this.setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)