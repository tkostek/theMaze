package com.tkostek.themaze;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static com.tkostek.themaze.R.id.activity_main;
import static com.tkostek.themaze.R.id.perimeter1;

public class MainActivity extends AppCompatActivity {

    BitmapManager bmpMan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBmpMan(new BitmapManager());
        MapOfMaze maze = new MapOfMaze(9, 9);
        setContentView(R.layout.activity_main);
        Player player1 = new Player(maze.getChamber(4, 4), (Perimeter) findViewById(perimeter1), getBmpMan());
    }

    public BitmapManager getBmpMan() {
        return bmpMan;
    }

    public void setBmpMan(BitmapManager bmpMan) {
        this.bmpMan = bmpMan;
        bmpMan.addImage(BitmapFactory.decodeResource(getResources(), R.drawable.horizontal), "horizontalWall1");
        bmpMan.addImage(BitmapFactory.decodeResource(getResources(), R.drawable.vertical), "verticalWall1");
        bmpMan.addImage(BitmapFactory.decodeResource(getResources(), R.drawable.floor), "floor1");
        bmpMan.addImage(BitmapFactory.decodeResource(getResources(), R.drawable.hero1), "hero1");
    }
}
