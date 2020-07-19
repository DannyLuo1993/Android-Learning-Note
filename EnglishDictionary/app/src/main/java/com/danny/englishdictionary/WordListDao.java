package com.danny.englishdictionary;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Dictionary;
import java.util.List;

@Dao
public interface WordListDao {

    @Insert
    void insertwords(Word... words);

    @Update
    void updatewords(Word... words);

    @Delete
    void deletewords(Word... words);


    @Query("DELETE FROM DICTIONARY")
    void deleteallwords();

    @Query("SELECT * FROM DICTIONARY ORDER BY ID DESC")
    LiveData<List<WordListViewModel>> getallwordslive();
}
