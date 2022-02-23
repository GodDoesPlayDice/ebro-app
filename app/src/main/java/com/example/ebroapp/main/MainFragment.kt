package com.example.ebroapp.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ebroapp.R
import com.example.ebroapp.base.BaseFragment
import com.example.ebroapp.databinding.FragmentMainBinding
import com.example.ebroapp.userinfo.UserInfoFragment

class MainFragment : BaseFragment<FragmentMainBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding =
        FragmentMainBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.beginTransaction().replace(R.id.flWidget, UserInfoFragment()).commit()
    }


}