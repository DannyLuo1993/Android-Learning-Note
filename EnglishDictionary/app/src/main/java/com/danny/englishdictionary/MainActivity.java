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

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Recycler View部分定义
    private WordListAdapter wordListAdapter;
    private RecyclerView recyclerView;
    private WordListViewModel wordListViewModel;

    //DataBase 部分定义
    private WordListDao wordListDao;
    private WordListDataBase INSTANCE;
    private LiveData<List<WordListViewModel>> allwordsLive;

    //主页面控件部分定义
    Button buttonInsert, buttonUpdate, buttonDelete, buttonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");
        setContentView(R.layout.activity_main);

        //初始化ViewModel
        wordListViewModel = ViewModelProviders.of(this).get(WordListViewModel.class);
        //初始化recycler view
        recyclerView = findViewById(R.id.recyclerview);

        //設定recycler view的layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //將wordlist adapter中的輸入綁定到recycler view 中輸出。
        //稍候調用wordlist adapter中的构造方法設置數據。
        wordListAdapter = new WordListAdapter(wordListViewModel.getChineseword().getValue(), wordListViewModel.getEnglishword().getValue());
        recyclerView.setAdapter(wordListAdapter);

        //初始化Dao
        INSTANCE = WordListDataBase.getInstance(this);
        wordListDao = INSTANCE.wordListDao();
        allwordsLive = wordListDao.getallwordslive();

        //初始化页面控件
        buttonInsert = findViewById(R.id.buttonInsert);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonSearch = findViewById(R.id.buttonQuery);
        buttonUpdate = findViewById(R.id.buttonUpdate);



    }
}