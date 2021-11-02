package com.example.notes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordDao {
    @Insert
    void insert(Words word);
    @Update
    void update(Words word);
    @Delete
    void delete(Words word);
    @Query("Delete From wordTable")
    void deleteAllWords();
    @Query("Select * From wordTable")
    LiveData<List<Words>>getAllWords();
}
