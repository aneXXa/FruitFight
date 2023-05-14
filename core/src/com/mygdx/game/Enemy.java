package com.mygdx.game;

import static com.mygdx.game.FruitFightMain.SCR_HEIGHT;
import static com.mygdx.game.FruitFightMain.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Enemy extends GameObject{

    boolean isAlive;

    public Enemy(){
        super(0,0,250,250);
        x = MathUtils.random(width/2, SCR_WIDTH-width/2);
        y = MathUtils.random(SCR_HEIGHT+height/2, SCR_HEIGHT*2);
        vy = MathUtils.random(-2f, -1f);
        vx = MathUtils.random(-1.8f, 1.8f);
    }

    void move(){
        if (y>100){
            y += vy;
        }else{
            x += vx;
        }
        outOfBounds();
    }


    void outOfBounds(){
        if(x<0 || x>SCR_WIDTH) vx = -vx;
    }

}
