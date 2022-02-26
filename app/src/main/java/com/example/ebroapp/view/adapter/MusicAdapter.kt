package com.example.ebroapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ebroapp.databinding.ItemSongBinding
import com.example.ebroapp.domain.entity.Song
import com.squareup.picasso.Picasso

class MusicAdapter(private val onClick: (Song) -> Unit) :
    RecyclerView.Adapter<MusicAdapter.ViewHolder>() {

    private var items: List<Song> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemSongBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fill(items[position], onClick)
    }

    override fun getItemCount(): Int = items.size

    fun addItems(songs: List<Song>) {
        items = songs
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemSongBinding) : RecyclerView.ViewHolder(binding.root) {
        fun fill(item: Song, onClick: (Song) -> Unit) {
            Picasso.get()
                .load(item.image)
                .into(binding.ivImage)
            binding.ivImage.setOnClickListener { onClick.invoke(item) }
        }
    }
}
