package com.mygdx.game;

import static com.mygdx.game.FruitFightMain.SCR_WIDTH;

public class Player {
    float x, y;
    float width, height;
    float vx;
    int lives = 3;
    public static final boolean RIGHT = false, LEFT = true;
    boolean direction = RIGHT;
    int faza;
    public static final int STAY = 0, GO = 1, CHOP = 2;
    int state = STAY;

    public Player(){
        width = 208;
        height = 212;
        x = SCR_WIDTH/2;
        y = 262;

    }

    boolean outOfBounds() {
        if(x < width/2) {
            x = width/2;

        }
        if(x > SCR_WIDTH-width/2){
            x = SCR_WIDTH-width/2;

        }
        return true;
    }
    public float getX() {
        return x-width/2;
    }

    public float getY() {
        return y-height/2;
    }
    void moveL(){
        vx = 1.8f;
        x -= vx;
        direction = LEFT;
        faza = 1;
        outOfBounds();
    }
    void moveR(){
        vx = 1.8f;
        x += vx;
        direction = RIGHT;
        faza = 1;
        outOfBounds();
    }

    void stay() {
        //state=STAY;
        faza = 0;
    }
    void killEnemy(){

    }

}
