package com.forerunner.carzsiswafinal.Passenger;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.forerunner.carzsiswafinal.Adapter.RideAdapter;
import com.forerunner.carzsiswafinal.Model.firebase.ManageRideQuery;
import com.forerunner.carzsiswafinal.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class Passenger_fragment_available_rides extends Fragment {
    private FirebaseFirestore db;
    private RecyclerView recyclerViewAvailableRides;
    ManageRideQuery manageRideQuery = ManageRideQuery.getInstance();
    private TextView invisibleTextView;  // Add a reference to the TextView


    public Passenger_fragment_available_rides() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_available_rides,
                container,
                false);
        invisibleTextView = requireActivity().findViewById(R.id.invisibleTextView); // Get reference to the TextView

        recyclerViewAvailableRides = view.findViewById(R.id.recycler_available_rides_fragment_home);
        manageRideQuery.getAvailableRides(
                rides -> {
                    if (rides != null && !rides.isEmpty()) {
                        // If the list of rides is not empty, populate the RecyclerView
                        RideAdapter actAdapter = new RideAdapter(rides, requireActivity());
                        recyclerViewAvailableRides.setAdapter(actAdapter);
                        updateTextViewVisibility(View.INVISIBLE);

                    } else {
                        updateTextViewVisibility(View.VISIBLE);
                    }
                },
                exception ->
                        Log.e("Passenger_fragment_available_rides", "Error getting available rides " + exception.getMessage())

        );

        return view;
    }

    public void updateTextViewVisibility(int visibility) {
        if (invisibleTextView != null) {
            invisibleTextView.setVisibility(visibility);
        }
    }
}