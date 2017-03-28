package com.tkostek.themaze;

import android.util.Log;

/**
 * Created by tkostek on 28.03.17.
 */

public class Statistics {
    private int gold;

    public Statistics(){
        setGold(0);
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void increaseGold(int gold){
        setGold(getGold() + gold);
        Log.d("gold", getGold() + "$");
    }
}
