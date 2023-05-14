package com.mygdx.game;

import static com.mygdx.game.FruitFightMain.SCR_HEIGHT;
import static com.mygdx.game.FruitFightMain.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Enemy {
    float x, y;
    float width, height;
    float vx, vy;
    boolean isAlive;
    Texture img;

    public Enemy(Texture imgEnemyApple){
        img = imgEnemyApple;
        x = MathUtils.random(width/2, SCR_WIDTH-width/2);
        y = MathUtils.random(SCR_HEIGHT+height/2, SCR_HEIGHT*1.5f);
        width = 100;
        height = 100;
        vy = MathUtils.random(-2f, -1f);
        vx = MathUtils.random(-1.8f, 1.8f);
    }

    void move(){
        if (y>100){
            y += vy;
        }else{
            x += vx;
        }
        if(outOfBounds()) vx=-vx;
    }
    public float getX() {
        return x-width/2;
    }

    public float getY() {
        return y-height/2;
    }

    boolean outOfBounds(){
        return x<width/2 || x>SCR_WIDTH-width/2;
    }
}
