package com.example.demomvvm.db;

import android.content.Context;

import com.example.demomvvm.model.Fruit;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Fruit.class, version = 1)
public abstract class FruitRoomDb extends RoomDatabase {

    private static final String DB_NAME = "Items";
    private static FruitRoomDb fruitRoomDb;

    public static synchronized FruitRoomDb getInstance(Context ctx) {
        if (fruitRoomDb == null) {
            fruitRoomDb = Room.databaseBuilder(ctx, FruitRoomDb.class, DB_NAME)
                    .build();
        }
        return fruitRoomDb;
    }

    public abstract FruitDao fruitDao();
}
