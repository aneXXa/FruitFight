package com.mygdx.game;

import static com.mygdx.game.FruitFightMain.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Enemy {
    float x, y;
    float width, height;
    float vx;
    boolean isAlive;

    public Enemy(){
        width = height = 250;
        x = MathUtils.random(width/2, SCR_WIDTH-width/2);
        y = 100;
        vx = 2f;
    }

    void move(){
        x += vx;
        outOfBounds();
    }

    void outOfBounds(){
        if(x<0 || x>SCR_WIDTH-width/2) vx = -vx;
    }

}
