package com.example.notes;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Entity (tableName = "wordTable")
public class Words {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String wordName;
    private String wordMeaning;
    @ColumnInfo(name = "Type")
    private String wordType;

    public Words(String wordName, String wordMeaning, String wordType) {
        this.wordName = wordName;
        this.wordMeaning = wordMeaning;
        this.wordType = wordType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getWordName() {
        return wordName;
    }

    public String getWordMeaning() {
        return wordMeaning;
    }

    public String getWordType() {
        return wordType;
    }


}
