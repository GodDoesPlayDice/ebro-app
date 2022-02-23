package com.example.ebroapp.view.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ebroapp.R
import com.example.ebroapp.view.main.base.BaseFragment
import com.example.ebroapp.databinding.FragmentMainBinding
import com.example.ebroapp.view.main.fragment.map.MapFragment
import com.example.ebroapp.view.main.fragment.muisc.MusicFragment
import com.example.ebroapp.view.main.fragment.userinfo.UserInfoFragment

class MainFragment : BaseFragment<FragmentMainBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding =
        FragmentMainBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.beginTransaction().replace(R.id.fragmentUserInfo, UserInfoFragment()).commit()
        parentFragmentManager.beginTransaction().replace(R.id.fragmentMap, MapFragment()).commit()
        parentFragmentManager.beginTransaction().replace(R.id.fragmentMusic, MusicFragment()).commit()
    }
}