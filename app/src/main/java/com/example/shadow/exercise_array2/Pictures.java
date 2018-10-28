package com.example.shadow.exercise_array2;

import android.graphics.drawable.Drawable;

public class Pictures {
    String memeber;
    String dec;
    int image;

    public Pictures(String memeber, String dec, int image) {
        this.memeber = memeber;
        this.dec = dec;
        this.image = image;
    }

    public String getMemeber() {
        return memeber;
    }

    public void setMemeber(String memeber) {
        this.memeber = memeber;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
