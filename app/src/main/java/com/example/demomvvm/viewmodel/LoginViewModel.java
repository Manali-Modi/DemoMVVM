package com.example.demomvvm.viewmodel;

import com.example.demomvvm.model.User;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    public MutableLiveData<User> userEmailMutableLiveData = new MutableLiveData<>();
    public LiveData<User> userLiveData = userEmailMutableLiveData;

    public void onClick(String email, String pwd) {
        User user = new User(email, pwd);
        userEmailMutableLiveData.postValue(user);
    }
}
