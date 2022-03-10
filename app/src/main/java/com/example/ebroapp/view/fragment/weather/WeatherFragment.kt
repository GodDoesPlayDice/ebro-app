package com.example.ebroapp.view.fragment.weather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ebroapp.R
import com.example.ebroapp.databinding.FragmentWeatherBinding
import com.example.ebroapp.domain.repository.DomainRepository
import com.example.ebroapp.remote.repository.RemoteRepository
import com.example.ebroapp.utils.TimeUtil.getLongDay
import com.example.ebroapp.view.base.BaseFragment
import com.mapbox.geojson.Point
import com.squareup.picasso.Picasso
import io.reactivex.disposables.Disposable


class WeatherFragment : BaseFragment<FragmentWeatherBinding>() {

    private var disposable: Disposable? = null
    private val remoteRepository = RemoteRepository.obtain()
    private val domainRepository = DomainRepository.obtain()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentWeatherBinding =
        FragmentWeatherBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        domainRepository.getCurrentLocation()?.let {
            displayWeatherWidget(it)
        }
    }

    private fun displayWeatherWidget(point: Point) {
        disposable?.dispose()
        disposable = remoteRepository.getWeatherFull(point.latitude(), point.longitude())
            .subscribe(
                {
                    val current = it.current
                    val day1 = it.daily[1]
                    val day2 = it.daily[2]
                    val day3 = it.daily[3]
                    val day4 = it.daily[4]

                    Picasso.get()
                        .load("https://openweathermap.org/img/wn/${current.weather[0].icon}@2x.png")
                        .into(binding.ivWeather)

                    binding.tvDayOfWeek.text = getLongDay(current.dt)
                    binding.tvTemperature.text =
                        requireContext().getString(R.string.temperature, current.temp.toInt())

                    binding.tvDay1Label.text = getLongDay(day1.dt)
                    binding.tvDay1Temp.text =
                        requireContext().getString(R.string.temperature, day1.temp.eve.toInt())

                    binding.tvDay2Label.text = getLongDay(day2.dt)
                    binding.tvDay2Temp.text =
                        requireContext().getString(
                            R.string.temperature,
                            day2.temp.eve.toInt()
                        )

                    binding.tvDay3Label.text = getLongDay(day3.dt)
                    binding.tvDay3Temp.text =
                        requireContext().getString(
                            R.string.temperature,
                            day3.temp.eve.toInt()
                        )

                    binding.tvDay4Label.text = getLongDay(day4.dt)
                    binding.tvDay4Temp.text =
                        requireContext().getString(
                            R.string.temperature,
                            day4.temp.eve.toInt()
                        )
                },
                { Log.d("WEATHER", it.message, it) }
            )
    }

    override fun onDestroyView() {
        disposable?.dispose()
        super.onDestroyView()
    }
}