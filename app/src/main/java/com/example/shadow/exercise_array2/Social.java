package com.example.shadow.exercise_array2;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Social extends android.support.v7.widget.RecyclerView.Adapter<Social.myHolder> {
    ArrayList<Pictures> al;
    Context c;

    public Social(ArrayList<Pictures> al, Context c) {
        this.al = al;
        this.c = c;
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(c).inflate(R.layout.list,null);
        myHolder mh = new myHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder myHolder, int i) {
        myHolder.user.setText(al.get(i).memeber);
        myHolder.dec.setText(al.get(i).dec);
        Glide.with(c).load(al.get(i).image).into(myHolder.image);
        myHolder.comment.setImageResource(R.drawable.comment);

        myHolder.image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(c, "loooooooooooong!", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return al.size();
    }


    public class myHolder extends RecyclerView.ViewHolder {
        TextView user;
        TextView dec;
        ImageView image;
        CheckBox like;
        ImageView comment;

        public myHolder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.name);
            dec = itemView.findViewById(R.id.dec);
            image = itemView.findViewById(R.id.imageView);
            like = itemView.findViewById(R.id.like);
            comment = itemView.findViewById(R.id.comment);

        }

    }
}
