package com.example.ebroapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ebroapp.databinding.ItemAddressesBinding

class AddressesAdapter : RecyclerView.Adapter<AddressesAdapter.ViewHolder>() {

    private var items: List<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemAddressesBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fill(items[position])
    }

    override fun getItemCount(): Int = items.size


    fun addItems(addresses: List<String>) {
        items = addresses
        notifyDataSetChanged()
    }

    fun addItem(addresses: List<String>) {
        items = addresses
        notifyItemInserted(items.size - 1)
    }

    class ViewHolder(private val binding: ItemAddressesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun fill(address: String) {
            binding.tvAddressesItem.text = address
        }
    }
}
