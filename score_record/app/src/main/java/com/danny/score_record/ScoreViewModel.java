package com.danny.score_record;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScoreViewModel extends ViewModel {
    private MutableLiveData<Integer> teama_score;
    private MutableLiveData<Integer> teamb_score;
    int teamA_minus_score = 0;
    int teamB_minus_score = 0;

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
        teama_score.setValue(teama_score.getValue() + n);
        teamA_minus_score =n;
        teamB_minus_score =0;
    }}

    public void addScoreToTeamB(int n){
     if(teamb_score.getValue() != null){
            teamb_score.setValue(teamb_score.getValue() + n);
            teamB_minus_score =n;
            teamA_minus_score =0;
        }}

    public void minusScore(){
        while(teamA_minus_score == 0 && teamB_minus_score != 0){
            teamb_score.setValue(teamb_score.getValue() - teamB_minus_score);
            teamB_minus_score = 0;
            break;
        }
        while(teamB_minus_score == 0 & teamA_minus_score != 0){
            teama_score.setValue(teama_score.getValue() - teamA_minus_score);
            teamA_minus_score = 0;
            break;
        }}

    public void refreshScore(){
        teamA_minus_score = 0;
        teamB_minus_score = 0;
        teama_score.setValue(0);
        teamb_score.setValue(0);
    }
}
