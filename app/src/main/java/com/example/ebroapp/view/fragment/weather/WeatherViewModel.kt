package com.example.ebroapp.view.fragment.weather

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.ebroapp.domain.repository.DomainRepository
import com.example.ebroapp.remote.entity.weather.FullWeather
import com.example.ebroapp.remote.repository.RemoteRepository
import com.example.ebroapp.utils.launchIO
import kotlinx.coroutines.GlobalScope
import timber.log.Timber

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    val weather = MutableLiveData<FullWeather>()

    private val remoteRepository = RemoteRepository.obtain()
    private val domainRepository = DomainRepository.obtain()

    fun getCurrentLocation() {
        GlobalScope.launchIO({
            domainRepository.getCurrentLocation()?.let { point ->
                weather.value = remoteRepository.getWeatherFull(point.latitude(), point.longitude())
            }
        }, { Timber.e(it) })
    }
}
