package com.mygdx.game;

import static com.mygdx.game.FruitFightMain.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;

public class Enemy {
    float x, y;
    float width, height;
    float vx;
    boolean isAlive;

    public Enemy(Texture texture, float x, float y){
        width = height = 250;
        x = this.x;
        y = this.y;
        vx = 0.8f;
    }

    void move(){
        x += vx;
        outOfBounds();
    }

    void outOfBounds(){
        if(x<0-width/2 || x> SCR_WIDTH-width/2) vx = -vx;
    }

}
