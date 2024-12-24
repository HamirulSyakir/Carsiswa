package com.forerunner.carzsiswafinal.Model.room;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.forerunner.carzsiswafinal.Driver.Driver;
import com.forerunner.carzsiswafinal.Passenger.Passenger;

@Database(entities = {Passenger.class, Driver.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}

