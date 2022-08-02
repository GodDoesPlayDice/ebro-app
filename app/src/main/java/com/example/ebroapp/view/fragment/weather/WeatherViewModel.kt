package com.example.ebroapp.view.fragment.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ebroapp.domain.repository.DomainRepository
import com.example.ebroapp.remote.entity.weather.FullWeather
import com.example.ebroapp.remote.repository.RemoteRepository
import com.example.ebroapp.utils.launchIO
import com.example.ebroapp.utils.withMain
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
                val response = remoteRepository.getWeatherFull(point.latitude(), point.longitude())
                withMain { weather.value = response }
            }
        }, { Timber.e(it) })
    }
}
