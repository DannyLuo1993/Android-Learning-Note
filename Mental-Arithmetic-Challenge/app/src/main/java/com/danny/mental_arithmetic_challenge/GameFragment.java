package com.danny.mental_arithmetic_challenge;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danny.mental_arithmetic_challenge.databinding.FragmentGameBinding;


public class GameFragment extends Fragment {

    DataViewModel dataViewModel;
    FragmentGameBinding binding;

    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dataViewModel = new ViewModelProvider(requireActivity()).get(DataViewModel.class);

        binding = FragmentGameBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
    }

}