package com.mygdx.game;

import static com.mygdx.game.FruitFightMain.*;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Enemy {
    float x, y;
    float width, height;
    float vx, vy;
    boolean isAlive;
    Texture img0, img1;
    Texture img;
    public static final int FRUIT = 0, VEGGIE = 1;
    int type;
    int randomSprite;


    public Enemy(Texture[] imgFruits, Texture[] imgVeggies){
        type = MathUtils.random(0, 1);
        if(type == FRUIT) {
            int nPic = MathUtils.random(0, imgFruits.length/2-1) * 2;
            img0 = imgFruits[nPic];
            img1 = imgFruits[nPic + 1];
        } else {
            int nPic = MathUtils.random(0, imgVeggies.length/2-1) * 2;
            img0 = imgVeggies[nPic];
            img1 = imgVeggies[nPic + 1];
        }
        img = img0;


        width = img.getWidth();
        height = img.getHeight();
        x = MathUtils.random(width*1.6f, SCR_WIDTH-width*1.6f);
        y = SCR_HEIGHT+height+10;
        vy = MathUtils.random(-3f, -2f);
        vx = MathUtils.random(-2f, 2f);
    }

    void move(){
        if (y>GROUND){
            y += vy;
        }else{
            x += vx;
            img = img1;
        }
        if(outOfBounds()) vx=-vx;
    }
    public float getX() {
        return x-width/2;
    }

    public float getY() {
        return y;
    }

    boolean outOfBounds(){
        return x<width/2 || x>SCR_WIDTH-width/2;
    }
}
