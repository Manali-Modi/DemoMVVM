package com.example.demomvvm.db;

import com.example.demomvvm.model.Fruit;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface FruitDao {

    @Query("select * from fruit")
    List<Fruit> getAllFruit();

    @Insert
    void insertFruit(Fruit fruit);
}
