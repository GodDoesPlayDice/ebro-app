package com.example.ebroapp.view.fragment.musicfull

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ebroapp.domain.entity.song.Song
import com.example.ebroapp.domain.entity.song.SongListItem
import com.example.ebroapp.domain.repository.DomainRepository
import com.example.ebroapp.utils.Player
import com.example.ebroapp.utils.SongMapper.toAdapter
import javax.inject.Inject

class MusicFullViewModel @Inject constructor(
    private val domainRepository: DomainRepository,
    private val player: Player
) : ViewModel() {

    val songs = MutableLiveData<List<SongListItem>>()
    val insertedSongs = MutableLiveData<Pair<List<SongListItem>, Long>>()

    fun getSongs() {
        songs.value = domainRepository.getSongs().toAdapter()
    }

    fun getInsertedSong(id: Long) {
        insertedSongs.value = Pair(domainRepository.getSongs().toAdapter(), id)
    }

    fun setSongIsFavorite(id: Long, checked: Boolean) {
        domainRepository.setSongIsFavorite(id, checked)
    }

    fun nextSong(song: Song? = null) {
        song?.let { player.nextSong(it) } ?: player.nextSong()

    }

    fun setOnPlayerStateChangeListener(listener: (Int, Int) -> Unit) {
        player.setOnPlayerStateChangeListener { progress, duration ->
            listener.invoke(progress, duration)
        }
    }

    fun getCurrentSong(listener: (Song) -> Unit) {
        player.currentSong?.let { listener.invoke(it) }
    }

    fun isPlaying() = player.isPlaying()

    fun playMusic() {
        player.playMusic()
    }

    fun pauseMusic() {
        player.pauseMusic()
    }

    fun previousSong() {
        player.previousSong()
    }
}
