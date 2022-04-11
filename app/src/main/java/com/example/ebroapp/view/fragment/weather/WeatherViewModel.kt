package com.example.ebroapp.view.fragment.weather

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.ebroapp.domain.repository.DomainRepository
import com.example.ebroapp.remote.entity.weather.FullWeather
import com.example.ebroapp.remote.repository.RemoteRepository
import io.reactivex.disposables.Disposable

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    val weather = MutableLiveData<FullWeather>()

    private var disposable: Disposable? = null
    private val remoteRepository = RemoteRepository.obtain()
    private val domainRepository = DomainRepository.obtain()

    fun getCurrentLocation() {
        domainRepository.getCurrentLocation()?.let { point ->
            disposable?.dispose()
            disposable = remoteRepository.getWeatherFull(point.latitude(), point.longitude())
                .subscribe(
                    {
                        weather.value = it
                    },
                    { Log.d("WEATHER", it.message, it) }
                )
        }

    }
}
