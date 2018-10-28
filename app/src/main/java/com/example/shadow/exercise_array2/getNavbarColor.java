package com.example.shadow.exercise_array2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;

public class getNavbarColor {

    public getNavbarColor() {
    }

    public int findNavbarColor(Bitmap bitmap,Context c) {

        Palette p = createPaletteSync(bitmap);
        Palette.Swatch vibrantSwatch = p.getLightVibrantSwatch();

        // Load default colors
        int backgroundColor = ContextCompat.getColor(c,
                R.color.favorite_nav);
        int textColor = ContextCompat.getColor(c,
                R.color.search_nav);

        // Check that the Vibrant swatch is available
        if(vibrantSwatch != null){
            backgroundColor = vibrantSwatch.getRgb();
            textColor = vibrantSwatch.getTitleTextColor();
        }
        return backgroundColor;
        // Set the toolbar background and text colors
        //bnve.setBackgroundColor(backgroundColor);
        //toolbar.setTitleTextColor(textColor);

    }
    public Palette createPaletteSync(Bitmap bitmap) {
        Palette p = Palette.from(bitmap).generate();
        return p;
    }
}

