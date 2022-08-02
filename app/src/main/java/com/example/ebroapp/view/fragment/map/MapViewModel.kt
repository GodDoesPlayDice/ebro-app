package com.example.ebroapp.view.fragment.map

import androidx.lifecycle.ViewModel
import com.example.ebroapp.domain.repository.DomainRepository
import com.mapbox.geojson.Point
import javax.inject.Inject

class MapViewModel @Inject constructor(
    private val domainRepository: DomainRepository
) : ViewModel() {

    fun addCurrentLocation(point: Point) {
        domainRepository.addCurrentLocation(point)
    }

    fun getCurrentLocation() = domainRepository.getCurrentLocation()

    fun getDestinationLocation() = domainRepository.getDestinationLocation()

    fun addDestinationLocation(point: Point) {
        domainRepository.addDestinationLocation(point)
    }

    fun addAddress(address: String) {
        domainRepository.addAddress(address)
    }

    fun removeDestinationLocation() {
        domainRepository.removeDestinationLocation()
    }
}
