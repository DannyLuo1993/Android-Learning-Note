package com.danny.mental_arithmetic_challenge;

import android.os.Bundle;

import androidx.annotation.NavigationRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danny.mental_arithmetic_challenge.databinding.FragmentGameOverBinding;

public class GameOverFragment extends Fragment {

    FragmentGameOverBinding binding;
    DataViewModel dataViewModel;
    public GameOverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGameOverBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dataViewModel = new ViewModelProvider(requireActivity()).get(DataViewModel.class);
        binding.setReport(dataViewModel);
        binding.setLifecycleOwner(this);
        dataViewModel.gameover.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.textViewFailReport.setText("你本次的战果是： " + dataViewModel.score.getValue());
            }
        });
        binding.button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataViewModel.gameover.setValue(false);
                System.out.println(dataViewModel.gameover.getValue());
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_gameOverFragment_to_fragmentWelcome3);
            }
        });
    }




}