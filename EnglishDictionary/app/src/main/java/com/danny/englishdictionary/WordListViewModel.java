package com.danny.englishdictionary;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WordListViewModel extends AndroidViewModel {

    private MutableLiveData<String[]> englishword = new MutableLiveData<>();
    private MutableLiveData<String[]> chineseword = new MutableLiveData<>();
    private String[] myDataset = {"1", "2", "3","4", "5", "6"};
    private String[] testDataset = {"Hello", "World", "java", "Random", "Dota2", "CSGO"};


    public WordListViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String[]> getChineseword() {
        chineseword.setValue(myDataset);
        return chineseword;
    }

    public MutableLiveData<String[]> getEnglishword() {
        englishword.setValue(testDataset);
        return englishword;
    }


}
