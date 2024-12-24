package com.forerunner.carzsiswafinal.Passenger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.forerunner.carzsiswafinal.R;


public class Passenger_fragment_ongoing_history extends Fragment {


    public Passenger_fragment_ongoing_history() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_passenger_ongoing_history,
                container,
                false);
        return view;
    }
}