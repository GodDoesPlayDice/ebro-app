package com.example.ebroapp.view.activity.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import com.example.ebroapp.R
import com.example.ebroapp.databinding.ActivitySplashBinding
import com.example.ebroapp.view.activity.MainActivity
import com.example.ebroapp.view.base.BaseActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity :
    BaseActivity<ActivitySplashBinding, SplashViewModel>(SplashViewModel::class.java) {

    override val bindingInflater: (LayoutInflater) -> ActivitySplashBinding =
        ActivitySplashBinding::inflate

    override fun setupUI() {
        val path = "android.resource://" + packageName + "/" + R.raw.splash
        binding.vvSplash.apply {
            setVideoURI(Uri.parse(path))
            start()
            setOnCompletionListener {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
        }
    }
}