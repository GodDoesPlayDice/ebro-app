package com.example.ebroapp.view.fragment.musicfull

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.example.ebroapp.App
import com.example.ebroapp.databinding.FragmentMusicFullBinding
import com.example.ebroapp.domain.DomainRepository
import com.example.ebroapp.domain.entity.song.SongListItem.Companion.TYPE_SEPARATOR
import com.example.ebroapp.domain.entity.song.SongListItem.Companion.TYPE_SONG
import com.example.ebroapp.utils.Mapper.toAdapter
import com.example.ebroapp.utils.getMusicList
import com.example.ebroapp.utils.setTime
import com.example.ebroapp.view.adapter.MusicAdapter
import com.example.ebroapp.view.base.BaseFragment


class MusicFullFragment : BaseFragment<FragmentMusicFullBinding>() {

    private val songAdapter by lazy {
        MusicAdapter { song ->
            App.get().playerDelegate.nextSong(song)
            binding.btnPlay.isChecked = true
            fillCurrentSong()
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMusicFullBinding =
        FragmentMusicFullBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        App.get().playerDelegate.setOnPlayerStateChangeListener { progress, duration ->
            binding.pbMusic.progress = if (duration != 0) progress * 100 / duration else 0
            binding.tvTimer.setTime(duration / 100 * progress)
        }

        initAdapter()

        binding.btnFavorite.setOnClickListener {
            App.get().playerDelegate.currentSong?.id?.let {
                DomainRepository.obtain().setSongIsFavorite(it, binding.btnFavorite.isChecked)
                songAdapter.addItems(getMusicList(requireContext()).toAdapter())
            }
        }

        binding.btnPlay.apply {
            isChecked = App.get().playerDelegate.isPlaying()
            setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) App.get().playerDelegate.playMusic()
                else App.get().playerDelegate.pauseMusic()
            }
        }

        binding.btnNextSong.setOnClickListener {
            App.get().playerDelegate.nextSong()
            fillCurrentSong()
        }
        binding.btnPreviousSong.setOnClickListener {
            App.get().playerDelegate.previousSong()
            fillCurrentSong()
        }

        fillCurrentSong()
    }

    private fun initAdapter() {
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
        songAdapter.addItems(getMusicList(requireContext()).toAdapter())
    }

    private fun fillCurrentSong() {
        App.get().playerDelegate.currentSong?.let { song ->
            binding.ivAlbumCover.setImageBitmap(song.bitmap)
            binding.tvName.text = song.name
            binding.tvSinger.text = song.singer
            binding.tvAlbum.text = song.album
            binding.btnFavorite.isChecked = song.isFavorites
        }
    }
}