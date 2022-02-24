package com.example.ebroapp.view.main.fragment.addresses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ebroapp.R
import com.example.ebroapp.databinding.FragmentAddressesBinding
import com.example.ebroapp.view.main.base.BaseFragment

class AddressesFragment : BaseFragment<FragmentAddressesBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddressesBinding
        = FragmentAddressesBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}