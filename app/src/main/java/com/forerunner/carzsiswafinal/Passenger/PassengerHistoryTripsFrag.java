package com.forerunner.carzsiswafinal.Passenger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.forerunner.carzsiswafinal.Adapter.PassengerActivitiesAdapter;
import com.forerunner.carzsiswafinal.Model.firebase.ManageRideQuery;
import com.forerunner.carzsiswafinal.R;
import com.google.firebase.auth.FirebaseAuth;


public class PassengerHistoryTripsFrag extends Fragment {
    private RecyclerView recyclerViewOngoingRides;
    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ManageRideQuery manageRideQuery= ManageRideQuery.getInstance();


    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_passenger_ongoing_history, container, false);

        recyclerViewOngoingRides = view.findViewById(R.id.recycler_passenger_ongoing_history_rides_fragment);
        if (!isNetworkConnected()) {

            return view;
        }
        manageRideQuery.getPassengerRidesIDs(mAuth.getUid(),
                ridesIds ->{
                    if (ridesIds == null || ridesIds.isEmpty()) {
                        return;}
            manageRideQuery.getPassengerCompletedRides(ridesIds,
                   completedRides->{
                       if (completedRides == null || completedRides.isEmpty()) {
                           return;}
                       else{
                       PassengerActivitiesAdapter actAdapter = new PassengerActivitiesAdapter(completedRides);
                       recyclerViewOngoingRides.setAdapter(actAdapter);}
                   },exception -> {} );
                }
                ,
                exception -> {
                    // Handle failure to get driver data
                    Log.e("PassengerHistoryTripsFrag", "Error getting passenger Rides: " + exception.getMessage());});

        return view;
    }
    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) requireContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
        }
        return false;
    }
}