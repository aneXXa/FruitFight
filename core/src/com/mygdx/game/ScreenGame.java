package com.mygdx.game;

import static com.mygdx.game.FruitFightMain.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;

public class ScreenGame implements Screen {
    FruitFightMain f;
    Texture bgGame, bgPause;
    Texture Pause;
    Texture imgLives;
    Texture imgBtnPause, imgBtnMoveL, imgBtnMoveR, imgBtnAttack, imgBtnResume, imgBtnHome, imgBtnRestart, imgBacktoMenu, imgBtnPlayAgain;

    Texture[] imgEnemyFruit = new Texture[8];
    Texture[] imgEnemyVeggie = new Texture[8];
    Texture[] imgPlayer = new Texture[5];

    String comboString = "Combo: ";
    String gameOverString = "GAME OVER";
    Texture[] words = new Texture[2];
    Texture currentWord;

    ImgButton btnBacktoMenu, btnPlayAgain, btnPause, btnMoveL, btnMoveR, btnAttack, btnResume, btnHome, btnRestart;

    ArrayList<Enemy> enemies = new ArrayList<>();
    Player player;

    int combo = 0;
    int index;
    public static final int GENERATE_WORD = 0,PLAY_GAME = 1;
    int condition = GENERATE_WORD;

    boolean pause = false;
    boolean gameOver;

    long timeEnemyLastSpawn, timeEnemySpawnInterval = 2000;

    public ScreenGame(FruitFightMain context){
        f = context;

        bgGame = new Texture("bgIntro.png");
        bgPause = new Texture("pauseScreen.png");
        Pause = new Texture("pause.png");
        imgLives = new Texture("HP.png");
        imgBtnPause = new Texture("btnPause.png");
        imgBtnMoveL = new Texture("btnMoveL.png");
        imgBtnMoveR = new Texture("btnMoveR.png");
        imgBtnAttack = new Texture("btnAttack.png");
        imgBacktoMenu=new Texture("btnHome.png");
        imgBtnPlayAgain = new Texture("btnRestart.png");
        imgBtnResume = new Texture("btnResume.png");
        imgBtnHome = new Texture("btnHome.png");
        imgBtnRestart = new Texture("btnRestart.png");

        for (int i = 0; i < imgPlayer.length; i++) {
            imgPlayer[i] = new Texture("player."+i+".png");
        }

        for (int i = 0; i < imgEnemyFruit.length; i++) {
            imgEnemyFruit[i] = new Texture("Enemy.Fruit."+(i/2+1)+"."+i%2+".png");
        }
        for (int i = 0; i < imgEnemyVeggie.length; i++) {
            imgEnemyVeggie[i] = new Texture("Enemy.Veggie."+(i/2+1)+"."+i%2+".png");
        }
        for (int i = 0; i < words.length; i++) {
            words[i] = new Texture("string"+(i+1)+".png");
        }

        btnPause = new ImgButton(imgBtnPause, SCR_WIDTH-100, SCR_HEIGHT-100, 90, 90);
        btnMoveL = new ImgButton(imgBtnMoveL, 50, 50, 100, 100);
        btnMoveR = new ImgButton(imgBtnMoveR, 225, 50, 100, 100);
        btnAttack = new ImgButton(imgBtnAttack, SCR_WIDTH-200, 50, 100, 100);
        btnHome = new ImgButton(imgBtnHome, SCR_WIDTH/2-40, SCR_HEIGHT/2-90, 90, 90);
        btnBacktoMenu = new ImgButton(imgBtnHome, SCR_WIDTH/2+30, SCR_HEIGHT/2-90, 90, 90);
        btnResume = new ImgButton(imgBtnResume, SCR_WIDTH/2+160, SCR_HEIGHT/2-90, 90, 90);
        btnRestart = new ImgButton(imgBtnRestart, SCR_WIDTH/2-240, SCR_HEIGHT/2-90, 90, 90);
        btnPlayAgain = new ImgButton(imgBtnRestart, SCR_WIDTH/2-150, SCR_HEIGHT/2-90, 90, 90);

        player = new Player();
    }

    @Override
    public void show() {
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
    }

