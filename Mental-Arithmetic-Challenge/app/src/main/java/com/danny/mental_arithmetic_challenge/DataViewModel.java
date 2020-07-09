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

import java.util.Random;


public class DataViewModel extends AndroidViewModel {

    SavedStateHandle handle;

    public static String NEW_HIGH_RECORD = "NewHighRecord";
    public static String CURRENT_SCORE = "CurrentScore";
    public static String LEFT_NUMBER = "LeftNumber";
    public static String RIGHT_NUMBER = "RightNumber";
    public static String OPERATOR = "Operator";
    public int reference_answer = 0;
    public boolean win_flag = false;
    public MutableLiveData<Integer> NewHighRecord = new MutableLiveData<>();
    public MutableLiveData<Integer> CurrentScore = new MutableLiveData<>();
    public MutableLiveData<Integer> LeftNumber = new MutableLiveData<>();
    public MutableLiveData<String> Operator = new MutableLiveData<>();
    public MutableLiveData<Integer> RightNumber = new MutableLiveData<>();
    private MutableLiveData<Integer> calResult = new MutableLiveData<>();


    public DataViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);
        this.handle = handle;
        //初始化
        if (!handle.contains(NEW_HIGH_RECORD)){
            handle.set(NEW_HIGH_RECORD, 0);
            handle.set(CURRENT_SCORE, 0);
            handle.set(LEFT_NUMBER, 0);
            handle.set(RIGHT_NUMBER,0);
            handle.set(OPERATOR,"+");
        }
    }

    public MutableLiveData<Integer> getNewHighRecord(){

        //因为返回值是MutableLiveData，所以它在生命周期内会持续被Fragment 或 Activity观察
        //但因ViewModel的生命周期特性，Fragment或Activity被后台杀死后，数据不会被保存 【看回放】
        //这时需要将需要被保存的数据，传给SharedPreference保存，由SavedStateHandle完成存放&转交。
        SharedPreferences shp = getApplication().getSharedPreferences(NEW_HIGH_RECORD, Context.MODE_PRIVATE);
        handle.set(NEW_HIGH_RECORD, shp.getInt(NEW_HIGH_RECORD, 0));
        return NewHighRecord;
    }

    public MutableLiveData<Integer> getLeftNumber(){

        return handle.getLiveData(LEFT_NUMBER);
    }
    public MutableLiveData<Integer> getRightNumber(){
        return handle.getLiveData(RIGHT_NUMBER);
    }
    public MutableLiveData<String> getOperator(){
        return handle.getLiveData(OPERATOR);
    }

    public MutableLiveData<Integer> getCurrentScore(){

        //通过这个操作，拿到得是存放在handle里的CurrentScore值
        //如果是这里填 return CurrentScore, 那么在Activity被杀死时，关联的Current Score就会丢失。
        return handle.getLiveData(CURRENT_SCORE);
    }

    //Question and Answer generator
    public void generateQA (){
        int Level = 20;
        Random random = new Random();
        //是否应该在这里赋值？
        int x = random.nextInt(Level) + 1;
        int y = random.nextInt(Level) + 1;
        //这里填getLeftNumber和直接填LeftNumber有什么区别？
        getLeftNumber().setValue(x);
        getRightNumber().setValue(y);
        if(x%2==0){
            getOperator().setValue("+");
            reference_answer = x + y;
        }else{
            getOperator().setValue("-");
            if(x - y < 0){
                getLeftNumber().setValue(y);
                getRightNumber().setValue(x);
                reference_answer = y - x;
            }else{
                reference_answer = x - y;
            }

        }

    }

    //保存最高分
    //Save new high record;
    public void saveNewHighRecord(){
        if(getNewHighRecord().getValue() < getCurrentScore().getValue()){
            win_flag = true;
            SharedPreferences shp = getApplication().getSharedPreferences(NEW_HIGH_RECORD, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = shp.edit();
            editor.putInt(NEW_HIGH_RECORD, getCurrentScore().getValue());
            editor.commit();
        }
    }

    // 答对问题时加分
    // add 1 to current score when question is answered correctly.
    public void addScore(){
       getCurrentScore().setValue(CurrentScore.getValue() + 1);
    }


}
