package com.example.ebroapp.view.fragment.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ebroapp.R
import com.example.ebroapp.databinding.FragmentSettingsInteractiveBinding
import com.example.ebroapp.view.base.BaseFragment

class SettingsInteractiveFragment : BaseFragment<FragmentSettingsInteractiveBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSettingsInteractiveBinding =
        FragmentSettingsInteractiveBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}