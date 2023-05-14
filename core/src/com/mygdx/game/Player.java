package com.mygdx.game;

import static com.mygdx.game.FruitFightMain.SCR_WIDTH;

public class Player {
    float x, y;
    float width, height;
    float vx, vy;
    int lives = 3;

    public Player(){
        x = SCR_WIDTH/2;
        y = 200;
        width = 60;
        height = 70;
    }

    boolean outOfBounds() {
        if(x < width/2) {
            x = width/2;
            vx = 0;
        }
        if(x > SCR_WIDTH-width/2){
            x = SCR_WIDTH-width/2;
            vx = 0;
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
        vx = 1.5f;
        x -= vx;
        outOfBounds();
    }
    void moveR(){
        vx = 1.5f;
        x += vx;
        outOfBounds();
    }

    void killEnemy(){

    }

}
