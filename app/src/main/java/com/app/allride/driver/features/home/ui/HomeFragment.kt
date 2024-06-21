package com.app.allride.driver.features.home.ui

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.content.res.Resources
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import com.app.allride.driver.MainActivity
import com.app.allride.driver.R
import com.app.allride.driver.databinding.FragmentHomeBinding
import com.app.allride.driver.utils.AppHelper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapCapabilities
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions

class HomeFragment : Fragment(), OnMapReadyCallback {

    companion object {
        private const val TAG: String = "HOME_FRAGMENT"
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    private lateinit var mMap: GoogleMap
    private lateinit var mapView: MapView
    private lateinit var binding: FragmentHomeBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var mapOptions: GoogleMapOptions


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        mapView = binding.mainMapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        return binding.root.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.enableEdgeToEdge()

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        binding.toggleSwitch.setOnCheckedChangeListener { _, isChecked ->
            binding.tvStatus.text =
                if (isChecked) "Ready to take next task" else "Ready to take new task?"
            binding.tvOnlineOfflineTitle.text =
                if (isChecked) "You are Online" else "You are Offline"
            binding.tvOnlineOfflineDesc.text =
                if (isChecked) "You are now ready to take new request." else "Go online to receive request."
        }

        // Hamburger icon pressed to open drawer from home fragment
        binding.btnHamburger.setOnClickListener {
            val activity = requireActivity() as MainActivity
            activity.openDrawer()
        }

    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mapOptions = GoogleMapOptions()
        mapOptions.compassEnabled(false)
            .mapType(GoogleMap.MAP_TYPE_HYBRID)
            .rotateGesturesEnabled(true)
            .tiltGesturesEnabled(true)

        try {
            mMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    context as Activity,
                    R.raw.map_style
                )
            )
            mMap.isBuildingsEnabled = true
            AppHelper.showDebugLog(TAG, "Map Style Changed")

        } catch (e: Resources.NotFoundException) {
            AppHelper.showDebugLog(TAG, e.toString())
        }

        // Check for location permissions
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }

        mMap.isMyLocationEnabled = true

        // Get the current location
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val currentLatLng = LatLng(location.latitude, location.longitude)
                    mMap.addMarker(MarkerOptions().position(currentLatLng).title("You are here"))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 16f))
                }
            }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                onMapReady(mMap)
            }
        }
    }
}