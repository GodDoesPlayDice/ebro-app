package com.example.ebroapp.view.fragment.addresses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ebroapp.databinding.FragmentAddressesBinding
import com.example.ebroapp.domain.repository.DomainRepository
import com.example.ebroapp.view.adapter.AddressesAdapter
import com.example.ebroapp.view.base.BaseFragment

class AddressesFragment : BaseFragment<FragmentAddressesBinding>() {

    private val addressesAdapter by lazy { AddressesAdapter() }
    private val viewManager by lazy {
        LinearLayoutManager(requireContext()).apply {
            reverseLayout = true
            stackFromEnd = true
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddressesBinding =
        FragmentAddressesBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvAddresses.apply {
            layoutManager = viewManager
            adapter = addressesAdapter
        }

        addressesAdapter.addItems(DomainRepository.obtain().getAddresses())

        DomainRepository.obtain().setOnAddressesChangeListener {
            addressesAdapter.addItem(it)
            viewManager.scrollToPosition(it.lastIndex)
        }
    }
}