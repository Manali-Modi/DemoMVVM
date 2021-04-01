package com.example.demomvvm.viewmodel;

import com.example.demomvvm.event.SingleLiveEvent;

import androidx.lifecycle.ViewModel;

public class ObserverViewModel extends ViewModel {

    private final SingleLiveEvent<Boolean> singleLiveEventToast = new SingleLiveEvent<>();

    public SingleLiveEvent<Boolean> getSingleLiveEventToast() {
        return singleLiveEventToast;
    }

    public void setSingleLiveEventToast(Boolean singleLiveEventToast) {
        this.singleLiveEventToast.setValue(singleLiveEventToast);
    }
}
