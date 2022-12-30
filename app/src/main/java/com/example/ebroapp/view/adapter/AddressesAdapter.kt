package com.example.ebroapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ebroapp.databinding.ListItemAddressesBinding

class AddressesAdapter : RecyclerView.Adapter<AddressesAdapter.ViewHolder>() {

    private var items: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ListItemAddressesBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fill(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun addItems(addresses: List<String>) {
        items = addresses.toMutableList()
        notifyDataSetChanged()
    }

    fun addItem(addresses: String) {
        items.add(addresses)
        notifyItemInserted(items.size - 1)
    }

    class ViewHolder(private val binding: ListItemAddressesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun fill(address: String) {
            binding.tvAddresses.text = address
        }
    }
}
