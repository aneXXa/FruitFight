package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class ImgButton {
    float x, y;
    float width, height;
    Texture texture;

    public ImgButton(Texture texture, float x, float y, float width, float height){
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    boolean hit(float tx, float ty){
        if(tx > x && tx < x+width && ty > y && ty < y+height){
            return true;
        }
        return false;
    }
}
