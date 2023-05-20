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
    Texture img0, img1;
    int type;
    int randomSprite;
    Texture[] EnemyFruit0 = new Texture[5];
    Texture[] EnemyFruit1= new Texture[5];
    //

    public Enemy(){
        type = MathUtils.random(0,1);
        randomSprite = MathUtils.random(1, EnemyFruit0.length-1);
        for (int i = 1; i < EnemyFruit0.length; i++) {
            EnemyFruit0[i] = new Texture("Enemy.Fruit."+i+".0.png");
        }
        for (int i = 1; i < EnemyFruit0.length; i++) {
            EnemyFruit1[i] = new Texture("Enemy.Fruit."+i+".1.png");
        }
        img0 = EnemyFruit0[randomSprite];
        img1 = EnemyFruit1[randomSprite];
        width = img0.getWidth();
        height = img0.getHeight();
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
            img0 = img1;
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
