package com.example.ebroapp.view.fragment.lowertoolbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ebroapp.view.base.BaseFragment
import com.example.ebroapp.databinding.FragmentLowerToolbarPlaceholderBinding

class LowerToolbarFragment : BaseFragment<FragmentLowerToolbarPlaceholderBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLowerToolbarPlaceholderBinding
            = FragmentLowerToolbarPlaceholderBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}