package com.example.demomvvm.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.example.demomvvm.R;
import com.example.demomvvm.databinding.FragmentBBinding;
import com.example.demomvvm.interfaces.ActivityListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class BFragment extends Fragment {

    FragmentBBinding binding;
    MutableLiveData<Integer> progressBMutableLiveData = new MutableLiveData<>();
    ActivityListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (ActivityListener) context;
        listener.register(progressBMutableLiveData);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_b, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        manageSeekBarProgress();
        setObservers();
    }

    private void setObservers() {
        progressBMutableLiveData.observe(this, new Observer<Integer>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(Integer integer) {
                binding.txtTitleB.setText("Fragment B " + integer);
            }
        });

        listener.getMediatorLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.seekB.setProgress(integer);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void manageSeekBarProgress() {
        binding.txtTitleB.setText("Fragment B " + listener.getCurrentProgress());
        binding.seekB.setProgress(listener.getCurrentProgress());

        binding.seekB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressBMutableLiveData.postValue(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        progressBMutableLiveData.postValue(binding.seekB.getProgress());
    }
}