package com.example.ebroapp.view.fragment.musicfull

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ebroapp.App
import com.example.ebroapp.databinding.FragmentMusicFullBinding
import com.example.ebroapp.utils.getMusicList
import com.example.ebroapp.view.adapter.MusicAdapter
import com.example.ebroapp.view.base.BaseFragment
import com.squareup.picasso.Picasso

class MusicFullFragment : BaseFragment<FragmentMusicFullBinding>() {

    private val adapter by lazy {
        MusicAdapter {
            Picasso.get()
                .load(it.image)
                .into(binding.ivImage)
            binding.tvName.text = it.name
            binding.tvSinger.text = it.singer
            binding.tvAlbum.text = it.album
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMusicFullBinding =
        FragmentMusicFullBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPlay.isChecked = App.get().isPlaying()
        binding.btnPlay.setOnCheckedChangeListener { _, _ ->
            App.get().playPauseMusic()
        }

        binding.rvSongs.adapter = adapter
        adapter.addItems(getMusicList())
    }
}