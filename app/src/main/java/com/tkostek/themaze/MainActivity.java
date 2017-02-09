package com.tkostek.themaze;

import android.graphics.BitmapFactory;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Arrays;

import static com.tkostek.themaze.R.id.perimeter1;

public class MainActivity extends AppCompatActivity{

    private BitmapManager bmpMan;
    private GestureDetectorCompat mDetector;
    private ArrayList<Player> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBmpMan(new BitmapManager());
        MapOfMaze maze = new MapOfMaze(9, 9);
        setContentView(R.layout.activity_main);
        players.add(new Player(maze.getChamber(4, 4), (Perimeter) findViewById(perimeter1), getBmpMan()));
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
            double[] distances = new double[getPlayers().size()];
            Player move = null;
            double minim = 1000;

            for(int i = 0; i < distances.length; i++){
                Chamber pChamber = getPlayers().get(i).getLocation();
                distances[i] = Math.hypot(pChamber.getScreenX() - event1.getX(), pChamber.getScreenY() - event1.getY());
                if(distances[i] < minim){
                    minim = distances[i];
                    move = getPlayers().get(i);
                }
            }

            Arrays.sort(distances);
            if(distances.length > 1 && distances[0] * 2 > distances [1]){
                return true;
            }

            if(Math.abs(velocityX) / Math.abs(velocityY) > 3 && Math.abs(velocityX) > 100) {
                if(velocityX > 0){
                    move.tryRight();
                }
                else{
                    move.tryLeft();
                }
            }

            if(Math.abs(velocityY) / Math.abs(velocityX) > 3 && Math.abs(velocityY) > 100) {
                if(velocityY > 0){
                    move.tryDown();
                }
                else{
                    move.tryUp();
                }
            }
            Log.d(DEBUG_TAG, "velocity: " + velocityX + " " + velocityY);
            Log.d(DEBUG_TAG, "move: " + move.getLocation().getX() + " " + move.getLocation().getY());
            return true;
        }
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

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
