package com.example.demomvvm.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import com.example.demomvvm.db.FruitRoomDb;
import com.example.demomvvm.model.Fruit;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class FruitViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Fruit>> fruitMutableLiveData = new MutableLiveData<>();
    private final FruitRoomDb fruitRoomDb;
    public LiveData<List<Fruit>> fruitLiveData = fruitMutableLiveData;
    List<Fruit> fruitList = new ArrayList<>();

    public FruitViewModel(Application ctx) {
        super(ctx);
        fruitRoomDb = FruitRoomDb.getInstance(ctx);
    }

    public void getFruitList() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                fruitList = fruitRoomDb.fruitDao().getAllFruit();
                fruitMutableLiveData.postValue(fruitList);
            }
        });
    }

    public void insertFruitItem(Fruit fruit) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                fruitRoomDb.fruitDao().insertFruit(fruit);
                List<Fruit> fruits = fruitMutableLiveData.getValue();
                if (fruits == null)
                    fruits = new ArrayList<>();
                fruits.add(fruit);
                fruitMutableLiveData.postValue(fruits);
            }
        });
    }
}
