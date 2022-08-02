package com.example.ebroapp.view.fragment.addresses

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ebroapp.domain.repository.DomainRepository
import javax.inject.Inject

class AddressesViewModel @Inject constructor(
    private val domainRepository: DomainRepository
) : ViewModel() {

    val address = MutableLiveData<String>()
    val addresses = MutableLiveData<List<String>>()

    fun getAddresses() {
        addresses.value = domainRepository.getAddresses()
    }

    fun setOnAddressChangeListener() {
        domainRepository.setOnAddressesChangeListener {
            address.value = it
        }
    }
}
