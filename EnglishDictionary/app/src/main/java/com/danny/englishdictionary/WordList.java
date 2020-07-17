package com.danny.englishdictionary;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WordList extends AndroidViewModel {

    public MutableLiveData<Integer> serialnumber = new MutableLiveData<>();
    public MutableLiveData<String> englishword = new MutableLiveData<>();
    public MutableLiveData<String> chineseword = new MutableLiveData<>();

    public WordList(@NonNull Application application) {
        super(application);
    }


    public MutableLiveData<Integer> getSerialnumber() {
        if(serialnumber == null){
            serialnumber = new MutableLiveData<>();
            serialnumber.setValue(1);
        }
        return serialnumber;
    }

    public MutableLiveData<String> getEnglishword() {
        if(englishword == null){
            englishword = new MutableLiveData<>();
            englishword.setValue(getApplication().getString(R.string.init_word_en));
        }
        return englishword;
    }

    public MutableLiveData<String> getChineseword() {
        if(chineseword == null){
            chineseword = new MutableLiveData<>();
            englishword.setValue(getApplication().getString(R.string.init_word_cn));
        }
        return chineseword;
    }

}
