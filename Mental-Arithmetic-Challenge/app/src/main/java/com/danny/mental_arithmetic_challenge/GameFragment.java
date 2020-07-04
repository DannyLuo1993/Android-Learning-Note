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

import java.util.Random;


public class GameFragment extends Fragment {

    DataViewModel dataViewModel;
    FragmentGameBinding binding;
    Random rand = new Random();
    int numberA ;
    int numberB ;
    String reference_answer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        binding = FragmentGameBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Get ViewModelProvider to observe data
        dataViewModel = new ViewModelProvider(requireActivity()).get(DataViewModel.class);
        dataViewModel.getScore().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                dataViewModel.addScore();
            }
        });
        //initialize the question
        generateQuestion();
    }

    //Generate the challenge question randomly;
    public String generateQuestion(){
        numberA = rand.nextInt(10);
        numberB = rand.nextInt(10);
        binding.textViewQuestion.setText(numberA + " + " +numberB + " = ?");
        reference_answer = String.valueOf(numberA+numberB);
        return reference_answer;
    }

    //Set Text when user answer the question correctly
    public void setTextWhenAnswerCorrectly(){
        binding.textViewCalResult.setText(R.string.correct_hint);
    }

    public void onClick(){
        NavController controller = Navigation.findNavController(binding.getRoot());
        controller.navigate(R.id.action_gameFragment_to_gameOverFragment2);
    }


    public GameFragment() {
        // Required empty public constructor
    }











}