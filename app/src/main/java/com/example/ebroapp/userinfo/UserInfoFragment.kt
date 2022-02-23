package com.example.ebroapp.userinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ebroapp.R
import com.example.ebroapp.base.BaseFragment
import com.example.ebroapp.databinding.ActivityMainBinding
import com.example.ebroapp.databinding.FragmentUserInfoBinding

class UserInfoFragment : BaseFragment<FragmentUserInfoBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentUserInfoBinding
            = FragmentUserInfoBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}