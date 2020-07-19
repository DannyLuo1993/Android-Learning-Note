package com.danny.englishdictionary;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities= (Word.class), version = 1, exportSchema = false)
public abstract class WordListDataBase extends RoomDatabase {

    public abstract WordListDao getWordListDao();
}
