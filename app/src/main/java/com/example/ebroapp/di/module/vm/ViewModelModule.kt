package com.example.ebroapp.di.module.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ebroapp.view.activity.MainViewModel
import com.example.ebroapp.view.fragment.addresses.AddressesViewModel
import com.example.ebroapp.view.fragment.map.MapViewModel
import com.example.ebroapp.view.fragment.muisc.MusicViewModel
import com.example.ebroapp.view.fragment.musicfull.MusicFullViewModel
import com.example.ebroapp.view.fragment.weather.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainActivityViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(com.example.ebroapp.view.fragment.MainViewModel::class)
    fun bindMainFragmentViewModel(viewModel: com.example.ebroapp.view.fragment.MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddressesViewModel::class)
    fun bindAddressesViewModel(viewModel: AddressesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    fun bindMapViewModel(viewModel: MapViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MusicViewModel::class)
    fun bindMusicViewModel(viewModel: MusicViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MusicFullViewModel::class)
    fun bindMusicFullViewModel(viewModel: MusicFullViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    fun bindWeatherViewModel(viewModel: WeatherViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
