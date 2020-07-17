package com.danny.englishdictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    WordList wordList;
    WordListAdapter wordListAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化recycler view
        recyclerView = findViewById(R.id.recyclerview);
        //設定recycler view的layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //將wordlist adapter中的輸入綁定到recycler view 中輸出。
        //稍候調用wordlist adapter中的setter方法設置數據。
        wordListAdapter = new WordListAdapter();
        recyclerView.setAdapter(wordListAdapter);


    }
}