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
    public static String SAVESHP = "SaveShp";
    public int reference_answer = 0;
    public boolean win_flag = false;



    public DataViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);
        //初始化，创建Savestatehandle的key-value map
        //This is a key-value map that will let you write and retrieve objects to and from the saved state
        //问题，那这些key-value map的值有变化吗？ 是怎么变化的呢？
        //答案，有变化，是通过读取方法的setValue()来变更的。
        if (!handle.contains(NEW_HIGH_RECORD)){
            SharedPreferences shp = getApplication().getSharedPreferences(SAVESHP,Context.MODE_PRIVATE);
            handle.set(NEW_HIGH_RECORD, shp.getInt(SAVESHP, 0));

            handle.set(CURRENT_SCORE, 0);
            handle.set(LEFT_NUMBER, 0);
            handle.set(RIGHT_NUMBER,0);
            handle.set(OPERATOR,"+");
        }
        this.handle = handle;
    }

    //get方法的作用是获取（读取）到相应的LiveData
    //得到的LiveData均会被Activity或Fragment中的ViewModelProvide接收；
    //返回值是从Savestatehandle中读取的；
    public MutableLiveData<Integer> getNewHighRecord(){

        return handle.getLiveData(NEW_HIGH_RECORD);
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
        //CurrentScore = handle.getLiveData(CURRENT_SCORE);
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

        //因为MutableLiveData在生命周期内会持续被Fragment 或 Activity观察
        //但因ViewModel的生命周期特性，Fragment或Activity被后台杀死后，数据不会被保存 【看回放】
        //这时需要将需要被保存的数据，传给SharedPreference保存，由SavedStateHandle完成存放&转交。
        if (getCurrentScore().getValue() > getNewHighRecord().getValue()) {
            win_flag = true;
            SharedPreferences shp = getApplication().getSharedPreferences(SAVESHP, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = shp.edit();
            editor.putInt(SAVESHP, getCurrentScore().getValue());
            editor.apply();
            getNewHighRecord().setValue(getCurrentScore().getValue());
            //handle.set(NEW_HIGH_RECORD, shp.getInt(SAVESHP,0));

        }
    }

    // 答对问题时加分
    // add 1 to current score when question is answered correctly.
    public void addScore() {
        getCurrentScore().setValue(getCurrentScore().getValue() + 1);
    }
}
