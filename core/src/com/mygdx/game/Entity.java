package com.mygdx.game;

public class Entity {
    float x, y;
    float width, height;
    float vx;

    public Entity(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    void move(){
        x += vx;
    }
}
