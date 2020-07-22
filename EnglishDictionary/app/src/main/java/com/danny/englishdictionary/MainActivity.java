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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Recycler View部分定义
    private WordListAdapter wordListAdapter;
    private RecyclerView recyclerView;
    private WordListViewModel wordListViewModel;


    //主页面控件部分定义
    Button buttonInsert, buttonUpdate, buttonDelete, buttonSearch;

    //
    List<String> englishword = new ArrayList<>();
    List<String> chineseword = new ArrayList<>();

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
        wordListAdapter = new WordListAdapter();
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
        buttonSearch = findViewById(R.id.buttonQuery);
        buttonUpdate = findViewById(R.id.buttonUpdate);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word1 = new Word("Hello", "你好");
                Word word2 = new Word( "World","世界");
                wordListViewModel.insertWords(word1, word2);
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