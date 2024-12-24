package com.forerunner.carzsiswafinal.Driver;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.forerunner.carzsiswafinal.Adapter.DriverActivitiesAdapter;
import com.forerunner.carzsiswafinal.Model.firebase.ManageRideQuery;
import com.forerunner.carzsiswafinal.R;

public class DriverOngoingTripsFrag extends Fragment {

    private RecyclerView recyclerViewOngoingRides;
    ManageRideQuery manageRideQuery = ManageRideQuery.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_driver_ongoing_history, container, false);

        recyclerViewOngoingRides = view.findViewById(R.id.recycler_driver_passenger_requests);
        manageRideQuery.getDriverRide("available",
                rides ->{
                    DriverActivitiesAdapter actAdapter = new DriverActivitiesAdapter(rides,requireActivity());
                    recyclerViewOngoingRides.setAdapter(actAdapter);
                }
                ,
                exception -> {
                    // Handle failure to get driver data
                    Log.e("DriverOngoingTripsFrag", "Error getting driver Rides: " + exception.getMessage());});

        return view;

    }


}