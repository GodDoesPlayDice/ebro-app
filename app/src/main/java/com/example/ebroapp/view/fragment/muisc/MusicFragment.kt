package com.example.ebroapp.view.fragment.muisc

import android.content.Context.AUDIO_SERVICE
import android.media.AudioManager
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.ebroapp.App
import com.example.ebroapp.R
import com.example.ebroapp.databinding.FragmentMusicBinding
import com.example.ebroapp.utils.VolumeObserver
import com.example.ebroapp.utils.setImageFromUri
import com.example.ebroapp.utils.setOnVolumeChangeListener
import com.example.ebroapp.utils.setTime
import com.example.ebroapp.view.base.BaseFragment


class MusicFragment :
    BaseFragment<FragmentMusicBinding, MusicViewModel>(MusicViewModel::class.java) {

    private val player = App.get().player
    private lateinit var volumeObserver: VolumeObserver

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMusicBinding =
        FragmentMusicBinding::inflate


    override fun setupUI() {
        player.setOnPlayerStateChangeListener { progress, duration ->
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

        binding.btnPlay.isChecked = player.isPlaying()
        binding.btnPlay.setOnCheckedChangeListener { _, isChecked ->
            player.playPauseMusic(isChecked)
        }

        binding.btnNextSong.setOnClickListener {
            player.nextSong()
            fillCurrentSong()
        }
        binding.btnPreviousSong.setOnClickListener {
            player.previousSong()
            fillCurrentSong()
        }

        fillCurrentSong()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireContext().contentResolver.unregisterContentObserver(volumeObserver)
    }

    private fun fillCurrentSong() {
        player.currentSong?.let { song ->
            binding.ivAlbumCover.setImageFromUri(song.uri)
            binding.tvName.text = song.name
            binding.tvSinger.text = song.singer
            binding.tvAlbum.text = song.album
        }
    }
}