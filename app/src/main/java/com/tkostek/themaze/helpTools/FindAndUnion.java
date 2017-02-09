package com.tkostek.themaze.helpTools;

import java.util.ArrayList;

/**
 * Created by tkostek on 04.02.17.
 */

public class FindAndUnion {
    private ArrayList<Integer>parent = new ArrayList<Integer>();
    private int size2;

    public FindAndUnion(int size, int size2){
        setSize2(size2);
        for(int i = 0; i < size * size2; i++){
            parent.add(i);
        }
    }

    public FindAndUnion(int size){
        setSize2(1);
        for(int i = 0; i < size; i++){
            parent.add(i);
        }
    }

    public int find(int x, int x2){
        return find(x * getSize2() + x2);
    }

    public int find(int x){
        if(parent.get(x) == x){
            return x;
        }
        else{
            parent.set(x, find(parent.get(x)));
            return parent.get(x);
        }
    }

    public void union(int x, int x2, int y, int y2){
        union(x * getSize2() + x2, y * getSize2() + y2);
    }

    public void union(int x, int y){
        x = parent.get(x);
        y = parent.get(y);
        parent.set(x, y);
    }

    public int getSize2(){
        return size2;
    }

    public void setSize2(int size2){
        this.size2 = size2;
    }
}
