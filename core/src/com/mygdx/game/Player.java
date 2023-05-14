package com.mygdx.game;

import static com.mygdx.game.FruitFightMain.SCR_WIDTH;

public class Player {
    float x, y;
    float width, height;
    float vx, vy;
    int lives = 3;

    public Player(){
        x = SCR_WIDTH/2;
        y = 100;
        width = 100;
        height = 100;
        vx = 1.5f;
    }
    void move() {
        x += vx;
        y += vy;
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
        x -= vx;
        outOfBounds();
    }
    void moveR(){
        x += vx;
        outOfBounds();
    }

    void killEnemy(){

    }

}
