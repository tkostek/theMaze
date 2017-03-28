package com.tkostek.themaze;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;
import android.view.View;

/**
 * Created by tkostek on 05.02.17.
 */

public class Perimeter {

    private Chamber location;
    private int range;
    private Displayer drawHere;


    public Perimeter() {
        setRange(3);
    }

    public void redraw(){
        getDrawHere().invalidate();
    }

    public Displayer getDrawHere() {
        return drawHere;
    }

    public void setDrawHere(Displayer drawHere) {
        this.drawHere = drawHere;
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

    public double calculateDistance(double clickX, double clickY){
        Point myPosition =  new Point(0, 0);
        Point screenPosition = new Point(0,0);
        for(int i = 0; i < getDrawHere().getScreens().size(); i++){
            if(getDrawHere().getScreens().get(i) == this){
                screenPosition = getDrawHere().getLocation().get(i);
            }
        }
        Point origin = new Point(screenPosition);
        int coord[] = new int[2];
        getDrawHere().getLocationOnScreen(coord);
        origin.x += coord[1];
        origin.y += coord[0];
        int cellSize = ((Bitmap)getDrawHere().getBmpMan().getImage().get("floor1")).getHeight();
        myPosition.x = getLocation().getScreenX(cellSize, location.getX() - getRange(), location.getY() - getRange(), origin);
        myPosition.y = getLocation().getScreenY(cellSize, location.getX() - getRange(), location.getY() - getRange(), origin);
        return Math.hypot(Math.abs(myPosition.x - clickX), Math.abs(myPosition.y - clickY));
    }
}
