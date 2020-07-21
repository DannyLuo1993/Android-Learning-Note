package com.danny.englishdictionary;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class WordListViewModel extends AndroidViewModel {

    private WordRepository wordRepository;




    public WordListViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);

    }

    public LiveData<List<Word>> getAllwordslive() {
        return wordRepository.getAllwordslive();
    }

    public void insertWords(Word... words){
        wordRepository.insertWords(words);
    }

    public void deleteallWords(){
        wordRepository.deleteallWords();
    }

}


