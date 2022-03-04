package com.example.ebroapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.setFragmentResult
import com.example.ebroapp.App
import com.example.ebroapp.R
import com.example.ebroapp.databinding.FragmentMainBinding
import com.example.ebroapp.view.base.BaseFragment
import com.example.ebroapp.view.fragment.addresses.AddressesFragment
import com.example.ebroapp.view.fragment.map.MapFragment
import com.example.ebroapp.view.fragment.muisc.MusicFragment
import com.example.ebroapp.view.fragment.userinfo.UserInfoFragment
import com.example.ebroapp.view.fragment.weather.WeatherFragment

class MainFragment : BaseFragment<FragmentMainBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding =
        FragmentMainBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.commit { replace<UserInfoFragment>(R.id.fragmentUserInfo) }
        parentFragmentManager.commit { replace<WeatherFragment>(R.id.fragmentWeather) }
        parentFragmentManager.commit { replace<AddressesFragment>(R.id.fragmentLocation) }
        parentFragmentManager.commit { replace<MapFragment>(R.id.fragmentMap) }
        parentFragmentManager.commit { replace<MusicFragment>(R.id.fragmentMusic) }

        binding.fragmentMap.setOnClickListener {
            setFragmentResult("requestKey", bundleOf("fragmentId" to it.id))
        }
        binding.fragmentMusic.setOnClickListener {
            setFragmentResult("requestKey", bundleOf("fragmentId" to it.id))
        }

        App.get().player.setOnMusicLoadingComplete {
            parentFragmentManager.commit { replace<MusicFragment>(R.id.fragmentMusic) }
        }
    }
}