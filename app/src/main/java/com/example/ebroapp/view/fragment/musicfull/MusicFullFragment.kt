package com.example.ebroapp.view.fragment.musicfull

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.example.ebroapp.App
import com.example.ebroapp.databinding.FragmentMusicFullBinding
import com.example.ebroapp.domain.entity.song.Song
import com.example.ebroapp.domain.entity.song.SongListItem.Companion.TYPE_SEPARATOR
import com.example.ebroapp.domain.entity.song.SongListItem.Companion.TYPE_SONG
import com.example.ebroapp.utils.Mapper.toAdapter
import com.example.ebroapp.utils.Mapper.toSongs
import com.example.ebroapp.utils.getMusicList
import com.example.ebroapp.view.adapter.MusicAdapter
import com.example.ebroapp.view.base.BaseFragment
import com.squareup.picasso.Picasso


class MusicFullFragment : BaseFragment<FragmentMusicFullBinding>() {

    private val songListItem by lazy { getMusicList(requireContext()).toAdapter().toMutableList()}
    private var adapterPosition = -1

    private val songAdapter by lazy {
        MusicAdapter { song, position ->
            Picasso.get().load(song.albumCover).into(binding.ivAlbumCover)
            binding.tvName.text = song.name
            binding.tvSinger.text = song.singer
            binding.tvAlbum.text = song.album
            binding.btnFavorite.isChecked = song.isFavorites
            adapterPosition = position
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMusicFullBinding =
        FragmentMusicFullBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPlay.apply {
            isChecked = App.get().isPlaying()
            setOnCheckedChangeListener { _, _ -> App.get().playPauseMusic() }
        }

        initAdapter()

        binding.btnFavorite.setOnCheckedChangeListener { _, isChecked ->
            if (adapterPosition != -1) {
                val song = songListItem[adapterPosition] as Song
                song.isFavorites = isChecked
                songListItem[adapterPosition] = song
                songAdapter.addItems(songListItem.toSongs().toAdapter())
            }
        }
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
        songAdapter.addItems(songListItem)
    }
}