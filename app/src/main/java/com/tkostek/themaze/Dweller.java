package com.tkostek.themaze;

/**
 * Created by tkostek on 04.02.17.
 */

public class Dweller {

    private Chamber location;

    public Chamber getLocation() {
        return location;
    }

    public Dweller(Chamber location, Perimeter around, BitmapManager mgr){
        this.location = location;
        this.around = around;
        around.setLocation(location);
        around.setBmpMan(mgr);
    }

    public void setLocation(Chamber location) {
        Chamber oldLocation = this.location;
        this.location = location;
        if(oldLocation != null){
            oldLocation.getInside().remove(this);
        }
        getAround().setLocation(location);
    }

    private Perimeter around;

    public Perimeter getAround() {
        return around;
    }

    public void setAround(Perimeter around) {
        this.around = around;
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
        return true;
    }

    public boolean tryUp(){
        return tryMove(0);
    }

    public boolean tryRight(){
        return tryMove(1);
    }

    public boolean tryDown(){
        return tryMove(2);
    }

    public boolean tryLeft(){
        return tryMove(3);
    }
}
