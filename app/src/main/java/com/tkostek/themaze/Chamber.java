package com.tkostek.themaze;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by tkostek on 03.02.17.
 */

public class Chamber {

    private Chamber[] neighbour = new Chamber[4];
    private Crossing[] crossing = new Crossing[4];
    private int X, Y;
    private MapOfMaze myMap;


    private ArrayList<Dweller> inside = new ArrayList<Dweller>();

    public Chamber(MapOfMaze myMap, int X, int Y){
        setMyMap(myMap);
        setX(X);
        setY(Y);
    }

    public void printOnScreen(Canvas canvas, BitmapManager mgr){
        int size = ((Bitmap) mgr.getImage().get("floor1")).getHeight();
        int thickness = ((Bitmap) mgr.getImage().get("horizontalWall1")).getHeight();
        Matrix leftCorner   = new Matrix();
        Matrix rightSide    = new Matrix();
        Matrix downSide     = new Matrix();
        leftCorner.postTranslate(getY() * size, getX() * size);
        downSide.postTranslate(getY() * size, getX() * size + size - thickness);
        rightSide.postTranslate(getY() * size + size - thickness, getX() * size);
        canvas.drawBitmap((Bitmap) mgr.getImage().get("floor1"), leftCorner, null);

        if(getCrossing(0).getState() == 0){
            canvas.drawBitmap((Bitmap) mgr.getImage().get("horizontalWall1"), leftCorner, null);
        }

        if(getCrossing(1).getState() == 0){
            canvas.drawBitmap((Bitmap) mgr.getImage().get("verticalWall1"), rightSide, null);
        }

        if(getCrossing(2).getState() == 0){
            canvas.drawBitmap((Bitmap) mgr.getImage().get("horizontalWall1"), downSide, null);
        }

        if(getCrossing(3).getState() == 0){
            canvas.drawBitmap((Bitmap) mgr.getImage().get("verticalWall1"), leftCorner, null);
        }

        for(Dweller d: inside){
            Log.d("player", Boolean.toString(d.isVisible()));
            if(!d.isVisible())
                continue;
            Matrix displayDweller = new Matrix();
            displayDweller.postTranslate(getY() * size + d.getDispX() * size, getX() * size + d.getDispY() * size);
            canvas.drawBitmap((Bitmap) mgr.getImage().get(d.getPictureName()), displayDweller, null);
        }
    }

    public MapOfMaze getMyMap() {
        return myMap;
    }

    public void setMyMap(MapOfMaze myMap) {
        this.myMap = myMap;
    }

    public Chamber getNeighbour(int direction){
        return neighbour[direction];
    }

    public void setNeighbour(Chamber newNeighbour, int direction){
        neighbour[direction] = newNeighbour;
    }

    public Crossing getCrossing(int direction){
        return crossing[direction];
    }

    public void setCrossing(Crossing newCrossing, int direction){
        crossing[direction] = newCrossing;
    }

    public int getX(){
        return X;
    }

    public void setX(int newX){
        X = newX;
    }

    public int getY(){
        return Y;
    }

    public void setY(int newY){
        Y = newY;
    }

    public ArrayList<Dweller> getInside() {
        return inside;
    }
}
