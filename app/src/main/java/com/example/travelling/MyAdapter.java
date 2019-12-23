package com.example.travelling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context c;
    ArrayList<DateCard> dc;
    private String[] mDataset;



    public MyAdapter(Context c, ArrayList<DateCard> dc){
        this.c = c;
        this.dc = dc;
    }



    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.date_card_holder,null);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        holder.textView.setText((dc.get(position).getText()));

    }

    @Override
    public int getItemCount() {
        return dc.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;
        public MyViewHolder(View v){
            super(v);

            this.textView = v.findViewById(R.id.date_string);
        }
    }









}
