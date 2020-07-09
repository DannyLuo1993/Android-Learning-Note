package com.danny.mental_arithmetic_challenge;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.danny.mental_arithmetic_challenge.databinding.FragmentGameBinding;


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
        dataViewModel = ViewModelProviders.of(requireActivity(),getDefaultViewModelProviderFactory()).get(DataViewModel.class);
        binding.setScore(dataViewModel);
        binding.setLifecycleOwner(this);
        dataViewModel.generateQA();
        //设置数字按键和清除按键的监听事件
    View.OnClickListener onClickListener = new View.OnClickListener() {
        StringBuilder stringBuilder = new StringBuilder();
        @Override
        public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:
            stringBuilder.append(1);
            binding.textViewCalResult.setText(stringBuilder.toString());
            break;

            case R.id.button2:
                stringBuilder.append(2);
                binding.textViewCalResult.setText(stringBuilder.toString());
            break;

            case R.id.button3:
                stringBuilder.append(3);
                binding.textViewCalResult.setText(stringBuilder.toString());
                break;

            case R.id.button4:
                stringBuilder.append(4);
                binding.textViewCalResult.setText(stringBuilder.toString());
                break;

            case R.id.button5:
                stringBuilder.append(5);
                binding.textViewCalResult.setText(stringBuilder.toString());
                break;

            case R.id.button6:
                stringBuilder.append(6);
                binding.textViewCalResult.setText(stringBuilder.toString());
                break;

            case R.id.button7:
                stringBuilder.append(7);
                binding.textViewCalResult.setText(stringBuilder.toString());
                break;

            case R.id.button8:
                stringBuilder.append(8);
                binding.textViewCalResult.setText(stringBuilder.toString());
                break;

            case R.id.button9:
                stringBuilder.append(9);
                binding.textViewCalResult.setText(stringBuilder.toString());
                break;

            case R.id.button11:
                stringBuilder.append(0);
                binding.textViewCalResult.setText(stringBuilder.toString());
                break;

            case R.id.button10:
                stringBuilder.setLength(0);
                binding.textViewCalResult.setText(stringBuilder.toString());
                break;
        }
        }
    };

        //设置提交按键的监听事件；
    View.OnClickListener submitListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            System.out.println(Integer.parseInt(binding.textViewCalResult.getText().toString()));
            System.out.println(dataViewModel.reference_answer);
            if(Integer.parseInt(binding.textViewCalResult.getText().toString()) == dataViewModel.reference_answer){
                binding.textViewCalResult.setText(getString(R.string.correct_hint));
                //加当前分数；
                dataViewModel.addScore();
                //生成下一道问题
                dataViewModel.generateQA();
            }else{
                //如果挑战胜利，就跳转到跳转胜利的页面
                if(dataViewModel.win_flag == true){
                    //将当前分数保存为最高分；
                    dataViewModel.saveNewHighRecord();
                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(R.id.action_gameFragment_to_gameWinFragment);
                }
                //如果挑战失败，就跳转到失败页面
                else{
                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(R.id.action_gameFragment_to_gameOverFragment2);
                }
            }

        }
    };

        binding.button1.setOnClickListener(onClickListener);
        binding.button2.setOnClickListener(onClickListener);
        binding.button3.setOnClickListener(onClickListener);
        binding.button4.setOnClickListener(onClickListener);
        binding.button5.setOnClickListener(onClickListener);
        binding.button6.setOnClickListener(onClickListener);
        binding.button7.setOnClickListener(onClickListener);
        binding.button8.setOnClickListener(onClickListener);
        binding.button9.setOnClickListener(onClickListener);
        binding.button10.setOnClickListener(onClickListener);
        binding.button11.setOnClickListener(onClickListener);
        binding.buttonSubmit.setOnClickListener(submitListener);


    }





    public GameFragment() {
        // Required empty public constructor
    }











}