package com.example.demomvvm.activity;

import android.os.Bundle;

import com.example.demomvvm.R;
import com.example.demomvvm.databinding.ActivityMain2Binding;
import com.example.demomvvm.fragment.AFragment;
import com.example.demomvvm.interfaces.ActivityListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class MainActivity2 extends AppCompatActivity implements ActivityListener {

    ActivityMain2Binding binding;
    MediatorLiveData<Integer> liveDataMerger = new MediatorLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MainActivity2.this, R.layout.activity_main2);

        liveDataMerger.setValue(0);

        runFragment();
    }

    private void runFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AFragment(), "Fragment A")
                .addToBackStack("Fragment A")
                .commit();
    }

    @Override
    public void register(MutableLiveData<Integer> mutableLiveData) {
        liveDataMerger.addSource(mutableLiveData, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                liveDataMerger.setValue(integer);
            }
        });
    }

    @Override
    public MediatorLiveData<Integer> getMediatorLiveData() {
        return liveDataMerger;
    }

    @Override
    public int getCurrentProgress() {
        if (getMediatorLiveData().getValue() != null)
            return getMediatorLiveData().getValue();
        return 0;
    }
}