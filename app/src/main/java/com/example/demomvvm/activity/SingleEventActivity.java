package com.example.demomvvm.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.demomvvm.R;
import com.example.demomvvm.databinding.ActivitySingleEventBinding;
import com.example.demomvvm.viewmodel.ObserverViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class SingleEventActivity extends AppCompatActivity {

    ObserverViewModel viewModel;
    ActivitySingleEventBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_single_event);
        viewModel = ViewModelProviders.of(this).get(ObserverViewModel.class);

        binding.btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.setSingleLiveEventToast(true);
            }
        });

        viewModel.getSingleLiveEventToast().observeSingleEvent(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isCLicked) {
                if (isCLicked) {
                    Toast.makeText(SingleEventActivity.this, "single event toast", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.getSingleLiveEventToast().observeSingleEvent(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isCLicked) {
                if (isCLicked) {
                    Toast.makeText(SingleEventActivity.this, "Second Observer", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}