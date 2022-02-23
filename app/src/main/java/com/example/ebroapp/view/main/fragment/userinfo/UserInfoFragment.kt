package com.example.ebroapp.view.main.fragment.userinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ebroapp.databinding.FragmentUserInfoBinding
import com.example.ebroapp.view.main.base.BaseFragment

class UserInfoFragment : BaseFragment<FragmentUserInfoBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentUserInfoBinding =
        FragmentUserInfoBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}