package com.danny.mental_arithmetic_challenge;



import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;



public class DataViewModel extends AndroidViewModel {

    SavedStateHandle handle;

    private static String NEW_HIGH_RECORD = "NewHighRecord";
    private static String CURRENT_SCORE = "CurrentScore";
    public MutableLiveData<Integer> NewHighRecord = new MutableLiveData<>();
    public MutableLiveData<Integer> CurrentScore = new MutableLiveData<>();
    private MutableLiveData<Integer> LeftNumber = new MutableLiveData<>();
    private MutableLiveData<String> Operator = new MutableLiveData<>();
    private MutableLiveData<String> RightNumber = new MutableLiveData<>();
    private MutableLiveData<Integer> calResult = new MutableLiveData<>();


    public DataViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);
        this.handle = handle;
    }

    public MutableLiveData<Integer> getNewHighRecord(){

        //因为返回值是MutableLiveData，所以它在生命周期内会持续被Fragment 或 Activity观察
        //但因ViewModel的生命周期特性，Fragment或Activity被后台杀死后，数据不会被保存 【看回放】
        //这时需要将需要被保存的数据，传给SharedPreference保存，由SavedStateHandle完成存放&转交。
        SharedPreferences shp = getApplication().getSharedPreferences(NEW_HIGH_RECORD, Context.MODE_PRIVATE);
        handle.set(NEW_HIGH_RECORD, shp.getInt(NEW_HIGH_RECORD, 0));
        return NewHighRecord;
    }

    public MutableLiveData<Integer> getCurrentScore(){

        //通过这个操作，拿到得是存放在handle里的CurrentScore值
        return handle.getLiveData(CURRENT_SCORE);
    }
}
