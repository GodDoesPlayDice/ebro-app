package com.example.ebroapp.view.fragment.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.ebroapp.R
import com.example.ebroapp.databinding.FragmentWeatherBinding
import com.example.ebroapp.utils.TimeUtil.DAY_1
import com.example.ebroapp.utils.TimeUtil.DAY_2
import com.example.ebroapp.utils.TimeUtil.DAY_3
import com.example.ebroapp.utils.TimeUtil.DAY_4
import com.example.ebroapp.utils.TimeUtil.getLongDay
import com.example.ebroapp.view.base.BaseFragment
import com.squareup.picasso.Picasso

class WeatherFragment :
    BaseFragment<FragmentWeatherBinding, WeatherViewModel>(WeatherViewModel::class.java) {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentWeatherBinding =
        FragmentWeatherBinding::inflate

    override fun setupUI() {
        viewModel.weather.observe(viewLifecycleOwner) {
            val current = it.current
            val day1 = it.daily[DAY_1]
            val day2 = it.daily[DAY_2]
            val day3 = it.daily[DAY_3]
            val day4 = it.daily[DAY_4]

            Picasso.get()
                .load("https://openweathermap.org/img/wn/${current.weather[0].icon}@2x.png")
                .into(binding.imgWeather)

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
        }

        viewModel.getCurrentLocation()
    }
}
