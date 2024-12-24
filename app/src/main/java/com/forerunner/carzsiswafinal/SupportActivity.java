package com.forerunner.carzsiswafinal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SupportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
    }

    public void onEmailButtonClick(View view) {
        // Handle the email button click, e.g., open the email app
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"danishnabilbitstrips@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Support Request");
        startActivity(emailIntent);
    }

    public void onCallButtonClick(View view) {
        // Handle the "Call Support" button click, e.g., initiate a phone call
        String phoneNumber = "1234567890"; // Replace with the actual phone number
        Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        startActivity(callIntent);
    }
}