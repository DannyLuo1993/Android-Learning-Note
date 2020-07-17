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


    List<WordList> allwords = new ArrayList<>();
    public void setAllwords(List<WordList> allwords) {
        this.allwords = allwords;
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
        WordList wordList = allwords.get(position);
        //將獲取到的數據綁定到視圖上
        holder.textViewNumber.setText(position + 1);
        holder.textViewEnglish.setText(wordList.getEnglishword().getValue());
        holder.textViewChinese.setText(wordList.getChineseword().getValue());
    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
