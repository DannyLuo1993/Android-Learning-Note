package com.danny.score_record;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScoreViewModel extends ViewModel {
    private MutableLiveData<Integer> teama_score;
    private MutableLiveData<Integer> teamb_score;

    public MutableLiveData<Integer> getScoreOfTeamA(){
        if(teama_score == null){
            teama_score = new MutableLiveData<Integer>();
            teama_score.setValue(0);
        }
        return teama_score;
    };

    public MutableLiveData<Integer> getScoreOfTeamB(){
        if(teamb_score == null){
            teamb_score = new MutableLiveData<Integer>();
            teamb_score.setValue(0);
        }
        return teamb_score;
    };

    public void addScoreToTeamA(int n){
        if(teama_score.getValue() != null){
        switch (n){
            case 1:
                teama_score.setValue(teama_score.getValue() + 1);
                break;
            case 2:
                teama_score.setValue(teama_score.getValue() + 2);
                break;
            case 3:
                teama_score.setValue(teama_score.getValue() + 3);
                break;
        }

    }}

    public void addScoreToTeamB(int n){
        if(teamb_score.getValue() != null){
            switch (n){
                case 1:
                    teama_score.setValue(teamb_score.getValue() + 1);
                    break;
                case 2:
                    teama_score.setValue(teamb_score.getValue() + 2);
                    break;
                case 3:
                    teama_score.setValue(teamb_score.getValue() + 3);
                    break;
            }

        }}

}
