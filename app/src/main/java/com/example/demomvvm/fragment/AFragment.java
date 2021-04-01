package com.example.demomvvm.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.example.demomvvm.R;
import com.example.demomvvm.databinding.FragmentABinding;
import com.example.demomvvm.interfaces.ActivityListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class AFragment extends Fragment {

    FragmentABinding binding;
    MutableLiveData<Integer> progressMutableLiveData = new MutableLiveData<>();
    ActivityListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (ActivityListener) context;
        listener.register(progressMutableLiveData);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_a, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        manageSeekBarProgress();
        setObservers();

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new BFragment(), "Fragment B")
                        .addToBackStack("Fragment B")
                        .commit();
            }
        });
    }

    private void setObservers() {
        progressMutableLiveData.observe(this, new Observer<Integer>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(Integer integer) {
                binding.txtTitleA.setText("Fragment A " + integer);
            }
        });

        listener.getMediatorLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.seekA.setProgress(integer);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void manageSeekBarProgress() {
        binding.txtTitleA.setText("Fragment A " + listener.getCurrentProgress());
        binding.seekA.setProgress(listener.getCurrentProgress());

        binding.seekA.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressMutableLiveData.postValue(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}