package com.mygdx.game;

import static com.mygdx.game.FruitFightMain.SCR_HEIGHT;
import static com.mygdx.game.FruitFightMain.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class ScreenOptions implements Screen {
    FruitFightMain f;
    Texture bgOptions;
    Texture imgBtnClose;

    ImgButton btnClose;


    public ScreenOptions(FruitFightMain context){
        f = context;
        bgOptions = new Texture("bgIntro.png");
        imgBtnClose = new Texture("btnClose.png");

        btnClose = new ImgButton(imgBtnClose,SCR_WIDTH-100, SCR_HEIGHT-100, 90, 90);
    }

    @Override
    public void show() {
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
    }

    @Override
    public void render(float delta) {
        // screen touch
        if(Gdx.input.justTouched()) {
            f.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            f.camera.unproject(f.touch);
            if(btnClose.hit(f.touch.x, f.touch.y)){
                f.setScreen(f.screenMainMenu);
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            f.setScreen(f.screenMainMenu);
        }

        //rendering graphics
        f.camera.update();
        f.batch.setProjectionMatrix(f.camera.combined);
        f.batch.begin();
        f.batch.draw(bgOptions, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        f.batch.draw(imgBtnClose,btnClose.x, btnClose.y, btnClose.width, btnClose.height);
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
        Gdx.input.setCatchKey(Input.Keys.BACK, false);
    }

    @Override
    public void dispose() {

    }
}
