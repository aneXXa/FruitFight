package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Player {
    float x, y;
    float width, height;
    float vx;
    boolean isAlive;

    public Player(Texture texture, float x, float y){
        width = height = 250;
        x = this.x;
        y = this.y;
        vx = 1f;
    }

    void moveL(){
        x -= vx;
    }

    void moveR(){
        x += vx;
    }
}
