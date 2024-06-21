package com.app.allride.driver.features.my_trips.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.allride.driver.R
import com.app.allride.driver.databinding.ActivityMainBinding
import com.app.allride.driver.databinding.ActivityMyTripsBinding
import com.app.allride.driver.utils.AppPreference

class MyTripsActivity : AppCompatActivity() {

    companion object{
        private const val TAG: String = "MY_TRIPS_ACTIVITY"
    }

    private lateinit var binding: ActivityMyTripsBinding
    private lateinit var mPreference: AppPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMyTripsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.layoutMyTrips) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mPreference = AppPreference(this@MyTripsActivity)

        setupToolbar()
    }


    private fun setupToolbar(){
        setSupportActionBar(binding.toolbarMyTrips)
        val actionBar = supportActionBar
        actionBar!!.title = "My Trips"
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}