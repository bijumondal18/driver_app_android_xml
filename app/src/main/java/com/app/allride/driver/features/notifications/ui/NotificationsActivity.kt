package com.app.allride.driver.features.notifications.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.allride.driver.R
import com.app.allride.driver.databinding.ActivityMyTripsBinding
import com.app.allride.driver.databinding.ActivityNotificationsBinding
import com.app.allride.driver.utils.AppPreference

class NotificationsActivity : AppCompatActivity() {

    companion object {
        private const val TAG: String = "MY_TRIPS_ACTIVITY"
    }

    private lateinit var binding: ActivityNotificationsBinding
    private lateinit var mPreference: AppPreference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.layoutNotifications) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mPreference = AppPreference(this@NotificationsActivity)

        setupToolbar()

        fetchNotifications()

    }

    private fun setupToolbar(){
        setSupportActionBar(binding.toolbarNotifications)
        val actionBar = supportActionBar
        actionBar!!.title = "Notifications"
        actionBar.setDisplayHomeAsUpEnabled(true)
        binding.toolbarNotifications.setNavigationIcon(R.drawable.ic_back_arrow)
    }

    private fun fetchNotifications(){

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}