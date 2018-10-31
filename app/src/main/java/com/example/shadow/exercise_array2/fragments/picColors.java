package com.example.shadow.exercise_array2.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.shadow.exercise_array2.R;

import java.util.ArrayList;

public class picColors {
    public int[] images = {R.drawable.cats,R.drawable.cute,R.drawable.poppy,R.drawable.dumb,R.drawable.sea};
    ArrayList<Integer> color;
    Context c;

    public picColors(Context c) {
        this.c = c;
    }

    public ArrayList<Integer> getBitmap(){
        color = new ArrayList<>();
        getNavbarColor navbarColor = new getNavbarColor();
        for (int i=0;i<images.length;i++){
            Bitmap icon = BitmapFactory.decodeResource(c.getResources(), images[i]);
            color.add(navbarColor.findNavbarColor(icon,c));
        }
        return color;
    }



    public int[] getImages() {
        return images;
    }


}
