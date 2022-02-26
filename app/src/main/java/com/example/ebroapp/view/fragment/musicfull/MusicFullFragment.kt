package com.example.ebroapp.view.fragment.musicfull

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.example.ebroapp.App
import com.example.ebroapp.databinding.FragmentMusicFullBinding
import com.example.ebroapp.domain.entity.song.SongListItem.Companion.TYPE_SEPARATOR
import com.example.ebroapp.domain.entity.song.SongListItem.Companion.TYPE_SONG
import com.example.ebroapp.utils.getMusicListItems
import com.example.ebroapp.view.adapter.MusicAdapter
import com.example.ebroapp.view.base.BaseFragment
import com.squareup.picasso.Picasso


class MusicFullFragment : BaseFragment<FragmentMusicFullBinding>() {

    private val songAdapter by lazy {
        MusicAdapter {
            Picasso.get().load(it.albumCover).into(binding.ivAlbumCover)
            binding.tvName.text = it.name
            binding.tvSinger.text = it.singer
            binding.tvAlbum.text = it.album
            binding.btnFavorite.isChecked = it.isFavorites
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

        val gridLayoutManager = GridLayoutManager(requireContext(), 10).apply {
            spanSizeLookup = object : SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (songAdapter.getItemViewType(position)) {
                        TYPE_SONG -> 1
                        TYPE_SEPARATOR -> 10
                        else -> -1
                    }
                }
            }
        }
        binding.rvSongs.apply {
            layoutManager = gridLayoutManager
            adapter = songAdapter
        }
        songAdapter.addItems(getMusicListItems(requireContext()))
    }
}