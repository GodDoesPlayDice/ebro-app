package com.example.ebroapp.view.fragment.map

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.example.ebroapp.databinding.FragmentMapBinding
import com.example.ebroapp.view.base.BaseFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng


class MapFragment : BaseFragment<FragmentMapBinding>() {

    private var map: GoogleMap? = null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMapBinding =
        FragmentMapBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mapView.onCreate(savedInstanceState);

        binding.mapView.onResume()

        try {
            MapsInitializer.initialize(requireContext())
        } catch (e: Exception) {
            e.printStackTrace()
        }

        binding.mapView.getMapAsync {
            map = it.apply {
                uiSettings.isMyLocationButtonEnabled = false
                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        ACCESS_FINE_LOCATION
                    ) != PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(
                        requireContext(),
                        ACCESS_COARSE_LOCATION
                    ) != PERMISSION_GRANTED
                ) {
                    isMyLocationEnabled = true
                    moveCamera(CameraUpdateFactory.newLatLng(LatLng(43.1, -87.9)))
                }
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