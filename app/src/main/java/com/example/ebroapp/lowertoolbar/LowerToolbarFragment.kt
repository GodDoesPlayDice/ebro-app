package com.example.ebroapp.lowertoolbar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ebroapp.R
import com.example.ebroapp.base.BaseFragment
import com.example.ebroapp.databinding.FragmentLowerToolbarPlaceholderBinding
import com.example.ebroapp.databinding.FragmentUserInfoBinding

class LowerToolbarFragment : BaseFragment<FragmentLowerToolbarPlaceholderBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLowerToolbarPlaceholderBinding
            = FragmentLowerToolbarPlaceholderBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}