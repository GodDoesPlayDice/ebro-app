package com.example.ebroapp.view.fragment.muisc

import android.content.Context.AUDIO_SERVICE
import android.media.AudioManager
import android.media.AudioManager.ADJUST_LOWER
import android.media.AudioManager.ADJUST_RAISE
import android.media.AudioManager.FLAG_PLAY_SOUND
import android.media.AudioManager.STREAM_MUSIC
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.ebroapp.R
import com.example.ebroapp.databinding.FragmentMusicBinding
import com.example.ebroapp.utils.music.VolumeObserver
import com.example.ebroapp.utils.setImageFromUri
import com.example.ebroapp.utils.setOnVolumeChangeListener
import com.example.ebroapp.utils.setTime
import com.example.ebroapp.view.base.BaseFragment

class MusicFragment :
    BaseFragment<FragmentMusicBinding, MusicViewModel>(MusicViewModel::class.java) {

    private val volumeObserver: VolumeObserver by lazy {
        VolumeObserver(requireContext(), Handler()) {
            binding.tvVolume.text = requireContext().getString(R.string.volume_percentage, it)
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMusicBinding =
        FragmentMusicBinding::inflate

    override fun setupUI() {
        viewModel.setOnPlayerStateChangeListener { progress, duration ->
            binding.pbMusic.progress = if (duration != 0) progress * PERCENT / duration else 0
            binding.tvTimer.setTime(duration / PERCENT * progress)
            binding.tvDuration.setTime(duration)
        }

        val audioManager = requireContext().getSystemService(AUDIO_SERVICE) as AudioManager
        val volume: Int = audioManager.getStreamVolume(STREAM_MUSIC) * PERCENT / MAX_VOLUME
        binding.tvVolume.text = requireContext().getString(R.string.volume_percentage, volume)
        requireContext().contentResolver.setOnVolumeChangeListener(volumeObserver)
        binding.imgVolumeUp.setOnClickListener {
            audioManager.adjustVolume(ADJUST_RAISE, FLAG_PLAY_SOUND)
        }
        binding.imgVolumeDown.setOnClickListener {
            audioManager.adjustVolume(ADJUST_LOWER, FLAG_PLAY_SOUND)
        }

        binding.btnPlay.isChecked = viewModel.isPlaying()
        binding.btnPlay.setOnCheckedChangeListener { _, isChecked ->
            viewModel.playPauseMusic(isChecked)
        }

        binding.imgNextSong.setOnClickListener {
            viewModel.nextSong()
            fillCurrentSong()
        }
        binding.imgPreviousSong.setOnClickListener {
            viewModel.previousSong()
            fillCurrentSong()
        }

        fillCurrentSong()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireContext().contentResolver.unregisterContentObserver(volumeObserver)
    }

    private fun fillCurrentSong() {
        viewModel.getCurrentSong { song ->
            binding.ivAlbumCover.setImageFromUri(song.uri)
            binding.tvName.text = song.name
            binding.tvSinger.text = song.singer
            binding.tvAlbum.text = song.album
        }
    }

    companion object {
        private const val PERCENT = 100
        private const val MAX_VOLUME = 15
    }
}
