package com.example.ebroapp.view.fragment.userinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.ebroapp.databinding.FragmentUserInfoBinding
import com.example.ebroapp.view.base.BaseFragment

class UserInfoFragment :
    BaseFragment<FragmentUserInfoBinding, UserInfoViewModel>(UserInfoViewModel::class.java) {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentUserInfoBinding =
        FragmentUserInfoBinding::inflate

    override fun setupUI() {}
}
