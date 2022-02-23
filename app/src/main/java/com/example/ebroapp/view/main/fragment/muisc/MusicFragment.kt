package com.example.ebroapp.view.main.fragment.muisc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ebroapp.databinding.FragmentMusicBinding
import com.example.ebroapp.databinding.FragmentUserInfoBinding
import com.example.ebroapp.view.main.base.BaseFragment

class MusicFragment : BaseFragment<FragmentMusicBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMusicBinding =
        FragmentMusicBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}