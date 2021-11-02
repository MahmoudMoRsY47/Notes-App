package com.example.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    private List<Words> mWordList = new ArrayList<>();
    private  OnItemCliclListener mListner;

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.word_list_item,parent,false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Words currentWord=mWordList.get(position);
        holder.textViewWord.setText(currentWord.getWordName());
        holder.textViewMeaning.setText(currentWord.getWordMeaning());
        holder.textViewType.setText(currentWord.getWordType());

    }

    //
    public void setWords(List<Words>words)
    {
        mWordList=words;
        notifyDataSetChanged();
    }

    //

    @Override
    public int getItemCount() {
        return mWordList.size();
    }

    public  class WordViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewWord;
        public TextView textViewMeaning;
        public TextView textViewType;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewWord = itemView.findViewById(R.id.word_tv);
            textViewMeaning = itemView.findViewById(R.id.meaning_tv);
            textViewType = itemView.findViewById(R.id.type_tv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = getAdapterPosition();
                    if(mListner != null && index != RecyclerView.NO_POSITION){
                        mListner.onItemClick(mWordList.get(index));
                    }
                }
            });
        }
    }
    public interface OnItemCliclListener
    {
        void onItemClick(Words word);
    }

    public void OnItemClickListener(OnItemCliclListener listener)
    {
        mListner = listener;
    }
    public Words getWordAt(int pos)
    {
        return mWordList.get(pos);
    }

}
