package com.tkostek.themaze.helpTools;

/**
 * Created by tkostek on 04.02.17.
 */

public class Wall {
    int X, Y, direction;
    public Wall(int X, int Y, int direction){
        setX(X);
        setY(Y);
        setDirection(direction);
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
