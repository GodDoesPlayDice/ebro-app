package com.example.ebroapp.view.fragment.mapfull

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.example.ebroapp.App
import com.example.ebroapp.databinding.FragmentMapFullBinding
import com.example.ebroapp.view.base.BaseFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng

class MapFullFragment : BaseFragment<FragmentMapFullBinding>() {


    private var map: GoogleMap? = null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMapFullBinding =
        FragmentMapFullBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPlay.isChecked = App.get().playerDelegate.isPlaying()
        binding.btnPlay.setOnCheckedChangeListener { _, isChecked ->
            App.get().playerDelegate.playPauseMusic(isChecked)
        }

        binding.mapView.onCreate(savedInstanceState);

        binding.mapView.onResume()

        try {
            MapsInitializer.initialize(requireContext())
        } catch (e: Exception) {
            e.printStackTrace()
        }

        binding.mapView.getMapAsync { googleMap ->
            map = googleMap
            map?.apply {
                uiSettings.isMyLocationButtonEnabled = false
                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    isMyLocationEnabled = true
                }
                moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(41.385063, 2.173404), 1f))
            }
        }
    }

    override fun onResume() {
        binding.mapView.onResume()
        super.onResume()
    }


    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onDestroyView() {
        binding.mapView.onDestroy()
        super.onDestroyView()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }
}