    @Override
    public void render(float delta) {
        // touches
        if (Gdx.input.isTouched()) {
            f.touch.set(Gdx.input.getX(), Gdx.input.getY(),0);
            f.camera.unproject(f.touch);
            if (gameOver){
                if(btnPlayAgain.hit(f.touch.x, f.touch.y)) {
                    newGame();
                }
                if(btnBacktoMenu.hit(f.touch.x, f.touch.y)) {
                    newGame();
                    f.setScreen(f.screenMainMenu);
                }
            }
            if(!pause && !player.isChop &&!gameOver) {
                if (btnMoveL.hit(f.touch.x, f.touch.y)) {
                    player.moveL();
                }
                if (btnMoveR.hit(f.touch.x, f.touch.y)) {
                    player.moveR();
                }
            }
        }
        if(Gdx.input.justTouched()){
            f.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            f.camera.unproject(f.touch);
            if (!gameOver){
                if(pause){
                    if(btnHome.hit(f.touch.x, f.touch.y)){
                        f.setScreen(f.screenMainMenu);
                    }
                    if(btnResume.hit(f.touch.x, f.touch.y)){
                        pause = !pause;
                    }
                    if(btnRestart.hit(f.touch.x, f.touch.y)){
                        pause = !pause;
                        newGame();
                    }
                }
                else {
                    if(btnPause.hit(f.touch.x, f.touch.y)){
                        pause = !pause;
                    }
                    if(btnAttack.hit(f.touch.x, f.touch.y)){
                        player.isChop = true;
                        for (int i = enemies.size()-1; i >= 0 ; i--) {
                            if((player.overlap(enemies.get(i))) && enemies.get(i).type == index){
                                combo++;
                                condition = GENERATE_WORD;
                                enemies.remove(i);
                                break;
                            } else if((player.overlap(enemies.get(i))) && enemies.get(i).type != index){
                                combo = 0;
                                player.lives--;
                                condition = GENERATE_WORD;
                                enemies.remove(i);
                                break;
                            }
                        }
                    }
                }
            }
            if (!btnMoveL.hit(f.touch.x, f.touch.y) && !btnMoveR.hit(f.touch.x, f.touch.y) &&
                    !player.isChop) {
                player.faza = 0;
            }
        }

        // events
        if(!gameOver && !pause) {
                if (condition == GENERATE_WORD) {
                    newRound();
                }
                spawnFruits();
                for (int i = enemies.size() - 1; i >= 0; i--) {
                    enemies.get(i).move();
                }
                if (player.isChop) {
                    player.chop();
                }
                if(player.lives == 0){
                    gameOver();
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

        f.batch.draw(imgPlayer[player.faza], player.getX(), player.getY(), player.width, player.height,
                0, 0, 208, 212, player.direction, false);

        f.batch.draw(imgBtnPause, btnPause.x, btnPause.y, btnPause.width, btnPause.height);
        f.batch.draw(imgBtnMoveL, btnMoveL.x, btnMoveL.y, btnMoveL.width, btnMoveL.height);
        f.batch.draw(imgBtnMoveR, btnMoveR.x, btnMoveR.y, btnMoveR.width, btnMoveR.height);
        f.batch.draw(imgBtnAttack, btnAttack.x, btnAttack.y, btnAttack.width, btnAttack.height);
        f.font.draw(f.batch, comboString+combo, SCR_WIDTH/2-94, SCR_HEIGHT-116);
        f.batch.draw(currentWord,SCR_WIDTH/2-(currentWord.getWidth()*0.8f)/2, SCR_HEIGHT-currentWord.getHeight(), currentWord.getWidth()*0.8f, currentWord.getHeight()*0.8f);
        for (int i = 1; i < player.lives+1; i++) {
            f.batch.draw(imgLives, 70*i, SCR_HEIGHT-100, imgLives.getWidth(), imgLives.getHeight());
        }
        if(pause){
            f.batch.draw(bgPause, 0, 0, SCR_WIDTH, SCR_HEIGHT);
            f.batch.draw(Pause,SCR_WIDTH/2-Pause.getWidth()/2,SCR_HEIGHT/2+Pause.getHeight()/2, Pause.getWidth()+10, Pause.getHeight()+10);
            f.batch.draw(imgBtnResume, btnResume.x, btnResume.y, btnResume.width, btnResume.height);
            f.batch.draw(imgBtnHome, btnHome.x, btnHome.y, btnHome.width, btnHome.height);
            f.batch.draw(imgBtnRestart, btnRestart.x, btnRestart.y, btnRestart.width, btnRestart.height);
        }
        if(gameOver) {
            f.batch.draw(bgPause, 0, 0, SCR_WIDTH, SCR_HEIGHT);
            f.fontLarge.draw(f.batch, gameOverString,450,500);
            f.batch.draw(imgBacktoMenu, SCR_WIDTH/2+30, SCR_HEIGHT/2-90, btnHome.width, btnHome.height);
            f.batch.draw(imgBtnPlayAgain,SCR_WIDTH/2-150, btnPlayAgain.y, btnRestart.width, btnRestart.height);
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
        for (Texture texture : imgPlayer) {
            texture.dispose();
        }
        for (Texture texture : imgEnemyFruit) {
            texture.dispose();
        }
        for (Texture texture : imgEnemyVeggie) {
            texture.dispose();
        }
        imgBtnPause.dispose();
        imgBtnMoveL.dispose();
        imgBtnMoveR.dispose();
        imgBtnAttack.dispose();
        imgBtnRestart.dispose();
        imgBtnHome.dispose();
        imgBtnResume.dispose();
        bgPause.dispose();
    }

    void spawnFruits() {
        if (TimeUtils.millis() > timeEnemyLastSpawn + timeEnemySpawnInterval) {
            enemies.add(new Enemy(imgEnemyFruit, imgEnemyVeggie));
            timeEnemyLastSpawn = TimeUtils.millis();
        }
    }

    void newGame(){
        gameOver = false;
        condition = GENERATE_WORD;
        enemies.clear();
        timeEnemyLastSpawn = TimeUtils.millis();
        player.x = SCR_WIDTH/2;
        player.lives=3;
        combo = 0;
        player.faza = 0;
    }
    void newRound(){
        index = MathUtils.random.nextInt(words.length);
        currentWord = words[index];
        condition = PLAY_GAME;
    }
    void gameOver(){
        gameOver = true;
        player.faza = 0; // сделаю спрайт будет не ноль а "проигрышный" спрайт
    }
}
