package com.mygdx.game;

import static com.mygdx.game.FruitFightMain.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class ScreenGame implements Screen {
    FruitFightMain f;
    Texture bgGame;


    public ScreenGame(FruitFightMain context){
        f = context;
        bgGame = new Texture("bgIntro.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // screen touch
        if(Gdx.input.justTouched()) {
            f.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            f.camera.unproject(f.touch);
        }

        //rendering graphics
        f.camera.update();
        f.batch.setProjectionMatrix(f.camera.combined);
        f.batch.begin();
        f.batch.draw(bgGame, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        f.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
