package com.app.allride.driver.features.bus_trip_module.ui

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.content.res.Resources
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.allride.driver.R
import com.app.allride.driver.databinding.ActivityBusBookingBinding
import com.app.allride.driver.utils.AppHelper
import com.app.allride.driver.utils.AppPreference
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior

class BusBookingActivity : AppCompatActivity(), OnMapReadyCallback {
    companion object {
        private const val TAG: String = "BUS_BOOKING_ACTIVITY"
        private const val LOCATION_PERMISSION_REQUEST_CODE = 2
    }

    private lateinit var binding: ActivityBusBookingBinding
    private lateinit var mPreference: AppPreference

    private lateinit var googleMap: GoogleMap
    private lateinit var mapView: MapView
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var mapOptions: GoogleMapOptions
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var bottomSheetBusBooking: ConstraintLayout

    private var tripId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBusBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mPreference = AppPreference(this@BusBookingActivity)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this@BusBookingActivity)

        mapView = binding.mainMapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        if (intent.hasExtra("trip_id")) {
            tripId = intent.getIntExtra("trip_id", 0)
        }


        bottomSheetBusBooking = findViewById(R.id.bottomSheetBusBooking)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetBusBooking)


        //#3 Listening to State Changes of BottomSheet
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                val bundle = Bundle()
                bundle.putInt("trip_id",tripId)
                val fragment:BusBookingBottomSheetFragment = BusBookingBottomSheetFragment()
                fragment.arguments = bundle
            }
        })


    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onMapReady(gMap: GoogleMap) {
        googleMap = gMap

        mapOptions = GoogleMapOptions()
        mapOptions.compassEnabled(false)
            .mapType(GoogleMap.MAP_TYPE_HYBRID)
            .rotateGesturesEnabled(true)
            .tiltGesturesEnabled(true)

        try {
            googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this,
                    R.raw.map_style
                )
            )
            googleMap.isBuildingsEnabled = true
            AppHelper.showDebugLog(TAG, "Map Style Changed")

        } catch (e: Resources.NotFoundException) {
            AppHelper.showDebugLog(TAG, e.toString())
        }

        // Check for location permissions
        if (ActivityCompat.checkSelfPermission(
                this@BusBookingActivity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this@BusBookingActivity,
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

        googleMap.isMyLocationEnabled = true

        // Get the current location
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val currentLatLng = LatLng(location.latitude, location.longitude)
                    googleMap.addMarker(MarkerOptions().position(currentLatLng).title("You are here"))
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 16f))
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
                onMapReady(googleMap)
            }
        }
    }

}