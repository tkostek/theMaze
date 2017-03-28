package com.tkostek.themaze;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
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

    public int getScreenX(int size, int relativeX, int relativeY, Point origin) {
        return (getY() - relativeY) * size + origin.y;
    }

    public int getScreenY(int size, int relativeX, int relativeY, Point origin) {
        return (getX() - relativeX) * size + origin.x;
    }

    public void printOnScreen(Canvas canvas, BitmapManager mgr, int relativeX, int relativeY, Point origin){

        int size = ((Bitmap) mgr.getImage().get("floor1")).getHeight();
        int thickness = ((Bitmap) mgr.getImage().get("horizontalWall1")).getHeight();

        Matrix leftCorner   = new Matrix();
        Matrix rightSide    = new Matrix();
        Matrix downSide     = new Matrix();
        Point screenLoc = new Point(getScreenX(size, relativeX, relativeY, origin), getScreenY(size, relativeX, relativeY, origin));

        leftCorner.postTranslate(screenLoc.x, screenLoc.y);
        downSide.postTranslate(screenLoc.x, screenLoc.y + size - thickness);
        rightSide.postTranslate(screenLoc.x + size - thickness, screenLoc.y);
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
            if(!d.isVisible())
                continue;
            Matrix displayDweller = new Matrix();
            displayDweller.postTranslate(screenLoc.x + d.getDispX() * size, screenLoc.y + d.getDispY() * size);
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
