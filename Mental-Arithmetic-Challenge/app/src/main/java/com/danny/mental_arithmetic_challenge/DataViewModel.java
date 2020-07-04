package com.danny.mental_arithmetic_challenge;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;

public class DataViewModel extends ViewModel {

    private MutableLiveData<Integer> score = new MutableLiveData<>();
    private MutableLiveData<String> calResultText = new MutableLiveData<>();
    GameFragment gameFragment = new GameFragment();


    public MutableLiveData<Integer> getScore(){
        if(score == null){
            score = new MutableLiveData();
            score.setValue(0);
        }
        return score;
    }


    public void addScore(){
        score.setValue(score.getValue()+1);
    }


    //Button 0 - 9 CLick Event
    public void inputNumber(int n){
        calResultText.setValue(calResultText.getValue()+n);
    }
    //Button C CLick Event
    public void clearNumber(){
        calResultText.setValue("");
    }
    //Button submit CLick Event
    public void submitNumber(){
        if (calResultText.getValue() == gameFragment.reference_answer){
            addScore();
            gameFragment.setTextWhenAnswerCorrectly();
            gameFragment.generateQuestion();

        }else{
            gameFragment.onClick();
        }
    }





}
