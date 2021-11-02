package com.example.notes;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Words>> getAllWords;
    public WordRepository (Application app)
    {
        WordRoomDb dp= WordRoomDb.getInstance(app);
        mWordDao =dp.wordsDao();
        getAllWords=mWordDao.getAllWords();
    }
     public void insert(Words word)
     {
         new InsertAsyncTask(mWordDao).execute(word);
     }

    public void delete(Words word)
    {
        new DeleteAsyncTask(mWordDao).execute(word);
    }
    public void update(Words word)
    {
        new UpdateAsyncTask(mWordDao).execute(word);
    }

    public LiveData<List<Words>> getAllWords()
    {
        return getAllWords;
    }

    public void deleteAllWords()
    {
        new DeleteAllWordsAsyncTask(mWordDao).execute();
    }
    private static class InsertAsyncTask extends AsyncTask<Words,Void,Void>{
       private WordDao mWordsDao;

        public InsertAsyncTask(WordDao wordsDao) {
            mWordsDao = wordsDao;
        }

        @Override
        protected Void doInBackground(Words... wordsEntities) {
           mWordsDao.insert(wordsEntities[0]);
            return null;
        }
    }
    private static class UpdateAsyncTask extends AsyncTask<Words,Void,Void>{
        private WordDao mWordsDao;

        public UpdateAsyncTask(WordDao wordsDao) {
            mWordsDao = wordsDao;
        }

        @Override
        protected Void doInBackground(Words... wordsEntities) {
            mWordsDao.update(wordsEntities[0]);
            return null;
        }
    }
    private static class DeleteAsyncTask extends AsyncTask<Words,Void,Void>{
        private WordDao mWordsDao;

        public DeleteAsyncTask(WordDao wordsDao) {
            mWordsDao = wordsDao;
        }

        @Override
        protected Void doInBackground(Words... wordsEntities) {
            mWordsDao.delete(wordsEntities[0]);
            return null;
        }
    }
    private static class DeleteAllWordsAsyncTask extends AsyncTask<Void,Void,Void>{
        private WordDao mWordsDao;

        public DeleteAllWordsAsyncTask(WordDao wordsDao) {
            mWordsDao = wordsDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mWordsDao.deleteAllWords();
            return null;
        }
    }
}
