package com.forerunner.carzsiswafinal.Model.room;

import android.app.Application;

public class UserViewModel {

    private UserRepository repository;

    public UserViewModel(Application application) {
        repository = new UserRepository(application);
    }

}