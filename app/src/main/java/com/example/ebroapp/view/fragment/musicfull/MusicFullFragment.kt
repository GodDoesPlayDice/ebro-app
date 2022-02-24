package com.example.ebroapp.view.fragment.musicfull

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ebroapp.databinding.FragmentMusicFullBinding
import com.example.ebroapp.view.base.BaseFragment


class MusicFullFragment(private val onClick: (Boolean) -> Unit) :
    BaseFragment<FragmentMusicFullBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMusicFullBinding =
        FragmentMusicFullBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPlay.setOnCheckedChangeListener { _, isChecked ->
            onClick.invoke(isChecked)
        }
    }
}