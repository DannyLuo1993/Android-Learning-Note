package com.danny.mental_arithmetic_challenge;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DataViedModel extends ViewModel {

    private MutableLiveData<Integer> score;
    private MutableLiveData<Integer> cal_result;
    private MutableLiveData<Integer> user_result;

    public MutableLiveData<Integer> getScore(){
        if(score == null){
            score = new MutableLiveData();
            score.setValue(0);
        }
        return score;
    }

    public MutableLiveData<Integer> getCal_result(){
        if(cal_result == null){
            cal_result = new MutableLiveData<>();
            cal_result.setValue(2);
        }

        return cal_result;
    }

    public MutableLiveData<Integer> getUser_result(){
        if(user_result == null ){
            user_result = new MutableLiveData<>();
            user_result.setValue(2);
        }
        return user_result;
    }



}
