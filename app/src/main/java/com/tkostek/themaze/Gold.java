package com.tkostek.themaze;

import android.util.Log;

/**
 * Created by tkostek on 28.03.17.
 */

public class Gold extends Item {

    private int value;

    public static void spawn(MapOfMaze map){
        Gold gold = new Gold(map.preferableFree());
    }

    public Gold(Chamber location){
        super(location);
        setValue(1);
        setDispMiddle();
        setPictureName("coin1");
    }

    public void collision(Dweller dweller){
        super.collision(dweller);
        if(dweller instanceof Player) {
            ((Player)dweller).getStatistics().increaseGold(getValue());
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
