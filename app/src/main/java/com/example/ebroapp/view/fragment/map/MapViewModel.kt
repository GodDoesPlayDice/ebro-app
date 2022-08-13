package com.example.ebroapp.view.fragment.map

import androidx.lifecycle.ViewModel
import com.example.domain.entity.LocalPoint
import com.example.domain.repository.DomainRepository
import com.mapbox.geojson.Point
import javax.inject.Inject

class MapViewModel @Inject constructor(
    private val domainRepository: DomainRepository
) : ViewModel() {

    fun addCurrentLocation(point: Point) {
        domainRepository.addCurrentLocation(LocalPoint(point.latitude(), point.latitude()))
    }

    fun getCurrentLocation(): Point? {
        return domainRepository.getCurrentLocation()?.let {
            Point.fromLngLat(it.longitude, it.latitude)
        }
    }

    fun getDestinationLocation(): Point? {
        return domainRepository.getDestinationLocation()?.let {
            Point.fromLngLat(it.longitude, it.latitude)
        }
    }

    fun addDestinationLocation(point: Point) {
        domainRepository.addDestinationLocation(LocalPoint(point.latitude(), point.latitude()))
    }

    fun addAddress(address: String) {
        domainRepository.addAddress(address)
    }

    fun removeDestinationLocation() {
        domainRepository.removeDestinationLocation()
    }
}
