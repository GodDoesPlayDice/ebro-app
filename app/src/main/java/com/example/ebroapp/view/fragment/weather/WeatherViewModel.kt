package com.example.ebroapp.view.fragment.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.DomainRepository
import com.example.ebroapp.utils.launchIO
import com.example.ebroapp.utils.withMain
import com.example.network.entity.FullWeather
import com.example.network.repository.RemoteRepository
import timber.log.Timber
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val domainRepository: DomainRepository,
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    val weather = MutableLiveData<FullWeather>()

    fun getCurrentLocation() {
        viewModelScope.launchIO({
            domainRepository.getCurrentLocation()?.let { point ->
                val response = remoteRepository.getWeatherFull(point.longitude, point.latitude)
                withMain { weather.value = response }
            }
        }, { Timber.e(it) })
    }
}
