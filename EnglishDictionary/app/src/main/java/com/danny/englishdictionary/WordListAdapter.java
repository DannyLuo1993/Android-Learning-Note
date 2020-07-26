package com.danny.englishdictionary;

import android.content.Intent;
import android.net.Uri;
import android.provider.UserDictionary;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.view.View.GONE;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordListViewHolder> {


    private List<Word> wordList;
    private WordListViewModel wordListViewModel;

    public WordListAdapter(WordListViewModel wordListViewModel) {
        this.wordListViewModel = wordListViewModel;
    }


    public void setWordList(List<Word> wordList) {
        if(wordList == null){
            wordList = new ArrayList<>();
        }
        this.wordList = wordList;
    }

    //創建ViewHolder來管理Adapter的Input layout中的全部View
    public static class WordListViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNumber, textViewEnglish, textViewChinese;
        Switch aSwitch;
        public WordListViewHolder(@NonNull View itemView) {
            super(itemView);
            System.out.println("WordListViewHolder");
            textViewNumber = itemView.findViewById(R.id.textView);
            textViewEnglish = itemView.findViewById(R.id.textView2);
            textViewChinese = itemView.findViewById(R.id.textView3);
            aSwitch = itemView.findViewById(R.id.switch2);
        }
    }




    //Input方法，將需要ViewHolder管理的內容注入ViewHolder
    @NonNull
    @Override
    public WordListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("onCreateViewHolder");
        //獲得layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        //解析input源的View
        View itemView = layoutInflater.inflate(R.layout.cell_card, parent, false);
        //將解析後的View投入ViewHolder管理
        return new WordListViewHolder(itemView);
    }

    //output，將ViewHolder管理的內容以指定的方式輸出給recycler view (invoked by the layout manager)
    // 1. 將holder里的View分別與對應數據綁定
    // 2.
    @Override
    public void onBindViewHolder(@NonNull final WordListViewHolder holder, int position) {
        final Word word = wordList.get(position);
        System.out.println(word);
        System.out.println(word.isVisible());
        //allwords是數據的ArrayList集合，這裡會獲取集合裡每個position的數據
        //將獲取到的數據綁定到視圖上
        System.out.println("onBindVIewHolder");
        holder.textViewNumber.setText(String.valueOf(position));
        holder.textViewEnglish.setText(word.getEnglishword());
        holder.textViewChinese.setText(word.getChineseword());
        holder.aSwitch.setOnCheckedChangeListener(null);
        if(word.isVisible()){
            holder.aSwitch.setChecked(false);
        }else {
            holder.aSwitch.setChecked(true);
        }
        holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //对数据库操作部分
                    //wordListViewModel没有初始化(实例化)，所以操作时报空指针错误
                    word.setVisible(false);
                    wordListViewModel.updateWords(word);
                    //对界面操作部分
                    holder.textViewChinese.setVisibility(GONE);
                }else{
                    word.setVisible(true);
                    wordListViewModel.updateWords(word);
                    holder.textViewChinese.setVisibility(View.VISIBLE);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creates a Uri which parses the given encoded URI string.
                //return Uri for this given uri string
                Uri uri = Uri.parse("https://www.ldoceonline.com/dictionary/" + word.getEnglishword());
                //used on a contacts or mailto
                //when used on a contacts entry it will view the entry;
                //when used on a mailto: URI it will bring up a compose window filled with the information
                // * supplied by the URI;
                //when used with a tel: URI it will invoke thedialer.
                Intent intent = new Intent(Intent.ACTION_VIEW);
                //Set the data this intent is operating on.
                intent.setData(uri);
                holder.itemView.getContext().startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        if(wordList == null){
            return 0;
        }else{
        return wordList.size();}
    }





}
