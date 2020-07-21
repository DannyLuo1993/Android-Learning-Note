package com.danny.englishdictionary;

import android.provider.UserDictionary;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordListViewHolder> {

    private List<String> menglishword;
    private List<String> mchineseword;



    //創建ViewHolder來管理Adapter的Input layout中的全部View
    //在內部類前加Static防止內存洩露
    public static class WordListViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNumber, textViewEnglish, textViewChinese;
        public WordListViewHolder(@NonNull View itemView) {
            super(itemView);
            System.out.println("WordListViewHolder");
            textViewNumber = itemView.findViewById(R.id.textView);
            textViewEnglish = itemView.findViewById(R.id.textView2);
            textViewChinese = itemView.findViewById(R.id.textView3);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    // To allow activity to pass parameter
    // 传进来的参数即将要通过OnBindViewHolder设置给控件，所以传递过来的参数格式极其重要
    public WordListAdapter(List<String> myDataset, List<String> testDataset){
        menglishword = myDataset;
        mchineseword = testDataset;
    }


    //Input方法，將需要ViewHolder管理的內容注入ViewHolder
    @NonNull
    @Override
    public WordListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("onCreateViewHolder");
        //獲得layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        //解析input源的View
        View itemView = layoutInflater.inflate(R.layout.fragment_word_list, parent, false);
        //將解析後的View投入ViewHolder管理
        return new WordListViewHolder(itemView);
    }

    //output，將ViewHolder管理的內容以指定的方式輸出給recycler view (invoked by the layout manager)
    // 1. 將holder里的View分別與對應數據綁定
    // 2.
    @Override
    public void onBindViewHolder(@NonNull WordListViewHolder holder, int position) {
        //allwords是數據的ArrayList集合，這裡會獲取集合裡每個position的數據
        //將獲取到的數據綁定到視圖上
        System.out.println("onBindVIewHolder");
        holder.textViewNumber.setText(String.valueOf(position));

        holder.textViewEnglish.setText(menglishword.get(position));
        holder.textViewChinese.setText(mchineseword.get(position));
    }

    @Override
    public int getItemCount() {
        return menglishword.size();
    }




}
