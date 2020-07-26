package com.danny.englishdictionary;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Word.class}, version = 2, exportSchema = false)
public abstract class WordListDataBase extends RoomDatabase {

    //创建Dao的抽象类来操作Word.class这个Entity
    public abstract WordListDao wordListDao();

    //创建数据库单例
    private static WordListDataBase INSTANCE;

    public static WordListDataBase getInstance(Context context){
        INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordListDataBase.class, "wordListDataBase")
                .fallbackToDestructiveMigration()
                .build();
        return INSTANCE;
    }
}
