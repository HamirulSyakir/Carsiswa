package com.forerunner.carzsiswafinal.Passenger;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.forerunner.carzsiswafinal.Model.firebase.Authentication;
import com.forerunner.carzsiswafinal.Model.firebase.ManageUserQuery;
import com.forerunner.carzsiswafinal.Model.room.UserDao;
import com.forerunner.carzsiswafinal.Model.room.UserDatabase;
import com.forerunner.carzsiswafinal.R;
import com.forerunner.carzsiswafinal.carpoolProject;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class PassengerLogin extends AppCompatActivity {


    ManageUserQuery manageUserQuery = ManageUserQuery.getInstance();
    UserDatabase userDatabase = carpoolProject.getInstance().getDb();
    final UserDao userDao = userDatabase.userDao();
    private static final Executor executor = Executors.newSingleThreadExecutor();

    Authentication authentication = Authentication.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_login);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });
    }
    public void insertPassenger(Passenger passenger, UserDao userDao) {
        executor.execute(() -> userDao.insertPassenger(passenger));
    }
    private void addToRoomDb(String passengerId) {
        manageUserQuery.getInstance().getPassengerData(passengerId,
                passenger -> {
                    insertPassenger(passenger, userDao);
                },
                exception -> {
                    // Handle failure to get passenger data
                    Log.e("PassengerProfileView", "Error getting passenger data: " + exception.getMessage());
                });
    }

    void performLogin(){
        String inputEmail = ((EditText) findViewById(R.id.emailText)).getText().toString();
        String password = ((EditText) findViewById(R.id.passText)).getText().toString();
        if(inputEmail.isEmpty()||password.isEmpty())
        {
            Toast.makeText(this,"Please fill are fields",Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isValidEmail(inputEmail)) {
            Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show();
            return;
        }
        authentication.logIn(inputEmail,password,
                result -> {
                if("successful".equals(result)) {
                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    addToRoomDb(currentUser.getUid());
                    Intent goToMain = new Intent(PassengerLogin.this, PassengerMainPage.class);
                    Toast.makeText(PassengerLogin.this, "Authentication successful",
                            Toast.LENGTH_SHORT).show();
                    startActivity(goToMain);
                    Log.d("PassengerLogin", "Passenger authenticated");
                }
                else if ("wrong".equals(result)){
                    Toast.makeText(PassengerLogin.this, "Wrong email or password.",
                            Toast.LENGTH_SHORT).show();
                }
                },
                exception -> {
                    Log.e("PassengerLogin", "Error logging in " + exception.getMessage());
                });

    }
    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@siswa.ukm\\.edu\\.my";
        return email.matches(emailPattern);
    }
    public void signUpClick(View v){
        Intent intent = new Intent(PassengerLogin.this, PassengerSignUp.class);
        startActivity(intent);
    }
}