package com.example.ebroapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ebroapp.R
import com.example.ebroapp.databinding.ItemSeparatorBinding
import com.example.ebroapp.databinding.ItemSongBinding
import com.example.ebroapp.domain.entity.song.Separator
import com.example.ebroapp.domain.entity.song.Song
import com.example.ebroapp.domain.entity.song.SongListItem
import com.example.ebroapp.domain.entity.song.SongListItem.Companion.TYPE_SONG
import com.example.ebroapp.utils.setImageBitmapOrPlaceholder

class MusicAdapter(private val onClick: (Song) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<SongListItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_SONG -> SongViewHolder(ItemSongBinding.inflate(inflater, parent, false))
            else -> SeparatorViewHolder(ItemSeparatorBinding.inflate(inflater, parent, false))
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

    class SongViewHolder(private val binding: ItemSongBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun fill(song: Song, onClick: (Song) -> Unit) {
            binding.ivAlbumCover.setImageBitmapOrPlaceholder(song.bitmap)
            binding.ivAlbumCover.setOnClickListener { onClick.invoke(song) }
        }
    }

    class SeparatorViewHolder(private val binding: ItemSeparatorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun fill(item: Separator) {
            binding.tvLabel.text = item.label
        }
    }
}
