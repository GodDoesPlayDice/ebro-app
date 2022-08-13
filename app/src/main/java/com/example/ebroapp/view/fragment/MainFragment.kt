package com.example.ebroapp.view.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.setFragmentResult
import com.example.ebroapp.R
import com.example.ebroapp.databinding.FragmentMainBinding
import com.example.ebroapp.view.activity.MainActivity.Companion.FRAGMENT_ID
import com.example.ebroapp.view.activity.MainActivity.Companion.REQUEST_KEY
import com.example.ebroapp.view.base.BaseFragment
import com.example.ebroapp.view.fragment.addresses.AddressesFragment
import com.example.ebroapp.view.fragment.map.MapFragment
import com.example.ebroapp.view.fragment.muisc.MusicFragment
import com.example.ebroapp.view.fragment.userinfo.UserInfoFragment
import com.example.ebroapp.view.fragment.weather.WeatherFragment

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(MainViewModel::class.java) {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding =
        FragmentMainBinding::inflate

    override fun setupUI() {
        childFragmentManager.commit { replace<UserInfoFragment>(R.id.fragmentUserInfo) }
        childFragmentManager.commit { replace<WeatherFragment>(R.id.fragmentWeather) }
        childFragmentManager.commit { replace<AddressesFragment>(R.id.fragmentLocation) }
        childFragmentManager.commit { replace<MapFragment>(R.id.fragmentMap) }
        childFragmentManager.commit { replace<MusicFragment>(R.id.fragmentMusic) }

        binding.fragmentMap.setOnClickListener {
            setFragmentResult(REQUEST_KEY, bundleOf(FRAGMENT_ID to it.id))
        }
        binding.fragmentMusic.setOnClickListener {
            setFragmentResult(REQUEST_KEY, bundleOf(FRAGMENT_ID to it.id))
        }

        viewModel.setOnMusicLoadingCompleteListener {
            childFragmentManager.commit { replace<MusicFragment>(R.id.fragmentMusic) }
        }

        viewModel.setOnLocationListener {
            childFragmentManager.commit { replace<WeatherFragment>(R.id.fragmentWeather) }
        }
    }
}