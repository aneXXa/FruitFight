package com.mygdx.game;

import static com.mygdx.game.FruitFightMain.*;

import com.badlogic.gdx.utils.TimeUtils;

public class Player {
    float x, y;
    float width, height;
    float vx;
    int lives = 3;
    public static final boolean RIGHT = false, LEFT = true;
    boolean direction = LEFT;
    int faza;
    //public static final int STAY = 0, GO = 1, CHOP = 2;
    //int state = STAY;
    boolean isChop;
    long timeNewFaza, timeFazaInterval = 100;

    public Player(){
        width = 208;
        height = 212;
        x = SCR_WIDTH/2;
        y = GROUND;

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
        return y;
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

    void chop(){
        if(timeNewFaza+timeFazaInterval < TimeUtils.millis()) {
            faza++;
            if (faza == 5) {
                faza = 1;
                isChop = false;
            }
            timeNewFaza = TimeUtils.millis();
        }
    }

    void stay() {
        //state=STAY;
        faza = 0;
    }

    boolean overlap(Enemy enemy){
        /*if(direction == LEFT) {
            return x - enemy.x > width/2 && enemy.y<=GROUND;
        }
        if (direction == RIGHT){
            return enemy.x - x < width/2 && enemy.y<=GROUND;
        }
        return false;*/
        return Math.abs(x-enemy.x) < width/2 + enemy.width/4  && enemy.y <= GROUND;
        // сейчас работает в обе стороны без директиона
        // оверлеп работает только так, возможно что-то не то с direction
    }

}
