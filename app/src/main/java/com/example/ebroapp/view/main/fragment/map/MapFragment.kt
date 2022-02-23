package com.example.ebroapp.view.main.fragment.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ebroapp.databinding.FragmentMapBinding
import com.example.ebroapp.databinding.FragmentUserInfoBinding
import com.example.ebroapp.view.main.base.BaseFragment

class MapFragment : BaseFragment<FragmentMapBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMapBinding =
        FragmentMapBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}