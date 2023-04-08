package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class FruitFightMain extends Game {
	public static final int SCR_WIDTH = 1280, SCR_HEIGHT = 720;

	SpriteBatch batch;
	OrthographicCamera camera;

	Vector3 touch;
	ScreenMainMenu screenMainMenu;
	ScreenGame screenGame;
	ScreenOptions screenOptions;
	ScreenPause screenPause;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
		touch = new Vector3();

		screenMainMenu = new ScreenMainMenu(this);
		screenGame = new ScreenGame(this);
		screenOptions = new ScreenOptions(this);
		screenPause = new ScreenPause(this);

		setScreen(screenMainMenu);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
