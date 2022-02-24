package com.example.ebroapp.view.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.ebroapp.R
import com.example.ebroapp.databinding.ActivityMainBinding
import com.example.ebroapp.view.base.BaseActivity
import com.example.ebroapp.view.fragment.MainFragment
import com.example.ebroapp.view.fragment.lowertoolbar.LowerToolbarFragment
import com.example.ebroapp.view.fragment.mapfull.MapFullFragment
import com.example.ebroapp.view.fragment.musicfull.MusicFullFragment
import com.example.ebroapp.view.fragment.settings.SettingsFragment
import com.google.android.material.button.MaterialButtonToggleGroup

// наследуемся от базового класса
class MainActivity : BaseActivity<ActivityMainBinding>() {

    // хз почему, но для этой залупы viewBinding не пашет
    private val btnToggleGroup by lazy { findViewById<MaterialButtonToggleGroup>(R.id.btnToggleGroup) }

    // нужно для viewBinding. ActivityMainBinding автогенерируется
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideSystemUi()
        // ставим фрагменты на место
        openMainFragment()
        supportFragmentManager.commit { replace<LowerToolbarFragment>(R.id.fragmentLowerToolbar) }

        btnToggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btnHome -> {
                        openMainFragment()
                    }
                    R.id.btnMap -> {
                        supportFragmentManager.commit { replace<MapFullFragment>(R.id.fragmentMain) }
                    }
                    R.id.btnMusic -> {
                        supportFragmentManager.commit { replace<MusicFullFragment>(R.id.fragmentMain) }
                    }
                    R.id.btnSettings -> {
                        supportFragmentManager.commit { replace<SettingsFragment>(R.id.fragmentMain) }
                    }
                }
            }
        }
    }

    // нужно чтоб нажатие на back не срабатывало
    override fun onBackPressed() {}

    private fun openMainFragment() {
        supportFragmentManager.commit {
            replace(R.id.fragmentMain, MainFragment { id ->
                when (id) {
                    R.id.fragmentMap -> {
                        btnToggleGroup.check(R.id.btnMap)
                    }
                    R.id.fragmentMusic -> {
                        btnToggleGroup.check(R.id.btnMusic)
                    }
                }
            })
        }
    }

    // скрываем весь системный UI и расширяем приложение через status bar
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