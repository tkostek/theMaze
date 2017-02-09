package com.tkostek.themaze;

/**
 * Created by tkostek on 05.02.17.
 */

public class Player extends Dweller {

    public Player (Chamber location, Perimeter around, BitmapManager mgr){
        super(location, around, mgr);
        setVisible(true);
        setDispX((float)25 / 100);
        setDispY((float)25 / 100);
        setPictureName("hero1");
    }
}
