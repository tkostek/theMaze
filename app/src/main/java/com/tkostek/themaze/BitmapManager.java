package com.tkostek.themaze;

import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tkostek on 05.02.17.
 */

public class BitmapManager {

    Map image;

    public BitmapManager(){
        setImage(new HashMap());
    }

    public void addImage(Bitmap bmp, String name){
        getImage().put(name, bmp);
    }

    public Map getImage() {
        return image;
    }

    public void setImage(Map image) {
        this.image = image;
    }
}
