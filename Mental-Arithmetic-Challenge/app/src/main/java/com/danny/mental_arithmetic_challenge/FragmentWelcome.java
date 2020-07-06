package com.danny.mental_arithmetic_challenge;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.danny.mental_arithmetic_challenge.databinding.FragmentGameBinding;
import com.danny.mental_arithmetic_challenge.databinding.FragmentWelcomeBinding;


public class FragmentWelcome extends Fragment {

    FragmentWelcomeBinding binding;
    DataViewModel dataViewModel;

    public FragmentWelcome() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWelcomeBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dataViewModel =  ViewModelProviders.of(this, getDefaultViewModelProviderFactory()).get(DataViewModel.class);
        System.out.println(dataViewModel.gameover.getValue());
        dataViewModel.game_over = 0;
        Button buttonStart;
        buttonStart = getView().findViewById(R.id.button_start);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.action_fragmentWelcome_to_gameFragment);
            }
        });

        binding.textViewRecord.setText("最高纪录： " + dataViewModel.getScoreRecord().getValue());
        dataViewModel.gameover.setValue(false);
        binding.setWelcomescore(dataViewModel);
        binding.setLifecycleOwner(this);

    }


}

