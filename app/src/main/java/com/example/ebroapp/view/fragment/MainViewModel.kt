package com.example.ebroapp.view.fragment

import androidx.lifecycle.ViewModel
import com.example.ebroapp.domain.repository.DomainRepository
import com.example.ebroapp.utils.map.LocationListener
import com.example.ebroapp.utils.music.Player
import com.mapbox.geojson.Point
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val domainRepository: DomainRepository,
    private val player: Player,
    private val locationListener: LocationListener
) : ViewModel() {

    fun setOnMusicLoadingCompleteListener(listener: () -> Unit) {
        player.setOnMusicLoadingComplete { listener.invoke() }
    }

    fun setOnLocationListener(listener: () -> Unit) {
        locationListener.setOnLocationListener { location ->
            domainRepository.addCurrentLocation(
                Point.fromLngLat(location.longitude, location.latitude)
            )
            listener.invoke()
        }
    }
}
