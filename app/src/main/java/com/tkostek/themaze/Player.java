package com.tkostek.themaze;

/**
 * Created by tkostek on 05.02.17.
 */

public class Player extends Dweller {

    Statistics statistics;

    public Player (Chamber location){
        super(location);
        setVisible(true);
        setDispMiddle();
        setPictureName("hero1");
        statistics = new Statistics();
    }

    public Statistics getStatistics() {
        return statistics;
    }
}
