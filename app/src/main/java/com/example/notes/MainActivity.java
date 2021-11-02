package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

private WordViewModel mWordViewModel;
private RecyclerView mRecycle;
private  WordAdapter mWordAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton floati=findViewById(R.id.button_add);
        floati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this,AddNewWordActivity.class);
                startActivityForResult(i,1);
            }
        });

        //recycle
        mRecycle=findViewById(R.id.words_recycle);
        mRecycle.setLayoutManager(new LinearLayoutManager(this));
        mRecycle.setHasFixedSize(true);

        //recycle with adapter
        mWordAdapter =new WordAdapter();
        mRecycle.setAdapter(mWordAdapter);

        //view model
        mWordViewModel= ViewModelProviders.of(this).get(WordViewModel.class);
        mWordViewModel.getAllWords().observe(this, new Observer<List<Words>>() {
            @Override
            public void onChanged(List<Words> words) {

                mWordAdapter.setWords(words);
            }
        });
        mWordAdapter.OnItemClickListener(new WordAdapter.OnItemCliclListener() {
            @Override
            public void onItemClick(Words word) {
                Intent i =new Intent(MainActivity.this,AddNewWordActivity.class);
                i.putExtra(AddNewWordActivity.EXTRA_ID,word.getId());
                i.putExtra(AddNewWordActivity.EXTRA_WORD,word.getWordName());
                i.putExtra(AddNewWordActivity.EXTRA_MEANING,word.getWordMeaning());
                i.putExtra(AddNewWordActivity.EXTRA_TYPE,word.getWordType());

                startActivity(i);

            }
        });
         new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                 ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
             @Override
             public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                 return false;
             }

             @Override
             public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                 int pos=viewHolder.getAdapterPosition() ;
                 mWordViewModel.delete(mWordAdapter.getWordAt(pos));
             }
         }).attachToRecyclerView(mRecycle);
    }
}