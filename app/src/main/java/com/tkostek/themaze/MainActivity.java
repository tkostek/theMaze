package com.tkostek.themaze;

import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Arrays;

import static com.tkostek.themaze.R.id.displayer1;

public class MainActivity extends AppCompatActivity{

    private BitmapManager bmpMan;
    private Displayer mainDisplayer;
    private GestureDetectorCompat mDetector;
    private ArrayList<Player> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBmpMan(new BitmapManager());
        setContentView(R.layout.activity_main);
        mainDisplayer = (Displayer) findViewById(displayer1);
        mainDisplayer.setBmpMan(getBmpMan());//error

        MapOfMaze maze = new MapOfMaze(20, 20);

        players.add(new Player(maze.getChamber(2, 2)));
        players.add(new Player(maze.getChamber(7, 7)));

        mainDisplayer.newScreen(players.get(0).getAround(), new Point(0,0));
        mainDisplayer.newScreen(players.get(1).getAround(), new Point(330,0));

        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        Gold.spawn(maze);
        Gold.spawn(maze);
        Gold.spawn(maze);
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
                distances[i] = getPlayers().get(i).getAround().calculateDistance(event1.getX(), event1.getY());
                if(distances[i] < minim){
                    minim = distances[i];
                    move = getPlayers().get(i);
                }
            }
            Log.d(DEBUG_TAG, "distances: " + distances[0] + " " + distances[1]);
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
        bmpMan.addImage(BitmapFactory.decodeResource(getResources(), R.drawable.coin1), "coin1");
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
