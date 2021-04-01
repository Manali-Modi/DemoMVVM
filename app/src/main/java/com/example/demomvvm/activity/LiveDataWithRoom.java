package com.example.demomvvm.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.demomvvm.R;
import com.example.demomvvm.databinding.ActivityLiveDataWithRoomBinding;
import com.example.demomvvm.model.Fruit;
import com.example.demomvvm.viewmodel.FruitViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class LiveDataWithRoom extends AppCompatActivity {

    ActivityLiveDataWithRoomBinding binding;
    FruitViewModel viewModel;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_live_data_with_room);
        viewModel = ViewModelProviders.of(this).get(FruitViewModel.class);

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.etFruit.getText().length() == 0) {
                    Toast.makeText(LiveDataWithRoom.this, "Enter item first", Toast.LENGTH_SHORT).show();
                } else {
                    viewModel.insertFruitItem(new Fruit(binding.etFruit.getText().toString()));
                    Toast.makeText(LiveDataWithRoom.this, "Item added", Toast.LENGTH_SHORT).show();
                    binding.etFruit.setText("");
                }
            }
        });

        viewModel.getFruitList();
        viewModel.fruitLiveData.observe(this, new Observer<List<Fruit>>() {
            @Override
            public void onChanged(List<Fruit> fruits) {
                List<String> allFruits = new ArrayList<>();
                for (int i = fruits.size() - 1; i >= 0; i--) {
                    Fruit fruit = fruits.get(i);
                    allFruits.add(fruit.getFruits());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, allFruits);
                binding.lstFruit.setAdapter(adapter);
            }
        });

        viewModel.fruitLiveData.observe(this, new Observer<List<Fruit>>() {
            @Override
            public void onChanged(List<Fruit> fruits) {
                Toast.makeText(LiveDataWithRoom.this, "Second Observer", Toast.LENGTH_SHORT).show();
            }
        });
    }
}