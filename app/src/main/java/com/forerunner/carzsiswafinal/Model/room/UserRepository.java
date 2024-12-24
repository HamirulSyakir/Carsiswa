package com.forerunner.carzsiswafinal.Model.room;

import android.app.Application;

import com.forerunner.carzsiswafinal.carpoolProject;

public class UserRepository {
    private UserDao userDao;
    public UserRepository(Application application) {
        UserDatabase database = carpoolProject.getInstance().getDb();
        userDao = database.userDao();
    }
}