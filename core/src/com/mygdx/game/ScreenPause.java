package com.mygdx.game;

import static com.mygdx.game.FruitFightMain.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class ScreenPause implements Screen {
    FruitFightMain f;
    Texture bgPause;
    Texture imgBtnClose;

    ImgButton btnPause;

    public ScreenPause(FruitFightMain context){
        f = context;
        bgPause = new Texture("bgIntro.png");
        imgBtnClose = new Texture("btnClose.png");

        btnPause = new ImgButton(imgBtnClose, SCR_WIDTH-100, SCR_HEIGHT-100, 80, 80);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // screen touch
        if(Gdx.input.justTouched()){
            f.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            f.camera.unproject(f.touch);
            if(btnPause.hit(f.touch.x, f.touch.y)){
                f.setScreen(f.screenGame);
            }
        }

        //rendering graphics
        f.camera.update();
        f.batch.setProjectionMatrix(f.camera.combined);
        f.batch.begin();
        f.batch.draw(bgPause, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        f.batch.draw(imgBtnClose, btnPause.x, btnPause.y, btnPause.width, btnPause.height);
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
