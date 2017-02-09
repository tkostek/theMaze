package com.tkostek.themaze;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by tkostek on 05.02.17.
 */

public class Perimeter extends View {

    private Chamber location;
    private int range;

    BitmapManager bmpMan;

    public Perimeter(Context context, AttributeSet attrs) {
        super(context, attrs);
        range = 3;
    }

    public BitmapManager getBmpMan() {
        return bmpMan;
    }

    public void setBmpMan(BitmapManager bmpMan) {
        this.bmpMan = bmpMan;
    }

    public Chamber getLocation() {
        return location;
    }

    public void setLocation(Chamber location) {
        this.location = location;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i = 0; i < getLocation().getMyMap().getHeight(); i++){
            for(int j = 0; j < getLocation().getMyMap().getWidth(); j++){
                if(Math.pow(i - getLocation().getX(),2) + Math.pow(j - getLocation().getY(),2) <= Math.pow(getRange(),2)){
                    getLocation().getMyMap().getChamber(i, j).printOnScreen(canvas, getBmpMan());
                }
            }
        }
    }
}
