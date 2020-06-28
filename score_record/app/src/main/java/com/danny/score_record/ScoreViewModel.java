package com.danny.score_record;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScoreViewModel extends ViewModel {
    private MutableLiveData<Integer> score;

    public MutableLiveData<Integer> getScore(){
      if(score == null){
          score = new MutableLiveData<Integer>();
          score.setValue(0);
      }
        return score;
    };

    public void addScore(int n){
        if(score.getValue() != null){
        switch (n){
            case 1:
                score.setValue(score.getValue() + 1);
                break;
            case 2:
                score.setValue(score.getValue() + 2);
                break;
            case 3:
                score.setValue(score.getValue() + 3);
                break;
        }

    }}

}
