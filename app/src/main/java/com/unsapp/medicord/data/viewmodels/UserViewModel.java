package com.unsapp.medicord.data.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.unsapp.medicord.data.models.User;

public class UserViewModel extends ViewModel {
    private MutableLiveData<User> userLiveData = new MutableLiveData<>();

    public void setUser(User user){
        userLiveData.setValue(user);
    }

    public LiveData<User> getUser(){
        return userLiveData;
    }
}
