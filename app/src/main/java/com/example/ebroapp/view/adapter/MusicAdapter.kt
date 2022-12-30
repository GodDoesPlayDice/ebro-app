package com.example.ebroapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.Separator
import com.example.domain.entity.Song
import com.example.domain.entity.SongListItem
import com.example.domain.entity.SongListItem.Companion.TYPE_SONG
import com.example.ebroapp.databinding.ListItemSeparatorBinding
import com.example.ebroapp.databinding.ListItemSongBinding
import com.example.ebroapp.utils.setImageFromUri

class MusicAdapter(private val onClick: (Song) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<SongListItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_SONG -> SongViewHolder(ListItemSongBinding.inflate(inflater, parent, false))
            else -> SeparatorViewHolder(ListItemSeparatorBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_SONG -> (holder as SongViewHolder).fill(items[position] as Song, onClick)
            else -> (holder as SeparatorViewHolder).fill(items[position] as Separator)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = items[position].type

    fun addItems(songs: List<SongListItem>) {
        items = songs
        notifyDataSetChanged()
    }

    fun changeItem(songs: List<SongListItem>, id: Long) {
        val fromPosition = items.indexOfFirst { it is Song && it.id == id }
        val toPosition = songs.indexOfFirst { it is Song && it.id == id }
        notifyItemMoved(fromPosition, toPosition)
        items = songs
    }

    class SongViewHolder(private val binding: ListItemSongBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun fill(song: Song, onClick: (Song) -> Unit) {
            binding.ivAlbumCover.setImageFromUri(song.uri)
            binding.ivAlbumCover.setOnClickListener { onClick.invoke(song) }
        }
    }

    class SeparatorViewHolder(private val binding: ListItemSeparatorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun fill(item: Separator) {
            binding.tvLabel.text = item.label
        }
    }
}
