package com.example.ebroapp.view.fragment.mapfull

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ebroapp.R
import com.example.ebroapp.databinding.FragmentMapFullBinding
import com.example.ebroapp.view.base.BaseFragment

class MapFullFragment : BaseFragment<FragmentMapFullBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMapFullBinding =
        FragmentMapFullBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}