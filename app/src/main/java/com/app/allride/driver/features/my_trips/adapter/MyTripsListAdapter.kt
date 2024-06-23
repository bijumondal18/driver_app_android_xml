package com.app.allride.driver.features.my_trips.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.allride.driver.R
import com.app.allride.driver.api.Constants
import com.app.allride.driver.features.my_trips.model.TripData
import com.app.allride.driver.utils.AppHelper

class MyTripsListAdapter(
    private val tripList: List<TripData>,
    val context: Context,
    val onItemClick: ((TripData) -> Unit?)
) : RecyclerView.Adapter<MyTripsListAdapter.MyTripsListAdapterViewHolder>() {

    private lateinit var clickBounceAnimation: Animation


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTripsListAdapterViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_trip_list, parent, false)
        clickBounceAnimation = AnimationUtils.loadAnimation(context, R.anim.click_bouncing_effect)
        return MyTripsListAdapterViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyTripsListAdapter.MyTripsListAdapterViewHolder, position: Int) {
        holder.tripStatus.text = AppHelper.getDispatchActivityStatus(tripList[position].latest_dispatch_activity)
        holder.tripType.text = AppHelper.getTripType(tripList[position].solution_id)
//        holder.tripFare.text = tripList[position].route.passengersCount
        holder.sourceAddress.text = tripList[position].route.origin_address
        holder.destinationAddress.text = tripList[position].route.destination_address
//        holder.sourceTime.text =  AppHelper.getAnyTime(tripList[position].scheduled_on,Constants.API_TIME_FORMAT)

    }

    override fun getItemCount(): Int {
        return tripList.size
    }


    inner class MyTripsListAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tripStatus: TextView = view.findViewById<TextView>(R.id.tv_trip_status)
        val tripFare: TextView = view.findViewById<TextView>(R.id.tv_fare)
        val tripType: TextView = view.findViewById<TextView>(R.id.tv_trip_type)
        val sourceAddress: TextView = view.findViewById<TextView>(R.id.tv_source_address)
        val destinationAddress: TextView = view.findViewById<TextView>(R.id.tv_destination_address)
        val sourceTime: TextView = view.findViewById<TextView>(R.id.tv_source_time)
        val destinationTime: TextView = view.findViewById<TextView>(R.id.tv_destination_time)

        init {
            itemView.setOnClickListener {
               // itemView.startAnimation(clickBounceAnimation).run {
                    onItemClick.invoke(tripList[adapterPosition])
                //}
            }
        }

    }


}