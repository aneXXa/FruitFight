package com.mygdx.game;

import static com.mygdx.game.FruitFightMain.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class ScreenMainMenu implements Screen {
    FruitFightMain f;
    Texture bgMainMenu;
    Texture imgBtnPlay, imgBtnOptions, imgBtnAbout ,imgBtnQuit;
    Texture logo;

    ImgButton btnPlay, btnOptions, btnAbout, btnQuit;

    public ScreenMainMenu(FruitFightMain context){
        f = context;
        bgMainMenu = new Texture("bgIntro.png"); // background
        logo = new Texture("logo.png"); // logo
        // all buttons
        imgBtnPlay = new Texture("btnPlay.png");
        imgBtnOptions = new Texture("btnOptions.png");
        imgBtnAbout = new Texture("btnAbout.png");
        imgBtnQuit = new Texture("btnQuit.png");
        btnPlay = new ImgButton(imgBtnPlay, 912, 516, 224, 104);
        btnOptions = new ImgButton(imgBtnOptions, 836, 388, 372, 104);
        btnAbout = new ImgButton(imgBtnAbout, 884, 260, 276, 104);
        btnQuit = new ImgButton(imgBtnQuit, 908, 132, 228, 104);
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
            if(btnQuit.hit(f.touch.x, f.touch.y)){
                Gdx.app.exit();
            }
            if(btnPlay.hit(f.touch.x, f.touch.y)){
                f.setScreen(f.screenGame);
            }
            if(btnOptions.hit(f.touch.x, f.touch.y)){
                f.setScreen(f.screenOptions);
            }
        }

        //rendering graphics
        f.camera.update();
        f.batch.setProjectionMatrix(f.camera.combined);
        f.batch.begin();
        f.batch.draw(bgMainMenu, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        f.batch.draw(logo, 120,56);
        f.batch.draw(imgBtnPlay, btnPlay.x, btnPlay.y);
        f.batch.draw(imgBtnOptions, btnOptions.x, btnOptions.y);
        f.batch.draw(imgBtnAbout, btnAbout.x, btnAbout.y);
        f.batch.draw(imgBtnQuit, btnQuit.x, btnQuit.y);
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
        bgMainMenu.dispose();
        logo.dispose();
        imgBtnPlay.dispose();
        imgBtnOptions.dispose();
        imgBtnAbout.dispose();
        imgBtnQuit.dispose();
    }
}
