package com.app.allride.driver.features.bus_trip_module.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.allride.driver.R
import com.app.allride.driver.api.ApiInterface
import com.app.allride.driver.api.Constants
import com.app.allride.driver.databinding.FragmentBusBookingBottomSheetBinding
import com.app.allride.driver.databinding.FragmentHomeBinding
import com.app.allride.driver.features.my_trips.ui.MyTripsActivity
import com.app.allride.driver.features.my_trips.ui.MyTripsActivity.Companion
import com.app.allride.driver.features.trip_details.model.TripDetailsResponseModel
import com.app.allride.driver.features.trip_details.ui.TripDetailsActivity
import com.app.allride.driver.utils.AppHelper
import com.app.allride.driver.utils.AppPreference
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BusBookingBottomSheetFragment : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "BUS_BOOKING_BOTTOM_SHEET_FRAGMENT"
    }

    private lateinit var binding: FragmentBusBookingBottomSheetBinding
    private  var tripId: Int? = null
    private lateinit var mPreference: AppPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBusBookingBottomSheetBinding.inflate(layoutInflater, container, false)
        mPreference = AppPreference(requireContext())
        tripId = arguments?.getInt("trip_id")!!
        AppHelper.showDebugLog(TAG, "trip id - $tripId")
        fetchTripDetails(tripId!!)
        return binding.root.rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun fetchTripDetails(tripId: Int) {
        if ( AppHelper.isConnectedToInternet(requireContext())) {

            // Show Loading dialog
            AppHelper.showLoading(requireContext())
            val call: Call<TripDetailsResponseModel> = ApiInterface.create()
                .tripDetails(Constants.API_CONTENT_TYPE, Constants.API_CONTENT_TYPE, Constants.DEVICE_TYPE, mPreference.getTenantId()!!, "Bearer ${mPreference.getAuth()!!}", tripId)
            call.enqueue(object : Callback<TripDetailsResponseModel> {
                override fun onResponse(
                    call: Call<TripDetailsResponseModel>,
                    response: Response<TripDetailsResponseModel>
                ) {
                    // Hide Loading dialog
                    AppHelper.hideLoading()
                    if (response.isSuccessful) {
                        AppHelper.showDebugLog(TAG, "${response.body()}")
                        val mData = response.body()!!
                        if (mData != null && mData.data != null) {

                            binding.tvSourceAddress.text = mData.data.route.origin_address

                        } else {
                            context?.let { AppHelper.toastLong(it, "Trip Data Not Found") }
                        }

                    } else {
                        context?.let { AppHelper.toastLong(it, "Something went wrong!!!") }
                    }
                }

                override fun onFailure(call: Call<TripDetailsResponseModel>, t: Throwable) {
                    AppHelper.hideLoading()
                    context?.let { AppHelper.toastLong(it, "${t.message}") }
                    AppHelper.showDebugLog(TAG, "Failure Response : ${t.message}")
                }
            })

        } else {
            context?.let { AppHelper.toastNetworkError(it) }
        }
    }

}