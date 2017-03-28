package com.tkostek.themaze;

/**
 * Created by tkostek on 28.03.17.
 */

public class Item extends Dweller {

    public Item(Chamber location){
        super(location);
        setVisible(true);
    }

    public void collision(Dweller dweller){
        super.collision(dweller);
        if(dweller instanceof Player) {
            setLocation(null);
        }
    }
}
