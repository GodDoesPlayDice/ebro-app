package com.example.ebroapp.view.activity

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.media.AudioManager
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResultListener
import com.example.ebroapp.App
import com.example.ebroapp.R
import com.example.ebroapp.databinding.ActivityMainBinding
import com.example.ebroapp.receiver.ActionPowerReceiver
import com.example.ebroapp.view.base.BaseActivity
import com.example.ebroapp.view.fragment.MainFragment
import com.example.ebroapp.view.fragment.lowertoolbar.LowerToolbarFragment
import com.example.ebroapp.view.fragment.map.MapFragment
import com.example.ebroapp.view.fragment.musicfull.MusicFullFragment
import com.example.ebroapp.view.fragment.settings.SettingsFragment
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
        supportFragmentManager.commit { replace(R.id.fragmentLowerToolbar, LowerToolbarFragment()) }

        btnToggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btnHome -> {
                        openMainFragment()
                    }
                    R.id.btnMap -> {
                        supportFragmentManager.commit {
                            replace(R.id.fragmentMain, MapFragment())
                        }
                    }
                    R.id.btnMusic -> {
                        supportFragmentManager.commit {
                            replace(
                                R.id.fragmentMain,
                                MusicFullFragment()
                            )
                        }
                    }
                    R.id.btnSettings -> {
                        supportFragmentManager.commit {
                            replace(
                                R.id.fragmentMain,
                                SettingsFragment()
                            )
                        }
                    }
                }
            }
        }

        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_POWER_DISCONNECTED)
            addAction(Intent.ACTION_POWER_CONNECTED)
        }
        registerReceiver(actionPowerReceiver, filter)

        val hasLocationPermission =
            ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION)
        val hasStoragePermission =
            ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE)

        if (hasLocationPermission != PackageManager.PERMISSION_GRANTED ||
            hasStoragePermission != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions()
        }
    }

    private fun requestPermissions() {
        val permissions = arrayOf(READ_EXTERNAL_STORAGE, ACCESS_FINE_LOCATION)
        ActivityCompat.requestPermissions(this, permissions, PERMISSIONS_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty()
            && grantResults.size == 2
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
            && grantResults[1] == PackageManager.PERMISSION_GRANTED
        ) {
            App.get().player.setPlayList(this)
            recreate()
        } else {
            requestPermissions()
        }
    }

    override fun onResume() {
        super.onResume()

        btnToggleGroup.check(R.id.btnHome)
        openMainFragment()
    }

    override fun onPause() {
        super.onPause()

        App.get().player.pauseMusic()
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
        val mainFragment = MainFragment()
        supportFragmentManager.commit {
            replace(R.id.fragmentMain, mainFragment)
        }
        mainFragment.setFragmentResultListener(REQUEST_KEY) { _, bundle ->
            when (bundle.getInt(FRAGMENT_ID)) {
                R.id.fragmentMap -> {
                    btnToggleGroup.check(R.id.btnMap)
                }
                R.id.fragmentMusic -> {
                    btnToggleGroup.check(R.id.btnMusic)
                }
            }
        }
    }

    companion object {
        const val REQUEST_KEY = "REQUEST_KEY"
        const val FRAGMENT_ID = "FRAGMENT_ID"
        private const val PERMISSIONS_REQUEST_CODE = 7530
    }
}