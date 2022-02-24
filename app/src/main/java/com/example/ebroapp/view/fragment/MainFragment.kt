package com.example.ebroapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ebroapp.R
import com.example.ebroapp.view.base.BaseFragment
import com.example.ebroapp.databinding.FragmentMainBinding
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

        parentFragmentManager.beginTransaction().replace(R.id.fragmentUserInfo, UserInfoFragment()).commit()
        parentFragmentManager.beginTransaction().replace(R.id.fragmentWeather, WeatherFragment()).commit()
        parentFragmentManager.beginTransaction().replace(R.id.fragmentLocation,AddressesFragment()).commit()
        parentFragmentManager.beginTransaction().replace(R.id.fragmentMap, MapFragment()).commit()
        parentFragmentManager.beginTransaction().replace(R.id.fragmentMusic, MusicFragment()).commit()
    }
}