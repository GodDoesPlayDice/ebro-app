package com.example.ebroapp.view.main.fragment.musicfull

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ebroapp.databinding.FragmentMusicFullBinding
import com.example.ebroapp.view.main.base.BaseFragment

class MusicFullFragment : BaseFragment<FragmentMusicFullBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMusicFullBinding =
        FragmentMusicFullBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}