package com.mygdx.game;

public class GameObject {
    float x, y;
    float width, height;
    float vx, vy;

    public GameObject(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    void move(){
        x += vx;
        y += vy;
    }
}
