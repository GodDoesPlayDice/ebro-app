package com.example.ebroapp.view.main.fragment.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ebroapp.R
import com.example.ebroapp.databinding.FragmentWeatherBinding
import com.example.ebroapp.view.main.base.BaseFragment


class WeatherFragment : BaseFragment<FragmentWeatherBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentWeatherBinding =
        FragmentWeatherBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}