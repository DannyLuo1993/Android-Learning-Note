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



    int score_n = 0;
    int game_over = 0;
    SavedStateHandle handle;
    public MutableLiveData<String> calResultText = new MutableLiveData<>();
    public MutableLiveData<String> referenceAnswer = new MutableLiveData<>();
    public MutableLiveData<Integer> score = new MutableLiveData<>();
    public MutableLiveData<Boolean> gameover = new MutableLiveData<>();

    public DataViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);
        this.handle = handle;
        if(!handle.contains("Score")){
           load();
        }
    }

    public LiveData<Integer> getScoreRecord(){

        return handle.getLiveData("Score");
    }

    //Load the score record from preference;
    public void load(){
        SharedPreferences shp = getApplication().getSharedPreferences("Score", Context.MODE_PRIVATE);
        int welcome_page_score = shp.getInt("Score", 0);
        handle.set("Score", welcome_page_score);
    }

    //Save the score to SharedPreference
    public void save(){
        SharedPreferences shp = getApplication().getSharedPreferences("Score", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        editor.putInt("Score", getScoreRecord().getValue());
        //为何用apply（）会报空指令？ 非要异步处理？
        editor.commit();
    }

    public void setCalResultText() {
        calResultText.setValue("开始答题");
    }

    //Button 0 - 9 CLick Event
    public void inputNumber(String n){
        if (calResultText.getValue() == "开始答题" || calResultText.getValue() == "回答正确！请继续答题"){
            clearNumber();
        }
        calResultText.setValue( calResultText.getValue() + n);
    }

    //Button C CLick Event
    public void clearNumber(){
        calResultText.setValue("");
    }
    //Button submit CLick Event
    public void submitNumber(){
        int userinput = Integer.parseInt(calResultText.getValue());
        int reference_answer = Integer.parseInt(referenceAnswer.getValue());
        //if( calResultText.getValue() == referenceAnswer.getValue()){
        if(userinput == reference_answer){
            calResultText.setValue("回答正确！请继续答题");
            getScore();
        }else{
            addRecordScore(score_n);
            game_over = 1;
            gameover.setValue(true);

        }
    }

    public void getScore(){

        if(score == null){
            score = new MutableLiveData<>();
            score.setValue(0);
        }
        ++score_n;
        score.setValue(score_n);
    }

    public void addRecordScore(int score_n){
        int x = score_n - getScoreRecord().getValue();
        if(x > 0){
        handle.set("Score", score_n);}
    }

    //Generate the challenge question randomly;






}
