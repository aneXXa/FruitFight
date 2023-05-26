package com.mygdx.game;

import static com.mygdx.game.FruitFightMain.SCR_HEIGHT;
import static com.mygdx.game.FruitFightMain.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class ScreenAbout implements Screen {
    FruitFightMain f;
    Texture bgAbout;
    Texture imgBtnClose;
    ImgButton btnClose;
    String rules =  "Правила:\n\n";
    String textAbout = "В этой игре вы будете играть\n" +
            "за повора, которому\n" +
            "нужно нарезать оживших\n"+
            "фруктов и овощей.\n"+
            "Сверху будет показано,\n"+
            "кто ему нужен в данный момент,\n"+
            "старайтесь не ошибаться\n"+
            "и занимайтесь весёлой нарезкой.\n\n";


    public ScreenAbout(FruitFightMain context){
        f = context;
        bgAbout = new Texture("bgIntro.png");
        imgBtnClose = new Texture("btnClose.png");
        btnClose = new ImgButton(imgBtnClose,SCR_WIDTH-100, SCR_HEIGHT-100, 90, 90);
    }

    @Override
    public void show() {
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
    }

    @Override
    public void render(float delta) {
        //touches
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

        //graphics
        f.camera.update();
        f.batch.setProjectionMatrix(f.camera.combined);
        f.batch.begin();
        f.batch.draw(bgAbout, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        f.fontLarge.draw(f.batch, rules, 500, 600);
        f.font.draw(f.batch, textAbout, 300, 500);
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
        imgBtnClose.dispose();
        bgAbout.dispose();
    }
}
