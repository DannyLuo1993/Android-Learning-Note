package com.danny.mental_arithmetic_challenge;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.danny.mental_arithmetic_challenge.databinding.FragmentGameBinding;
import com.danny.mental_arithmetic_challenge.databinding.FragmentGameOverBinding;

import java.util.Random;


public class GameFragment extends Fragment {

    DataViewModel dataViewModel;
    FragmentGameBinding binding;
    Random rand = new Random();
    int numberA ;
    int numberB ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGameBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
        //return inflater.inflate(R.layout.fragment_game, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Get ViewModelProvider to observe the change of score

        dataViewModel = new ViewModelProvider(requireActivity()).get(DataViewModel.class);
        //initialize the question
        generateQuestion();
        //Get ViewModelProvider to observe the change of the number
        dataViewModel.setCalResultText();

        //设置数据与Layout之间的监听
        binding.setScore(dataViewModel);
        binding.setLifecycleOwner(this);

        //Add socre when question is answered correctly.
        dataViewModel.score.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.textView5.setText("得分： " + String.valueOf(dataViewModel.score.getValue()));
                if (dataViewModel.score.getValue() == 10){
                    NavController navController = Navigation.findNavController(requireActivity(), R.id.button_submit);
                    navController.navigate(R.id.action_gameFragment_to_gameWinFragment);
                }else{
                    generateQuestion();}
            }
        });
        System.out.println(dataViewModel.game_over);
        //Gameover when question is not correctly answered.
        dataViewModel.gameover.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                System.out.println("game over");
                NavController navController = Navigation.findNavController(requireActivity(), R.id.button_submit);
                navController.navigate(R.id.action_gameFragment_to_gameOverFragment2);
            }
        });
    }



    //Generate the challenge question randomly;
    public void generateQuestion(){
        numberA = rand.nextInt(10);
        numberB = rand.nextInt(10);
        dataViewModel.referenceAnswer.setValue(String.valueOf(numberA + numberB));
        binding.textViewQuestion.setText(numberA + " + " +numberB + " = ?");

    }

    @Override
    public void onPause() {
        super.onPause();
        dataViewModel.save();
    }

    public GameFragment() {
        // Required empty public constructor
    }











}