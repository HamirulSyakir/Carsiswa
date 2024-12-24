package com.forerunner.carzsiswafinal.Start;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.forerunner.carzsiswafinal.Driver.DriverLogin;
import com.forerunner.carzsiswafinal.Driver.DriverMainPage;
import com.forerunner.carzsiswafinal.Model.room.UserDao;
import com.forerunner.carzsiswafinal.Model.room.UserDatabase;
import com.forerunner.carzsiswafinal.Passenger.PassengerLogin;
import com.forerunner.carzsiswafinal.Passenger.PassengerMainPage;
import com.forerunner.carzsiswafinal.R;
import com.forerunner.carzsiswafinal.carpoolProject;


public class MainActivity extends AppCompatActivity {

    UserDatabase userDatabase = carpoolProject.getInstance().getDb();
    final UserDao userDao = userDatabase.userDao();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button passengerLoginBtn = findViewById(R.id.loginBtn01);
        passengerLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkForExistingPassenger();

            }
        });

        Button driverLoginBtn = findViewById(R.id.loginBtn02);
        driverLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkForExistingDriver();

            }
        });
    }
    private void checkForExistingPassenger() {
        LiveData<Integer> passengerCountLiveData = userDao.getPassengerCount();
        passengerCountLiveData.observe(this, passengerCount -> {
            if (passengerCount != null && passengerCount > 0) {
                Intent goToMain = new Intent(MainActivity.this, PassengerMainPage.class);
                startActivity(goToMain);
                finish();// Finish the current activity to prevent going back to the login screen
            }
            else{
                Intent intent = new Intent(MainActivity.this, PassengerLogin.class);
                startActivity(intent);
            }
        });
    }
    private void checkForExistingDriver() {
        LiveData<Integer> driverCountLiveData = userDao.getDriverCount();
        driverCountLiveData.observe(this, driverCount -> {
            if (driverCount != null && driverCount > 0) {
                Intent goToMain = new Intent(MainActivity.this, DriverMainPage.class);
                startActivity(goToMain);
                finish(); // Finish the current activity to prevent going back to the login screen
            }
            else{
                Intent intent = new Intent(MainActivity.this, DriverLogin.class);
                startActivity(intent);
            }
        });
    }

}
