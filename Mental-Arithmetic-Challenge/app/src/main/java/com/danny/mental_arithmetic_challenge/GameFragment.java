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




    }



    public GameFragment() {
        // Required empty public constructor
    }











}