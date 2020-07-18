package com.danny.englishdictionary;

import android.provider.UserDictionary;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordListViewHolder> {

    private String[] number = {"1"};
    private String[] EN = {"Hello"};
    private String[] CN = {"你好"};

    public void setListContent(String[] number, String[] EN, String[] CN){
        this.number = number;
        this.EN = EN;
        this.CN = CN;
    }
    //創建ViewHolder來管理Adapter的Input layout中的全部View
    //在內部類前加Static防止內存洩露
    static class WordListViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNumber, textViewEnglish, textViewChinese;
        public WordListViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNumber = itemView.findViewById(R.id.textView);
            textViewEnglish = itemView.findViewById(R.id.textView2);
            textViewChinese = itemView.findViewById(R.id.textView3);
        }
    }


    //Input方法，將需要ViewHolder管理的內容注入ViewHolder
    @NonNull
    @Override
    public WordListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //獲得layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        //解析input源的View
        View itemView = layoutInflater.inflate(R.layout.fragment_word_list, parent, false);

        //將解析後的View投入ViewHolder管理
        return new WordListViewHolder(itemView);
    }

    //output，將ViewHolder管理的內容以指定的方式輸出給recycler view
    // 1. 將holder里的View分別與對應數據綁定
    // 2.
    @Override
    public void onBindViewHolder(@NonNull WordListViewHolder holder, int position) {
        //allwords是數據的ArrayList集合，這裡會獲取集合裡每個position的數據
        //將獲取到的數據綁定到視圖上
        System.out.println("onBindVIewHolder");
        holder.textViewNumber.setText(number.toString());
        holder.textViewEnglish.setText(EN.toString());
        holder.textViewChinese.setText(CN.toString());
    }

    @Override
    public int getItemCount() {
        return 0;
    }




}
