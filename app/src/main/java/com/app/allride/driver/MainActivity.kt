package com.app.allride.driver

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.app.allride.driver.databinding.ActivityMainBinding
import com.app.allride.driver.features.home.ui.HomeFragment
import com.app.allride.driver.features.home.ui.OnlineOfflineBottomSheetFragment
import com.app.allride.driver.features.my_trips.ui.MyTripsActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.material.navigation.NavigationView
import java.util.concurrent.Delayed

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG: String = "MAIN_ACTIVITY"
    }

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMainBinding

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    //    private lateinit var toolbar: Toolbar
    private lateinit var onlineOfflineBottomSheetFragment: OnlineOfflineBottomSheetFragment

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var toggleSwitch: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        configureDrawer()

        // Open HomeFragment by default
        if (savedInstanceState == null) {
            openFragment(HomeFragment())
        }
    }

    private fun initViews() {
        drawerLayout = binding.mainDrawerLayout
        navView = binding.navView
//        toggleSwitch = binding.toggleSwitch
        onlineOfflineBottomSheetFragment = OnlineOfflineBottomSheetFragment()
    }

    private fun configureDrawer() {
//        val toggle = ActionBarDrawerToggle(
//            this, drawerLayout, toolbar,
//            R.string.navigation_drawer_open, R.string.navigation_drawer_close
//        )
//        drawerLayout.addDrawerListener(toggle)
//        toggle.syncState()

        navView.setNavigationItemSelectedListener { menuitem ->
            when (menuitem.itemId) {
                R.id.nav_home -> {
                    openFragment(HomeFragment())
                }

                R.id.nav_my_trips -> {
                    startActivity(Intent(this@MainActivity, MyTripsActivity::class.java))
                }

                R.id.nav_notification -> {

                }

                R.id.nav_help_center -> {

                }

                R.id.nav_settings -> {

                }

                R.id.nav_logout -> {

                }
            }
            drawerLayout.closeDrawers()
            true
        }

    }


    fun openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START)
    }

    private fun openFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.contentFrame.id, fragment)
        fragmentTransaction.commit()
    }

    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


}