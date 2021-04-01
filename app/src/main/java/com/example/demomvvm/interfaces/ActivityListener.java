package com.example.demomvvm.interfaces;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

public interface ActivityListener {

    void register(final MutableLiveData<Integer> mutableLiveData);

    MediatorLiveData<Integer> getMediatorLiveData();

    int getCurrentProgress();
}
