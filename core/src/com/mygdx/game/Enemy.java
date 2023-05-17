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

    public Enemy(Texture imgEnemy){
        img = imgEnemy;
        width = img.getWidth();
        height = img.getHeight();
        x = MathUtils.random(width*1.6f, SCR_WIDTH-width*1.6f);
        y = MathUtils.random(SCR_HEIGHT+height/2, SCR_HEIGHT*1.5f);
        vy = MathUtils.random(-2f, -1f);
        vx = MathUtils.random(-1.8f, 1.8f);
    }

    void move(){
        if (y>200){
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
