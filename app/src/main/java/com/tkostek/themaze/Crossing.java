package com.tkostek.themaze;

/**
 * Created by tkostek on 03.02.17.
 */

public class Crossing {

    private boolean horizontal;
    private int X, Y, state;
    private Chamber[] neighbour = new Chamber[2];

    public Crossing(int state, int X, int Y, boolean horizontal){
        setState(state);
        setX(X);
        setY(Y);
        setHorizontal(horizontal);
    }

    public boolean getHorizontal(){
        return horizontal;
    }

    public void setHorizontal(boolean newHorizontal){
        horizontal = newHorizontal;
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

    public int getState(){
        return state;
    }

    public void setState(int newState){
        state = newState;
    }

    public Chamber getNeighbour(int direction){
        return neighbour[direction];
    }

    public void setChamber(int direction, Chamber newNeighbour){
        neighbour[direction] = newNeighbour;
    }

}
