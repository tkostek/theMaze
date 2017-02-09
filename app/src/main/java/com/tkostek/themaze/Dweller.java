package com.tkostek.themaze;

import android.util.Log;

/**
 * Created by tkostek on 04.02.17.
 */

public class Dweller {

    private Chamber location;
    private float dispX, dispY;
    private boolean visible;
    private String pictureName;
    private Perimeter around;

    public Dweller(Chamber location, Perimeter around, BitmapManager mgr){
        setAround(around);
        setLocation(location);
        around.setLocation(location);
        around.setBmpMan(mgr);
        setVisible(false);
    }

    public void setLocation(Chamber location) {
        Chamber oldLocation = this.location;
        this.location = location;
        if(oldLocation != null){
            oldLocation.getInside().remove(this);
        }
        getAround().setLocation(location);
        location.getInside().add(this);
    }

    public Chamber getLocation() {
        return location;
    }

    public Perimeter getAround() {
        return around;
    }

    public void setAround(Perimeter around) {
        this.around = around;
    }

    public float getDispX() {
        return dispX;
    }

    public void setDispX(float dispX) {
        this.dispX = dispX;
    }

    public float getDispY() {
        return dispY;
    }

    public void setDispY(float dispY) {
        this.dispY = dispY;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    private boolean tryMove(int direction){

        Crossing entrance = getLocation().getCrossing(direction);

        if(entrance == null){
            return false;
        }

        if(entrance.getState() == 0){
            return false;
        }

        setLocation(getLocation().getNeighbour(direction));
        getAround().invalidate();
        Log.d("move", "OK");
        return true;
    }

    public boolean tryUp(){
        Log.d("move", "tryUp");
        return tryMove(0);
    }

    public boolean tryRight(){
        Log.d("move", "tryRight");
        return tryMove(1);
    }

    public boolean tryDown(){
        Log.d("move", "tryDown");
        return tryMove(2);
    }

    public boolean tryLeft(){
        Log.d("move", "tryLeft");
        return tryMove(3);
    }
}
