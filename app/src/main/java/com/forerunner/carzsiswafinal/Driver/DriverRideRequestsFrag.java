package com.forerunner.carzsiswafinal.Driver;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.forerunner.carzsiswafinal.Adapter.BookingRequestsAdapter;
import com.forerunner.carzsiswafinal.Model.firebase.ManageRideQuery;
import com.forerunner.carzsiswafinal.Model.firebase.ManageUserQuery;
import com.forerunner.carzsiswafinal.R;
import com.google.firebase.auth.FirebaseAuth;

public class DriverRideRequestsFrag extends Fragment {

    private RecyclerView recyclerViewOngoingRides;
    FirebaseAuth mAuth =FirebaseAuth.getInstance();
    ManageRideQuery manageRideQuery = ManageRideQuery.getInstance();

    ManageUserQuery manageUserQuery = ManageUserQuery.getInstance();



    public DriverRideRequestsFrag() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_driver_ride_requests, container, false);

        String driverID = mAuth.getCurrentUser().getUid();
        // Inflate the layout for this fragment
        recyclerViewOngoingRides = view.findViewById(R.id.recycler_driver_passenger_requests);
        manageRideQuery.getPassengerRequests(driverID,"pending",
                passengerIDs -> {
                    manageUserQuery.getPassengersData(passengerIDs,
                            passengers -> {
                                BookingRequestsAdapter actAdapter = new BookingRequestsAdapter(passengers, requireActivity());
                                recyclerViewOngoingRides.setAdapter(actAdapter);
                            },
                            exception -> {
                                Log.e("DriverRideRequestsFrag", "Error getting passengers' data " + exception.getMessage());
                            }
                    );
                },
                exception -> {
                    // Handle failure to get driver data
                    Log.e("DriverRideRequestsFrag", "Error getting passenger IDs: " + exception.getMessage());
                }
        );
        return view;
    }

}