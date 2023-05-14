package com.mygdx.game;

import static com.mygdx.game.FruitFightMain.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;

public class ScreenGame implements Screen {
    FruitFightMain f;
    Texture bgGame;
    Texture imgBtnPause, imgBtnMoveL, imgBtnMoveR, imgBtnAttack;
    Texture imgEnemyApple;
    Texture imgPlayer;

    ImgButton btnPause, btnMoveL, btnMoveR, btnAttack;
    ArrayList<Enemy> enemies = new ArrayList<>();
    Player player;

    boolean gameOver;

    boolean pause = false;
    long timeEnemyLastSpawn, timeEnemySpawnInterval = 1000;

    public ScreenGame(FruitFightMain context){
        f = context;

        bgGame = new Texture("bgIntro.png");
        imgBtnPause = new Texture("btnPause.png");
        imgBtnMoveL = new Texture("btnMoveL.png");
        imgBtnMoveR = new Texture("btnMoveR.png");
        imgBtnAttack = new Texture("btnAttack.png");

        imgEnemyApple = new Texture("apple.png");
        imgPlayer = new Texture("apple.png");

        btnPause = new ImgButton(imgBtnPause, SCR_WIDTH-100, SCR_HEIGHT-100, 80, 80);
        btnMoveL = new ImgButton(imgBtnMoveL, 50, 50, 100, 100);
        btnMoveR = new ImgButton(imgBtnMoveR, 225, 50, 100, 100);
        btnAttack = new ImgButton(imgBtnAttack, SCR_WIDTH-200, 50, 150, 150);

        player = new Player();
    }

    @Override
    public void show() {
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
    }

    @Override
    public void render(float delta) {
        // screen touch
        if(Gdx.input.isTouched()) {
            f.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            f.camera.unproject(f.touch);
            if(btnPause.hit(f.touch.x, f.touch.y)){
                pause = !pause;
            }
        }
        if(!pause){
            spawnFruits();
            for (int i = enemies.size()-1; i >= 0; i--){
                enemies.get(i).move();
            }

            if (Gdx.input.isTouched()) {
                f.touch.set(Gdx.input.getX(), Gdx.input.getY(),0);
                f.camera.unproject(f.touch);
                if (btnMoveL.hit(f.touch.x, f.touch.y)) {
                    player.moveL();
                }
                if (btnMoveR.hit(f.touch.x, f.touch.y)) {
                    player.moveR();
                }
                if(btnAttack.hit(f.touch.x, f.touch.y)){
                    player.killEnemy();
                    //if ()
                }
            }

        }
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            f.setScreen(f.screenMainMenu);
        }

        //rendering graphics
        f.camera.update();
        f.batch.setProjectionMatrix(f.camera.combined);
        f.batch.begin();
        f.batch.draw(bgGame, 0, 0, SCR_WIDTH, SCR_HEIGHT);

        for(Enemy enemy : enemies){
            f.batch.draw(enemy.img, enemy.getX(), enemy.getY());
        }
        f.batch.draw(imgPlayer, player.getX(), player.getY());

        f.batch.draw(imgBtnPause, btnPause.x, btnPause.y, btnPause.width, btnPause.height);
        f.batch.draw(imgBtnMoveL, btnMoveL.x, btnMoveL.y, btnMoveL.width, btnMoveL.height);
        f.batch.draw(imgBtnMoveR, btnMoveR.x, btnMoveR.y, btnMoveR.width, btnMoveR.height);
        f.batch.draw(imgBtnAttack, btnAttack.x, btnAttack.y, btnAttack.width, btnAttack.height);
        if(pause){
            //
        }
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
        bgGame.dispose();
        imgPlayer.dispose();
        imgBtnPause.dispose();
        imgBtnMoveL.dispose();
        imgBtnMoveR.dispose();
        imgBtnAttack.dispose();
    }

    void spawnFruits(){
        if(TimeUtils.millis() > timeEnemyLastSpawn+timeEnemySpawnInterval) {
            enemies.add(new Enemy(imgEnemyApple));
            timeEnemyLastSpawn = TimeUtils.millis();
        }

    /*void newGame(){
        enemyApple.clear();
        gameOver = false;
    }
    void gameOver(){
        gameOver = true;*/
    }
}
