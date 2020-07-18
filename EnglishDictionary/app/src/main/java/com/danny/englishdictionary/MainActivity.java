package com.danny.englishdictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    WordListAdapter wordListAdapter;
    RecyclerView recyclerView;
    private String[] number = {"1"};
    private String[] EN = {"Hello"};
    private String[] CN = {"你好"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");
        setContentView(R.layout.activity_main);
        //初始化recycler view
        recyclerView = findViewById(R.id.recyclerview);
        //設定recycler view的layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //將wordlist adapter中的輸入綁定到recycler view 中輸出。
        //稍候調用wordlist adapter中的setter方法設置數據。
        wordListAdapter = new WordListAdapter();
        recyclerView.setAdapter(wordListAdapter);
        wordListAdapter.setListContent(number,EN,CN);


    }
}