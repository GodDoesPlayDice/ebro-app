package com.example.ebroapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Toast
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.ebroapp.base.BaseActivity
import com.example.ebroapp.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButtonToggleGroup

class MainActivity : BaseActivity<ActivityMainBinding>() {

    // хз почему, но для этой залупы viewBinding не пашет
    private val btnToggleGroup by lazy { findViewById<MaterialButtonToggleGroup>(R.id.btnToggleGroup) }

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hideSystemUi()

        btnToggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btnHome -> {
                        showToast("Home")
                    }
                    R.id.btnLocation -> {
                        showToast("Location")
                    }
                    R.id.btnMusic -> {
                        showToast("Music")
                    }
                    R.id.btnSettings -> {
                        showToast("Settings")
                    }
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {}

    private fun hideSystemUi() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, binding.root).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}