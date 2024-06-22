package com.app.allride.driver.features.trip_details.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.allride.driver.R
import com.app.allride.driver.databinding.ActivityMyTripsBinding
import com.app.allride.driver.databinding.ActivityTripDetailsBinding
import com.app.allride.driver.utils.AppHelper
import com.app.allride.driver.utils.AppPreference

class TripDetailsActivity : AppCompatActivity() {
    companion object {
        private const val TAG: String = "TRIP_DETAILS_ACTIVITY"
    }

    private lateinit var binding: ActivityTripDetailsBinding
    private lateinit var mPreference: AppPreference
    private lateinit var toolbarTitle: String
    private var tripId: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTripDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.layoutTripDetails) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        mPreference = AppPreference(this@TripDetailsActivity)


        if(intent.hasExtra("trip_reference_number")){
            toolbarTitle = intent.getStringExtra("trip_reference_number")!!
        }
        if(intent.hasExtra("trip_id")){
            tripId = intent.getIntExtra("trip_id",0)
        }

        setupToolbar()

    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbarTripDetails)
        val actionBar = supportActionBar
        actionBar!!.title = "Trip #${toolbarTitle.substring(toolbarTitle.length-4)}"
        actionBar.setDisplayHomeAsUpEnabled(true)
        binding.toolbarTripDetails.setNavigationIcon(R.drawable.ic_back_arrow)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}