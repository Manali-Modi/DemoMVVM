package com.example.demomvvm.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fruit")
public class Fruit {

    @PrimaryKey(autoGenerate = true)
    private int _id;

    @ColumnInfo(name = "fruitList")
    private String fruits;

    public Fruit(String fruits) {
        this.fruits = fruits;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getFruits() {
        return fruits;
    }

    public void setFruits(String fruits) {
        this.fruits = fruits;
    }
}
