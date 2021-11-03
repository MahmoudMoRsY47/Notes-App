package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewWordActivity extends AppCompatActivity {
    private EditText wordedt;
    private EditText meanedt;
    private EditText typeedt;

    boolean editMood;
    private int mID;
    public static final String EXTRA_ID="com.example.notes extraid";
    public static final String EXTRA_WORD="com.example.notes word";
    public static final String EXTRA_MEANING="com.example.notes meaning";
    public static final String EXTRA_TYPE = "com.example.wordlist.type";


    //view modelto add new word activity
    private AddNewWordViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_word);
        wordedt =findViewById(R.id.edt_word);
        meanedt =findViewById(R.id.edt_meaninig);
        typeedt = findViewById(R.id.edt_type);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_exit);
        Intent i = getIntent();
        if(i.hasExtra(EXTRA_ID))
        {
            setTitle("Edit Word");
            editMood=true;
            mID=i.getIntExtra(EXTRA_ID,-1);
            wordedt.setText(i.getStringExtra(EXTRA_WORD));
            meanedt.setText(i.getStringExtra(EXTRA_MEANING));
            typeedt.setText(i.getStringExtra(EXTRA_TYPE));



        }else {
            setTitle("Add new Word");
            editMood=false;
        }

        mViewModel= ViewModelProviders.of(this).get(AddNewWordViewModel.class);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m=getMenuInflater();
        m.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.save_word:
                saveWord();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    public void saveWord()
    {
        String word = wordedt.getText().toString().trim();
        String meaning = meanedt.getText().toString().trim();
        String type = typeedt.getText().toString().trim();


        Words wordObject = new Words(word, meaning,type);

        if (word.isEmpty() || meaning.isEmpty() || type.isEmpty()) {
            Toast.makeText(AddNewWordActivity.this, "please fill all fields", Toast.LENGTH_LONG).show();
            return;
        }
        if(editMood) {
            wordObject.setId(mID);
            mViewModel.update(wordObject);
        }else{
            mViewModel.insert(wordObject);
        }
        finish();
    }

}