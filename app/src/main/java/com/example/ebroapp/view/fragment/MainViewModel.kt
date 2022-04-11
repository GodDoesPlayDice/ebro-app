package com.example.ebroapp.view.fragment

import android.app.Application
import android.location.Location
import androidx.lifecycle.AndroidViewModel
import com.example.ebroapp.domain.repository.DomainRepository
import com.mapbox.geojson.Point

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = DomainRepository.obtain()

    fun addCurrentLocation(location: Location) {
        repository.addCurrentLocation(
            Point.fromLngLat(location.longitude, location.latitude)
        )
    }
}
