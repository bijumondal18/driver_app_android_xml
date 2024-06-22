package com.app.allride.driver.features.my_trips.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.allride.driver.R
import com.app.allride.driver.api.ApiInterface
import com.app.allride.driver.api.Constants
import com.app.allride.driver.databinding.ActivityMyTripsBinding
import com.app.allride.driver.features.my_trips.adapter.MyTripsListAdapter
import com.app.allride.driver.features.my_trips.model.TripListResponseModel
import com.app.allride.driver.utils.AppHelper
import com.app.allride.driver.utils.AppPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyTripsActivity : AppCompatActivity() {

    companion object {
        private const val TAG: String = "MY_TRIPS_ACTIVITY"
    }

    private lateinit var binding: ActivityMyTripsBinding
    private lateinit var mPreference: AppPreference
    private lateinit var mRecyclerView: RecyclerView


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

        setupToolbar()

        mPreference = AppPreference(this@MyTripsActivity)

        mRecyclerView = binding.rvTripList
        mRecyclerView.layoutManager = LinearLayoutManager(this@MyTripsActivity, LinearLayoutManager.VERTICAL,false)
        fetchMyTrips()

    }


    private fun setupToolbar() {
        setSupportActionBar(binding.toolbarMyTrips)
        val actionBar = supportActionBar
        actionBar!!.title = "My Trips"
        actionBar.setDisplayHomeAsUpEnabled(true)
        binding.toolbarMyTrips.setNavigationIcon(R.drawable.ic_back_arrow)
    }

    private fun fetchMyTrips() {
        if (AppHelper.isConnectedToInternet(this@MyTripsActivity)) {

            // Show Loading dialog
            AppHelper.showLoading(this@MyTripsActivity)
            val call: Call<TripListResponseModel> = ApiInterface.create()
                .tripList(Constants.API_CONTENT_TYPE, Constants.API_CONTENT_TYPE, Constants.DEVICE_TYPE, mPreference.getTenantId()!!, "Bearer ${mPreference.getAuth()!!}", mPreference.getUserId())
            call.enqueue(object : Callback<TripListResponseModel> {
                override fun onResponse(
                    call: Call<TripListResponseModel>,
                    response: Response<TripListResponseModel>
                ) {
                    // Hide Loading dialog
                    AppHelper.hideLoading()
                    if (response.isSuccessful) {
                        AppHelper.showDebugLog(TAG, "${response.body()}")
                        val mData = response.body()!!
                        if (mData != null && mData.data.isNotEmpty()) {

                            val myTripsAdapter = MyTripsListAdapter(mData.data,this@MyTripsActivity)
                            mRecyclerView.adapter = myTripsAdapter
                            myTripsAdapter.notifyDataSetChanged()

                        } else {
                            AppHelper.toastLong(this@MyTripsActivity, "No Trips Found")
                        }

                    } else {
                        AppHelper.toastLong(this@MyTripsActivity, "Something went wrong!!!")
                    }
                }

                override fun onFailure(call: Call<TripListResponseModel>, t: Throwable) {
                    AppHelper.hideLoading()
                    AppHelper.toastLong(this@MyTripsActivity, "${t.message}")
                    AppHelper.showDebugLog(TAG, "Failure Response : ${t.message}")
                }
            })

        } else {
            AppHelper.toastNetworkError(this@MyTripsActivity)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}