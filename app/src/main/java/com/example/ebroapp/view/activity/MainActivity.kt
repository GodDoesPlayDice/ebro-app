package com.example.ebroapp.view.activity

import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.ebroapp.R
import com.example.ebroapp.databinding.ActivityMainBinding
import com.example.ebroapp.receiver.ActionPowerReceiver
import com.example.ebroapp.view.base.BaseActivity
import com.example.ebroapp.view.fragment.MainFragment
import com.example.ebroapp.view.fragment.lowertoolbar.LowerToolbarFragment
import com.example.ebroapp.view.fragment.map.MapFragment
import com.example.ebroapp.view.fragment.musicfull.MusicFullFragment
import com.example.ebroapp.view.fragment.settings.SettingsInteractiveFragment
import com.google.android.material.button.MaterialButtonToggleGroup


// наследуемся от базового класса
class MainActivity : BaseActivity<ActivityMainBinding>() {

    // хз почему, но для этой залупы viewBinding не пашет
    private val btnToggleGroup by lazy { findViewById<MaterialButtonToggleGroup>(R.id.btnToggleGroup) }
    private val actionPowerReceiver by lazy { ActionPowerReceiver() }

    // нужно для viewBinding. ActivityMainBinding автогенерируется
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ставим фрагменты на место
        supportFragmentManager.commit { replace<LowerToolbarFragment>(R.id.fragmentLowerToolbar) }

        btnToggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btnHome -> {
                        openMainFragment()
                    }
                    R.id.btnMap -> {
                        supportFragmentManager.commit { replace<MapFragment>(R.id.fragmentMain) }
                    }
                    R.id.btnMusic -> {
                        supportFragmentManager.commit { replace<MusicFullFragment>(R.id.fragmentMain) }
                    }
                    R.id.btnSettings -> {
                        supportFragmentManager.commit { replace<SettingsInteractiveFragment>(R.id.fragmentMain) }
                    }
                }
            }
        }

        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_POWER_DISCONNECTED);
            addAction(Intent.ACTION_POWER_CONNECTED)
        }
        registerReceiver(actionPowerReceiver, filter)
    }

    override fun onResume() {
        super.onResume()

        btnToggleGroup.check(R.id.btnHome)
        openMainFragment()
    }

    override fun onDestroy() {
        super.onDestroy()

        unregisterReceiver(actionPowerReceiver)
    }

    // нужно чтоб нажатие на back не срабатывало
    override fun onBackPressed() {}

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        val manager = applicationContext.getSystemService(AUDIO_SERVICE) as AudioManager
        return when (event.keyCode) {
            KeyEvent.KEYCODE_VOLUME_UP -> {
                manager.adjustStreamVolume(
                    AudioManager.STREAM_MUSIC,
                    AudioManager.ADJUST_RAISE,
                    AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE
                )
                true
            }
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                manager.adjustStreamVolume(
                    AudioManager.STREAM_MUSIC,
                    AudioManager.ADJUST_LOWER,
                    AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE
                )
                true
            }
            else -> super.onKeyDown(keyCode, event)
        }
    }

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
}