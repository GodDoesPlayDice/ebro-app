package com.example.ebroapp.view.fragment.addresses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ebroapp.databinding.FragmentAddressesBinding
import com.example.ebroapp.view.adapter.AddressesAdapter
import com.example.ebroapp.view.base.BaseFragment

class AddressesFragment :
    BaseFragment<FragmentAddressesBinding, AddressesViewModel>(AddressesViewModel::class.java) {

    private val addressesAdapter by lazy { AddressesAdapter() }
    private val viewManager by lazy {
        LinearLayoutManager(requireContext()).apply {
            reverseLayout = true
            stackFromEnd = true
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddressesBinding =
        FragmentAddressesBinding::inflate

    override fun setupUI() {
        binding.rvAddresses.apply {
            layoutManager = viewManager
            adapter = addressesAdapter
        }

        viewModel.addresses.observe(viewLifecycleOwner) {
            addressesAdapter.addItems(it)
        }

        viewModel.address.observe(viewLifecycleOwner) {
            addressesAdapter.addItem(it)
            viewManager.scrollToPosition(addressesAdapter.itemCount - 1)
        }

        viewModel.getAddresses()
        viewModel.setOnAddressChangeListener()
    }
}
