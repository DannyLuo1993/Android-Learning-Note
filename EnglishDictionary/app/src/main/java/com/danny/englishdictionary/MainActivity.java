package com.danny.englishdictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Recycler View部分定义
    private WordListAdapter wordListAdapter;
    private RecyclerView recyclerView;
    private WordListViewModel wordListViewModel;



    //主页面控件部分定义
    Button buttonInsert, buttonDelete;


    //
    //List<String> englishword = new ArrayList<>();
    //List<String> chineseword = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");
        setContentView(R.layout.activity_main);

        //初始化ViewModel
        wordListViewModel = new ViewModelProvider(this).get(WordListViewModel.class);

        //初始化recycler view
        recyclerView = findViewById(R.id.recyclerview);
        //設定recycler view的layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //將wordlist adapter中的輸入綁定到recycler view 中輸出。
        //稍候調用wordlist adapter中的构造方法設置數據。
        wordListAdapter = new WordListAdapter(wordListViewModel);
        //View的数据通过setAdapter里的wordListAdapter传递
        recyclerView.setAdapter(wordListAdapter);

        wordListViewModel.getAllwordslive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                System.out.println("Observer_AllWordsLive");
                for(int i = 0; i<words.size()||i==words.size(); i++){
                    wordListAdapter.setWordList(words);
                    //设置完还要告诉recycler view数据发生改变了，需要刷新视图
                    wordListAdapter.notifyDataSetChanged();
                }
            }
        });


        //初始化页面控件
        buttonInsert = findViewById(R.id.buttonInsert);
        buttonDelete = findViewById(R.id.buttonDelete);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word1 = new Word("Hello", "你好", true);
                Word word2 = new Word("World","世界", true);
                Word word3 = new Word("Java", "安卓",true);
                Word word4 = new Word("Dota2", "刀塔2", true);
                Word word5 = new Word("Lays", "乐事", true);
                Word word6 = new Word("cake", "蛋糕", true);
                Word word7 = new Word("Tomato", "番茄",true);
                Word word9 = new Word("flavor","口味",true);
                Word word10 = new Word("chip", "薯片", true);
                Word word11 = new Word("tissue", "纸巾",true);
                Word word12 = new Word("Chair", "椅子", true);
                wordListViewModel.insertWords(word1,word2,word3,word4,word5,word6,word7,word9,word10,word11,word12);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordListViewModel.deleteallWords();
            }
        });




    }
}