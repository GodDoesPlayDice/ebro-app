package com.example.ebroapp.view.fragment.addresses

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.ebroapp.domain.repository.DomainRepository

class AddressesViewModel(application: Application) : AndroidViewModel(application) {

    val address = MutableLiveData<String>()
    val addresses = MutableLiveData<List<String>>()

    private val domainRepository = DomainRepository.obtain()

    fun getAddresses() {
        addresses.value = domainRepository.getAddresses()
    }

    fun setOnAddressChangeListener() {
        domainRepository.setOnAddressesChangeListener {
            address.value = it
        }
    }
}
