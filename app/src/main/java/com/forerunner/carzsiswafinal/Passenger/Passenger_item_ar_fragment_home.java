package com.forerunner.carzsiswafinal.Passenger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.forerunner.carzsiswafinal.R;

public class Passenger_item_ar_fragment_home extends Fragment {

    public Passenger_item_ar_fragment_home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_ar_home, container, false);
    }
}