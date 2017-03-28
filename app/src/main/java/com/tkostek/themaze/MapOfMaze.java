package com.tkostek.themaze;

import android.util.Log;

import com.tkostek.themaze.helpTools.FindAndUnion;
import com.tkostek.themaze.helpTools.Wall;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by tkostek on 04.02.17.
 */

public class MapOfMaze {

    private final int MAX_GRID_SIZE = 100;

    private int width, height;
    private Chamber[][] grid = new Chamber[MAX_GRID_SIZE][MAX_GRID_SIZE];

    public MapOfMaze(int width, int height){

        setWidth(width);
        setHeight(height);

        // Chambers initialization
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                setChamber(i, j, new Chamber(this, i, j));
            }
        }

        // neighbourhood initialization
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){

                if(i > 0){
                    getChamber(i, j).setNeighbour(getChamber(i - 1, j), 0);
                }
                if(j < width - 1){
                    getChamber(i, j).setNeighbour(getChamber(i, j + 1), 1);
                }
                if(i < height - 1){
                    getChamber(i, j).setNeighbour(getChamber(i + 1, j), 2);
                }
                if(j > 0){
                    getChamber(i, j).setNeighbour(getChamber(i, j - 1), 3);
                }

            }
        }

        // vertical Crossings initialization
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width - 1; j++){
                getChamber(i, j    ).setCrossing(new Crossing(0, i, j, false),    1);
                getChamber(i, j + 1).setCrossing(getChamber(i, j).getCrossing(1), 3);
            }
        }

        // horizontal Crossings initialization
        for(int i = 0; i < height - 1; i++){
            for(int j = 0; j < width; j++){
                getChamber(i, j    ).setCrossing(new Crossing(0, i, j, true),     2);
                getChamber(i + 1, j).setCrossing(getChamber(i, j).getCrossing(2), 0);
            }
        }

        // border initialization
        for(int j = 0; j < width; j++){
            getChamber(0, j).setNeighbour(null, 0);
            getChamber(0, j).setCrossing(new Crossing(0, -1, j, true), 0);
            getChamber(height - 1, j).setNeighbour(null, 2);
            getChamber(height - 1, j).setCrossing(new Crossing(0, height, j, true), 2);
        }

        for(int i = 0; i < height; i++){
            getChamber(i, 0).setNeighbour(null, 3);
            getChamber(i, 0).setCrossing(new Crossing(0, i, -1, false), 3);
            getChamber(i, width - 1).setNeighbour(null, 1);
            getChamber(i, width - 1).setCrossing(new Crossing(0, i, width, false), 1);
        }

        mazeBuilder();
    }

    private ArrayList<Chamber> getChamberList(){
        ArrayList result = new ArrayList<>();
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                result.add(getChamber(i, j));
            }
        }

        Collections.shuffle(result);
        return result;
    }

    private void mazeBuilder(){

        ArrayList<Wall> options = new ArrayList<>();
        FindAndUnion groups = new FindAndUnion(height, width);
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                for(int k = 0; k < 4; k++){
                    options.add(new Wall(i, j, k));
                }
            }
        }

        Collections.shuffle(options);

        for(Wall w: options){


            Chamber tmpChamber = getChamber(w.getX(), w.getY());
            Chamber tmpNeighbour = tmpChamber.getNeighbour(w.getDirection());

            if(tmpNeighbour == null){
                continue;
            }

            if(groups.find(tmpChamber.getX(), tmpChamber.getY()) == groups.find(tmpNeighbour.getX(), tmpNeighbour.getY())) {
                continue;
            }

            groups.union(tmpChamber.getX(), tmpChamber.getY(), tmpNeighbour.getX(), tmpNeighbour.getY());
            tmpChamber.getCrossing(w.getDirection()).setState(1);
            tmpNeighbour.getCrossing((w.getDirection() + 2) % 4).setState(1);

        }
    }

    public Chamber preferableFree(){
        ArrayList<Chamber> options = getChamberList();

        for(Chamber chamber: options){
            if(chamber.getInside().size() == 0){
                return chamber;
            }
        }

        return options.get(0);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Chamber getChamber(int x, int y){
        return grid[x][y];
    }

    public void setChamber(int x, int y, Chamber newChamber){
        grid[x][y] = newChamber;
    }
}
