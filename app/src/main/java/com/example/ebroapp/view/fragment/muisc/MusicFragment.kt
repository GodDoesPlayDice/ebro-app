package com.example.ebroapp.view.fragment.muisc

import android.content.Context.AUDIO_SERVICE
import android.media.AudioManager
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ebroapp.App
import com.example.ebroapp.R
import com.example.ebroapp.databinding.FragmentMusicBinding
import com.example.ebroapp.domain.entity.song.Song
import com.example.ebroapp.utils.VolumeObserver
import com.example.ebroapp.utils.setImageBitmapOrPlaceholder
import com.example.ebroapp.utils.setOnVolumeChangeListener
import com.example.ebroapp.utils.setTime
import com.example.ebroapp.view.base.BaseFragment


class MusicFragment : BaseFragment<FragmentMusicBinding>() {

    private lateinit var volumeObserver: VolumeObserver

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMusicBinding =
        FragmentMusicBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        App.get().playerDelegate.setOnPlayerStateChangeListener { progress, duration ->
            binding.pbMusic.progress = if (duration != 0) progress * 100 / duration else 0
            binding.tvTimer.setTime(duration / 100 * progress)
            binding.tvDuration.setTime(duration)
        }

        val audioManager = requireContext().getSystemService(AUDIO_SERVICE) as AudioManager
        val volume: Int = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) * 100 / 15
        binding.tvVolume.text = requireContext().getString(R.string.volume_percentage, volume)
        volumeObserver = VolumeObserver(requireContext(), Handler()) {
            binding.tvVolume.text = requireContext().getString(R.string.volume_percentage, it)
        }
        requireContext().contentResolver.setOnVolumeChangeListener(volumeObserver)
        binding.ivVolumeUp.setOnClickListener {
            audioManager.adjustVolume(
                AudioManager.ADJUST_RAISE,
                AudioManager.FLAG_PLAY_SOUND
            )
        }
        binding.ivVolumeDown.setOnClickListener {
            audioManager.adjustVolume(
                AudioManager.ADJUST_LOWER,
                AudioManager.FLAG_PLAY_SOUND
            )
        }

        binding.btnPlay.isChecked = App.get().playerDelegate.isPlaying()
        binding.btnPlay.setOnCheckedChangeListener { _, isChecked ->
            App.get().playerDelegate.playPauseMusic(isChecked)
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

    override fun onDestroyView() {
        super.onDestroyView()
        requireContext().contentResolver.unregisterContentObserver(volumeObserver)
    }

    private fun fillCurrentSong() {
        App.get().playerDelegate.currentSong?.let { song ->
            binding.ivAlbumCover.setImageBitmapOrPlaceholder(song.bitmap)
            binding.tvName.text = song.name
            binding.tvSinger.text = song.singer
            binding.tvAlbum.text = song.album
        }
    }
}