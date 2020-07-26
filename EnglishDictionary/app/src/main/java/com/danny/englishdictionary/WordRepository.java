package com.danny.englishdictionary;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {

    //DataBase 部分定义
    private WordListDao wordListDao;
    private WordListDataBase INSTANCE;
    private LiveData<List<Word>>allwordslive;

    public WordRepository(Context context) {
        //初始化Dao
        INSTANCE = WordListDataBase.getInstance(context.getApplicationContext());
        wordListDao = INSTANCE.wordListDao();
        allwordslive = wordListDao.getallwordslive();
    }

    public LiveData<List<Word>> getAllwordslive() {
        return allwordslive;
    }

    static class InsertAsynTask extends AsyncTask<Word, Void, Void> {
        private WordListDao wordListDao;

        public InsertAsynTask(WordListDao wordListDao){
            this.wordListDao = wordListDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordListDao.insertwords(words);
            return null;
        }
    }

    static class QueryAsynTask extends AsyncTask<Word, Void, Void>{
        private WordListDao wordListDao;

        public QueryAsynTask(WordListDao wordListDao){
            this.wordListDao = wordListDao;
        }


        @Override
        protected Void doInBackground(Word... words) {
            wordListDao.deleteallwords();
            return null;
        }
    }

    static class UpdateAsynTask extends AsyncTask<Word, Void, Void>{
        private WordListDao wordListDao;
        public UpdateAsynTask(WordListDao wordListDao){
            this.wordListDao = wordListDao;
        }
        @Override
        protected Void doInBackground(Word... words) {
            wordListDao.updatewords(words);
            return null;
        }
    }

    public void insertWords(Word... words){
        new InsertAsynTask(wordListDao).execute(words);
    }

    public void deleteallWords(){
        new QueryAsynTask(wordListDao).execute();
    }

    public void updateWords(Word... words){
        new UpdateAsynTask(wordListDao).execute(words);
    }
}
