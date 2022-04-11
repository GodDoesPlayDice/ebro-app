package com.example.ebroapp.view.fragment.musicfull

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.ebroapp.domain.entity.song.SongListItem
import com.example.ebroapp.domain.repository.DomainRepository
import com.example.ebroapp.utils.SongMapper.toAdapter

class MusicFullViewModel(application: Application) : AndroidViewModel(application) {

    val songs = MutableLiveData<List<SongListItem>>()
    val insertedSongs = MutableLiveData<Pair<List<SongListItem>, Long>>()
    private val domainRepository = DomainRepository.obtain()

    fun getSongs() {
        songs.value = domainRepository.getSongs().toAdapter()
    }

    fun getInsertedSong(id: Long) {
        insertedSongs.value = Pair(domainRepository.getSongs().toAdapter(), id)
    }
}
